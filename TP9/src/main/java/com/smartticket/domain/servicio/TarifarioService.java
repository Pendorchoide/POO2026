package com.smartticket.domain.servicio;

import com.smartticket.domain.excepcion.RecursoNoEncontradoException;
import com.smartticket.domain.repository.PrecioEventoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Experto en Informacion (GRASP) para el precio: es el UNICO lugar del sistema
 * donde se resuelve "cuanto cuesta esta entrada en este evento y sector".
 *
 * <p>El precio vive en {@code PrecioEvento} (evento + sector), no en Sector ni en
 * Entrada, asi que cotizar y pagar consultan al mismo servicio y nunca duplican
 * la formula.</p>
 */
@Service
public class TarifarioService {

    private final PrecioEventoRepository precioEventoRepository;

    public TarifarioService(PrecioEventoRepository precioEventoRepository) {
        this.precioEventoRepository = precioEventoRepository;
    }

    @Transactional(readOnly = true)
    public BigDecimal precioDe(Long eventoId, Long sectorId) {
        return precioEventoRepository.findByEventoIdAndSectorId(eventoId, sectorId)
                .map(precio -> precio.getPrecio())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No hay precio fijado para el sector " + sectorId + " en el evento " + eventoId));
    }

    /** La multiplicacion esta aca, no en el controller. */
    public BigDecimal subtotal(BigDecimal precioUnitario, int cantidad) {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }
}