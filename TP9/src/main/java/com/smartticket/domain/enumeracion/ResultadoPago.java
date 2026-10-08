package com.smartticket.domain.enumeracion;

/**
 * Respuesta de la pasarela de pagos simulada.
 */
public enum ResultadoPago {

    /** La pasarela autorizo la operacion: se puede emitir la venta. */
    APROBADO,

    /** La pasarela rechazo la operacion: no se registra venta ni se emiten entradas. */
    RECHAZADO
}