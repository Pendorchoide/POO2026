package com.smartticket.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Una linea del pedido. No lleva {@code @Min}: la regla "cantidad > 0" es una
 * regla de negocio y la resuelve el servicio (HU1, escenario 3), no el marco.
 */
@Schema(description = "Cantidad de entradas deseada para un sector concreto")
public record LineaCotizacionRequest(

        @Schema(description = "Id del Sector", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "el sectorId es obligatorio")
        Long sectorId,

        @Schema(description = "Cantidad de entradas", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "la cantidad es obligatoria")
        Integer cantidad
) {
}