package com.smartticket.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Escanear un codigo QR en la puerta de acceso")
public record AccesoRequest(

        @Schema(description = "Codigo QR impreso en la entrada",
                example = "QR-2026-0001", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "el codigo QR es obligatorio")
        String qrCode
) {
}