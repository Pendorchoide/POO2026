package com.smartticket.domain.servicio;

import com.smartticket.api.dto.AccesoResponse;
import com.smartticket.domain.entity.Entrada;
import com.smartticket.domain.enumeracion.EstadoTicket;
import com.smartticket.domain.excepcion.ConflictoDeEstadoException;
import com.smartticket.domain.excepcion.RecursoNoEncontradoException;
import com.smartticket.domain.repository.EntradaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

/**
 * CU 03 - Control de acceso en puerta.
 *
 * <p>El acomodador manda el QR; la decision (y el por que) vive aca, nunca en el
 * controller. Cada rechazo tiene su mensaje exacto y su codigo HTTP los resuelve
 * {@code ManejadorDeExcepciones}.</p>
 */
@Service
public class AccesoService {

    /** Mensajes exigidos por HU3. */
    static final String TICKET_INVALIDO = "Ticket Inválido o Inexistente";
    static final String ENTRADA_YA_UTILIZADA = "Entrada ya utilizada";
    static final String TICKET_NO_EMITIDO = "Ticket no emitido / Falta de pago";
    static final String ACCESO_PERMITIDO = "Acceso Permitido";

    private static final Logger log = LoggerFactory.getLogger(AccesoService.class);

    private final EntradaRepository entradaRepository;

    public AccesoService(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }

    @Transactional
    public AccesoResponse validarIngreso(String qrCode) {
        Entrada entrada = entradaRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new RecursoNoEncontradoException(TICKET_INVALIDO));

        switch (entrada.getEstado()) {
            // Escenario 2: se aborta ANTES de tocar la fecha, asi no se pisa el ingreso original.
            case UTILIZADA -> {
                throw new ConflictoDeEstadoException(ENTRADA_YA_UTILIZADA);
            }
            // Escenario 4: venta que nunca se completo.
            case DISPONIBLE, RESERVADA -> {
                throw new ConflictoDeEstadoException(TICKET_NO_EMITIDO);
            }
            case EMITIDA -> {
                entrada.setEstado(EstadoTicket.UTILIZADA);
                entrada.setFechaIngreso(Instant.now());
                entradaRepository.save(entrada);
                log.info("Acceso permitido: entrada={} qr={} ingreso={}",
                        entrada.getId(), entrada.getQrCode(), entrada.getFechaIngreso());
            }
        }

        return new AccesoResponse(
                "ACCESO_PERMITIDO",
                ACCESO_PERMITIDO,
                entrada.getId(),
                entrada.getQrCode(),
                entrada.getEvento().getNombre(),
                entrada.getSector().getNombre(),
                entrada.getEstado(),
                entrada.getFechaIngreso());
    }
}