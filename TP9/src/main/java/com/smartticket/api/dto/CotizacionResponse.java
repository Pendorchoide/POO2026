package com.smartticket.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Schema(description = "Cotizacion calculada con precios del evento y disponibilidad real por sector")
public record CotizacionResponse(

        @Schema(example = "1") Long eventoId,
        @Schema(example = "Recital en el Estadio") String eventoNombre,
        @Schema(description = "Id de la venta creada en estado PENDIENTE", example = "10") Long ventaId,
        @Schema(example = "PENDIENTE") String estadoVenta,
        @Schema(example = "45000.00") BigDecimal total,
        @Schema(description = "Instante en que caduca la reserva de las entradas") Instant expiresAt,
        @Schema(description = "Detalle por sector") List<LineaCotizacionResponse> lineas
) {
}