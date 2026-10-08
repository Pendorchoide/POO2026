package com.smartticket.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Datos de la tarjeta que se envian a la pasarela SIMULADA.
 * No se persisten en ningun lugar: viajan al gateway y se descartan.
 */
@Schema(description = "Datos de la tarjeta para la pasarela simulada")
public record DatosDeTarjetaRequest(

        @Schema(description = "Numero de tarjeta", example = "4111111111111111",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "el numero de tarjeta es obligatorio")
        String numero,

        @Schema(description = "Titular", example = "Ana Perez",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "el titular es obligatorio")
        String titular,

        @Schema(description = "Vencimiento MM/AA", example = "12/29",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "el vencimiento es obligatorio")
        @Pattern(regexp = "\\d{2}/\\d{2}", message = "el vencimiento debe tener formato MM/AA")
        String vencimiento,

        @Schema(description = "Codigo de seguridad", example = "123",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "el cvv es obligatorio")
        String cvv
) {
}