package com.smartticket.domain.pasarela;

import java.math.BigDecimal;

/**
 * PUERTO (DIP): el caso de uso conoce esta abstraccion y nunca la implementacion.
 * Hoy la cumple {@link PasarelaDePagosSimulada}; mañana, un cliente HTTP real
 * contra MercadoPago o Stripe, sin tocar {@code PagoService}.
 */
public interface PasarelaDePagos {

    /**
     * Consulta la pasarela por la operacion.
     *
     * @param numeroTarjeta tarjeta con la que se paga
     * @param monto         importe a debitar
     * @return la decision de la pasarela; nunca lanza por rechazo, eso lo decide el caso de uso
     */
    RespuestaPasarela procesar(String numeroTarjeta, BigDecimal monto);
}