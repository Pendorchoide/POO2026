package com.smartticket.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Detalle cotizado de una linea")
public record LineaCotizacionResponse(

        @Schema(example = "1") Long sectorId,
        @Schema(example = "Campo") String sectorNombre,
        @Schema(example = "2") Integer cantidad,
        @Schema(example = "15000.00") BigDecimal precioUnitario,
        @Schema(example = "30000.00") BigDecimal subtotal,
        @Schema(description = "Entradas DISPONIBLE que quedaron en el sector", example = "120") Integer disponibles
) {
}