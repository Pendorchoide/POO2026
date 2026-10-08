package com.smartticket.domain.pasarela;

import com.smartticket.domain.enumeracion.ResultadoPago;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * ADAPTADOR simulado. Regla unica y deterministica, para poder probar a mano:
 * <ul>
 *   <li>tarjeta terminada en {@code 0000} &rarr; RECHAZADO (fondos insuficientes)</li>
 *   <li>cualquier otra &rarr; APROBADO</li>
 * </ul>
 *
 * <p>Cada consulta queda registrada en el log: es la forma de comprobar que el
 * sistema aborta <strong>antes</strong> de llamar a la pasarela cuando se perdio
 * la disponibilidad (HU2, escenario 3).</p>
 */
@Component
public class PasarelaDePagosSimulada implements PasarelaDePagos {

    private static final Logger log = LoggerFactory.getLogger(PasarelaDePagosSimulada.class);

    private static final String SUFFIX_RECHAZO = "0000";

    @Override
    public RespuestaPasarela procesar(String numeroTarjeta, BigDecimal monto) {
        String normalizado = numeroTarjeta == null ? "" : numeroTarjeta.replaceAll("\\s+", "");

        if (normalizado.endsWith(SUFFIX_RECHAZO)) {
            log.warn("Pasarela simulada: RECHAZADO monto={} ref=REF-{}", monto, normalizado);
            return new RespuestaPasarela(
                    ResultadoPago.RECHAZADO,
                    "REF-" + System.nanoTime(),
                    "Fondos insuficientes");
        }

        String referencia = "REF-" + System.nanoTime();
        log.info("Pasarela simulada: APROBADO monto={} ref={}", monto, referencia);
        return new RespuestaPasarela(ResultadoPago.APROBADO, referencia, "Operacion aprobada");
    }
}