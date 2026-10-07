package com.smartticket.domain.enumeracion;

/**
 * Ciclo de vida de una Entrada.
 */
public enum EstadoTicket {

    /** Generada al crear el evento, aun no fue vendida ni reservada. */
    DISPONIBLE,

    /** Apartada temporalmente dentro de una cotizacion o venta pendiente. */
    RESERVADA,

    /** Pagada y entregada al cliente, todavia no ingreso al lugar. */
    EMITIDA,

    /** Ingreso registrado: el codigo QR fue escaneado en el acceso. */
    UTILIZADA
}