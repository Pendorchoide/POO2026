package com.smartticket.domain.excepcion;

/**
 * Se lanza cuando la pasarela de pagos responde RECHAZADO (fondos insuficientes,
 * tarjeta vencida, etc.). La venta NO se registra y las entradas quedan intactas.
 *
 * <p>Se traduce a HTTP 402 Payment Required: el recurso existe, pero hay que
 * resolver el pago para obtenerlo.</p>
 */
public class PagoRechazadoException extends RuntimeException {

    public PagoRechazadoException(String mensaje) {
        super(mensaje);
    }

    public PagoRechazadoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}