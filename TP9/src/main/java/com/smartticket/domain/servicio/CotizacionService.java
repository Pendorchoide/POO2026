package com.smartticket.domain.servicio;

import com.smartticket.api.dto.CotizacionRequest;
import com.smartticket.api.dto.CotizacionResponse;
import com.smartticket.api.dto.LineaCotizacionRequest;
import com.smartticket.api.dto.LineaCotizacionResponse;
import com.smartticket.domain.entity.Evento;
import com.smartticket.domain.entity.Sector;
import com.smartticket.domain.enumeracion.EstadoTicket;
import com.smartticket.domain.excepcion.ConflictoDeEstadoException;
import com.smartticket.domain.excepcion.RecursoNoEncontradoException;
import com.smartticket.domain.excepcion.ValidacionDeNegocioException;
import com.smartticket.domain.repository.EntradaRepository;
import com.smartticket.domain.repository.EventoRepository;
import com.smartticket.domain.repository.SectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CU 01 - Seleccion y cotizacion.
 *
 * <p>Operacion de SOLO LECTURA: calcula el costo exacto y verifica disponibilidad.
 * No reserva entradas ni crea ventas; eso ocurre recien en {@link PagoService}.</p>
 *
 * <p>Toda la validacion y el calculo viven aca (GRASP: Experto en Informacion).
 * El {@link CotizacionController} unicamente recibe la peticion y devuelve la respuesta.</p>
 */
@Service
public class CotizacionService {

    /** Mensaje exigido por la HU: debe ser exactamente este. */
    static final String SIN_DISPONIBILIDAD = "Capacidad insuficiente o entradas no disponibles";

    private final EventoRepository eventoRepository;
    private final SectorRepository sectorRepository;
    private final EntradaRepository entradaRepository;
    private final TarifarioService tarifario;

    public CotizacionService(EventoRepository eventoRepository,
                             SectorRepository sectorRepository,
                             EntradaRepository entradaRepository,
                             TarifarioService tarifario) {
        this.eventoRepository = eventoRepository;
        this.sectorRepository = sectorRepository;
        this.entradaRepository = entradaRepository;
        this.tarifario = tarifario;
    }

    @Transactional(readOnly = true)
    public CotizacionResponse cotizar(CotizacionRequest request) {
        Evento evento = eventoRepository.findById(request.eventoId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Evento", request.eventoId()));

        BigDecimal subtotal = BigDecimal.ZERO;
        List<LineaCotizacionResponse> lineas = new ArrayList<>();
        Map<Long, Integer> pedidoAcumuladoPorSector = new HashMap<>();

        for (LineaCotizacionRequest linea : request.lineas()) {
            int cantidad = CantidadesDeEntradas.validar(linea.cantidad());
            Sector sector = sectorDelEvento(linea.sectorId(), evento);

            // Acumula por sector: dos lineas del mismo sector se suman antes de comparar.
            int acumulado = pedidoAcumuladoPorSector.merge(sector.getId(), cantidad, Integer::sum);
            long disponibles = entradaRepository.countByEventoIdAndSectorIdAndEstado(
                    evento.getId(), sector.getId(), EstadoTicket.DISPONIBLE);
            if (disponibles < acumulado) {
                throw new ConflictoDeEstadoException(SIN_DISPONIBILIDAD);
            }

            BigDecimal precioUnitario = tarifario.precioDe(evento.getId(), sector.getId());
            BigDecimal subtotalLinea = tarifario.subtotal(precioUnitario, cantidad);
            subtotal = subtotal.add(subtotalLinea);

            lineas.add(new LineaCotizacionResponse(
                    sector.getId(), sector.getNombre(), cantidad,
                    precioUnitario, subtotalLinea, (int) disponibles));
        }

        return new CotizacionResponse(
                evento.getId(), evento.getNombre(),
                evento.getLugar().getId(), evento.getLugar().getNombre(),
                subtotal, subtotal, lineas);
    }

    /** El sector debe existir y estar fisicamente en el lugar donde se hace el evento. */
    private Sector sectorDelEvento(Long sectorId, Evento evento) {
        Sector sector = sectorRepository.findById(sectorId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Sector", sectorId));
        if (!sector.getLugar().getId().equals(evento.getLugar().getId())) {
            throw new ValidacionDeNegocioException(
                    "El sector " + sectorId + " no pertenece al lugar donde se realiza el evento");
        }
        return sector;
    }
}