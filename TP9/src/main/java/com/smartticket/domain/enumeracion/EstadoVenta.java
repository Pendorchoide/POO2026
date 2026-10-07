package com.smartticket.domain.enumeracion;

/**
 * Ciclo de vida de una Venta.
 */
public enum EstadoVenta {

    /** Creada por el Cliente, todavia sin confirmar el pago. */
    PENDIENTE,

    /** Pago acreditado: las entradas pasan a EMITIDA. */
    PAGADA,

    /** Venta anulada: las entradas vuelven a DISPONIBLE. */
    CANCELADA
}