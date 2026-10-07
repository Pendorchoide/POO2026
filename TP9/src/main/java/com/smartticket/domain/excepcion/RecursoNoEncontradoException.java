package com.smartticket.domain.excepcion;

/**
 * Se lanza cuando un id no existe en la base. El {@code @RestControllerAdvice}
 * la traduce a HTTP 404, para que los controllers no tengan que manejarla.
 */
public class RecursoNoEncontradoException extends RuntimeException {

    public RecursoNoEncontradoException(String recurso, Object id) {
        super(recurso + " con id " + id + " no existe");
    }

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}