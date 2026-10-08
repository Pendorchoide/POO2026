package com.smartticket.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Precio que la productora fija para un sector dentro de un evento concreto")
public record PrecioEventoRequest(

        @Schema(description = "Precio de la entrada en ese sector", example = "12000.00",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "el precio es obligatorio")
        @DecimalMin(value = "0.01", message = "el precio debe ser mayor a cero")
        BigDecimal precio,

        @Schema(description = "Id del Evento", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "el eventoId es obligatorio")
        Long eventoId,

        @Schema(description = "Id del Sector", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "el sectorId es obligatorio")
        Long sectorId
) {
}