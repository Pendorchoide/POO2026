package com.smartticket.domain.excepcion;

/**
 * Se lanza cuando una regla de negocio impide completar la operacion
 * (por ejemplo, pagar una venta que ya no esta PENDIENTE).
 * Se traduce a HTTP 409 Conflict.
 */
public class ConflictoDeEstadoException extends RuntimeException {

    public ConflictoDeEstadoException(String mensaje) {
        super(mensaje);
    }
}