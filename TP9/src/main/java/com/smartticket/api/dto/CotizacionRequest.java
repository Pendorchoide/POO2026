package com.smartticket.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Pedido de cotizacion: que entradas se quieren reservar y a que evento pertenecen")
public record CotizacionRequest(

        @Schema(description = "Id del Evento", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "el eventoId es obligatorio")
        Long eventoId,

        @Schema(description = "Entradas deseadas, al menos una linea")
        @NotEmpty(message = "debe enviar al menos una linea")
        @Valid
        List<LineaCotizacionRequest> lineas
) {
}