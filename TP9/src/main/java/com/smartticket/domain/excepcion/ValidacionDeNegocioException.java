package com.smartticket.domain.excepcion;

/**
 * Se lanza cuando un dato del pedido viola una regla de negocio del dominio
 * (por ejemplo, pedir 0 entradas o una cantidad negativa).
 *
 * <p>Se traduce a HTTP 400. Es distinto de una falla de {@code @Valid}: ahi el error
 * lo detecta el marco en la capa de entrada; aca lo detecta el Experto en Informacion
 * con datos que el marco no puede conocer.</p>
 */
public class ValidacionDeNegocioException extends RuntimeException {

    public ValidacionDeNegocioException(String mensaje) {
        super(mensaje);
    }
}