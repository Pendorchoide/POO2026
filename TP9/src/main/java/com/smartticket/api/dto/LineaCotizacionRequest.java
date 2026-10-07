package com.smartticket.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Cantidad de entradas deseada para un sector concreto")
public record LineaCotizacionRequest(

        @Schema(description = "Id del Sector", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "el sectorId es obligatorio")
        Long sectorId,

        @Schema(description = "Cantidad de entradas", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "la cantidad es obligatoria")
        @Min(value = 1, message = "la cantidad debe ser mayor o igual a 1")
        Integer cantidad
) {
}