package com.smartticket.domain.servicio;

import com.smartticket.api.dto.IniciarPagoRequest;
import com.smartticket.api.dto.IniciarPagoResponse;
import com.smartticket.api.dto.LineaCotizacionRequest;
import com.smartticket.domain.entity.Cliente;
import com.smartticket.domain.entity.Entrada;
import com.smartticket.domain.entity.Evento;
import com.smartticket.domain.entity.Sector;
import com.smartticket.domain.entity.Venta;
import com.smartticket.domain.enumeracion.EstadoTicket;
import com.smartticket.domain.enumeracion.EstadoVenta;
import com.smartticket.domain.excepcion.ConflictoDeEstadoException;
import com.smartticket.domain.excepcion.PagoRechazadoException;
import com.smartticket.domain.excepcion.RecursoNoEncontradoException;
import com.smartticket.domain.pasarela.PasarelaDePagos;
import com.smartticket.domain.pasarela.RespuestaPasarela;
import com.smartticket.domain.repository.ClienteRepository;
import com.smartticket.domain.repository.EntradaRepository;
import com.smartticket.domain.repository.EventoRepository;
import com.smartticket.domain.repository.SectorRepository;
import com.smartticket.domain.repository.VentaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CU 02 - Procesamiento de pago y emision de entradas.
 *
 * <p>Orden de la operacion, y por que esta en este orden:</p>
 * <ol>
 *   <li><b>Validar disponibilidad</b> &rarr; si alguien ya compro esas entradas,
 *       abortamos ANTES de gastar una llamada a la pasarela (escenario 3).</li>
 *   <li><b>Consultar la pasarela</b> &rarr; si dice RECHAZADO, lanzamos
 *       {@link PagoRechazadoException} y no se persiste nada (escenario 2).</li>
 *   <li><b>Reclamar las entradas bajo lock pesimista</b> &rarr; cierra la brecha
 *       de concurrencia entre el paso 1 y el commit.</li>
 *   <li><b>Crear la Venta PAGADA</b> y pasar las entradas a EMITIDA (escenario 1).</li>
 * </ol>
 *
 * <p>Si algo falla en el paso 3 o 4, la transaccion se revierte completa: no queda
 * venta ni entradas emitidas a medias.</p>
 */
@Service
public class PagoService {

    private static final Logger log = LoggerFactory.getLogger(PagoService.class);

    /** Mensaje exigido por HU2, escenario 3. */
    static final String SIN_DISPONIBILIDAD = "Los lugares seleccionados ya no se encuentran disponibles";
    /** Mensaje exigido por HU2, escenario 2. */
    static final String PAGO_DENEGADO = "Pago denegado";

    private static final DateTimeFormatter FORMATO_CODIGO =
            DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    private final ClienteRepository clienteRepository;
    private final EventoRepository eventoRepository;
    private final SectorRepository sectorRepository;
    private final EntradaRepository entradaRepository;
    private final VentaRepository ventaRepository;
    private final TarifarioService tarifario;
    private final PasarelaDePagos pasarela;

    public PagoService(ClienteRepository clienteRepository,
                       EventoRepository eventoRepository,
                       SectorRepository sectorRepository,
                       EntradaRepository entradaRepository,
                       VentaRepository ventaRepository,
                       TarifarioService tarifario,
                       PasarelaDePagos pasarela) {
        this.clienteRepository = clienteRepository;
        this.eventoRepository = eventoRepository;
        this.sectorRepository = sectorRepository;
        this.entradaRepository = entradaRepository;
        this.ventaRepository = ventaRepository;
        this.tarifario = tarifario;
        this.pasarela = pasarela;
    }

    @Transactional
    public IniciarPagoResponse pagar(IniciarPagoRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente", request.clienteId()));
        Evento evento = eventoRepository.findById(request.eventoId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Evento", request.eventoId()));

        List<LineaCotizacionRequest> lineas = ordenarPorSector(request.lineas());

        // 1. Disponibilidad ANTES de la pasarela.
        BigDecimal total = BigDecimal.ZERO;
        Map<Long, Integer> pedidoAcumuladoPorSector = new HashMap<>();
        for (LineaCotizacionRequest linea : lineas) {
            int cantidad = CantidadesDeEntradas.validar(linea.cantidad());
            Sector sector = sectorDelEvento(linea.sectorId(), evento);
            int acumulado = pedidoAcumuladoPorSector.merge(sector.getId(), cantidad, Integer::sum);

            long disponibles = entradaRepository.countByEventoIdAndSectorIdAndEstado(
                    evento.getId(), sector.getId(), EstadoTicket.DISPONIBLE);
            if (disponibles < acumulado) {
                log.warn("Pago abortado sin consultar pasarela: sector {} disponible={} pedido={}",
                        sector.getId(), disponibles, acumulado);
                throw new ConflictoDeEstadoException(SIN_DISPONIBILIDAD);
            }
            total = total.add(tarifario.subtotal(
                    tarifario.precioDe(evento.getId(), sector.getId()), cantidad));
        }

        // 2. Pasarela.
        RespuestaPasarela decision = pasarela.procesar(request.tarjeta().numero(), total);
        if (!decision.aprobada()) {
            throw new PagoRechazadoException(PAGO_DENEGADO);
        }

        // 3. Reclamar entradas: bloquea las filas hasta que cierre la transaccion.
        List<Entrada> emitidas = new ArrayList<>();
        for (LineaCotizacionRequest linea : lineas) {
            int cantidad = CantidadesDeEntradas.validar(linea.cantidad());
            List<Entrada> libres = entradaRepository.bloquearPorEventoSectorYEstado(
                    evento.getId(), linea.sectorId(), EstadoTicket.DISPONIBLE);
            if (libres.size() < cantidad) {
                throw new ConflictoDeEstadoException(SIN_DISPONIBILIDAD);
            }
            emitidas.addAll(libres.subList(0, cantidad));
        }

        // 4. Persistir la venta y las entradas emitidas.
        Venta venta = new Venta();
        venta.setCodigo(generarCodigo());
        venta.setEstado(EstadoVenta.PAGADA);
        venta.setMedioPago(request.medioPago());
        venta.setFechaCierre(Instant.now());
        venta.setCliente(cliente);

        for (Entrada entrada : emitidas) {
            entrada.setEstado(EstadoTicket.EMITIDA);
            entrada.setVenta(venta);
            venta.getEntradas().add(entrada);
        }
        Venta guardada = ventaRepository.save(venta);
        log.info("Venta {} PAGADA con {} entradas emitidas, total={}",
                guardada.getCodigo(), emitidas.size(), total);

        return armarRespuesta(guardada, total, decision);
    }

    private IniciarPagoResponse armarRespuesta(Venta venta, BigDecimal total, RespuestaPasarela decision) {
        List<IniciarPagoResponse.EntradaEmitidaResponse> entradas = venta.getEntradas().stream()
                .map(entrada -> new IniciarPagoResponse.EntradaEmitidaResponse(
                        entrada.getId(),
                        entrada.getQrCode(),
                        entrada.getSector().getId(),
                        entrada.getSector().getNombre(),
                        entrada.getEstado()))
                .toList();

        return new IniciarPagoResponse(
                venta.getId(),
                venta.getCodigo(),
                venta.getEstado().name(),
                total,
                venta.getMedioPago(),
                venta.getFechaCierre(),
                decision.resultado(),
                decision.referencia(),
                entradas);
    }

    /** Procesa los sectores en orden estable: dos pagos concurrentes bloquean las mismas filas en el mismo orden. */
    private List<LineaCotizacionRequest> ordenarPorSector(List<LineaCotizacionRequest> lineas) {
        return lineas.stream()
                .sorted(Comparator.comparing(LineaCotizacionRequest::sectorId))
                .toList();
    }

    private Sector sectorDelEvento(Long sectorId, Evento evento) {
        Sector sector = sectorRepository.findById(sectorId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Sector", sectorId));
        if (!sector.getLugar().getId().equals(evento.getLugar().getId())) {
            throw new RecursoNoEncontradoException(
                    "El sector " + sectorId + " no pertenece al lugar del evento " + evento.getId());
        }
        return sector;
    }

    private String generarCodigo() {
        return "VTA-" + LocalDateTime.now().format(FORMATO_CODIGO);
    }
}