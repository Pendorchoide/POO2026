package com.smartticket.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

/**
 * Resultado de la cotizacion. Es una operacion de SOLO LECTURA: no crea venta
 * ni reserva entradas. La venta nace recien al pagar (HU2).
 */
@Schema(description = "Cotizacion calculada con los precios fijados por la productora y la disponibilidad real")
public record CotizacionResponse(

        @Schema(example = "1") Long eventoId,
        @Schema(example = "Rock Fest") String eventoNombre,
        @Schema(example = "1") Long lugarId,
        @Schema(example = "Estadio Unico") String lugarNombre,

        @Schema(description = "Suma de los subtotales por sector", example = "24000.00")
        BigDecimal subtotal,

        @Schema(description = "Costo total exacto de la compra", example = "24000.00")
        BigDecimal total,

        @Schema(description = "Detalle por sector, con precio unitario y disponibilidad consultada")
        List<LineaCotizacionResponse> lineas
) {
}