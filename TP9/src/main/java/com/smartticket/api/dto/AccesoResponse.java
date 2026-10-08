package com.smartticket.api.dto;

import com.smartticket.domain.enumeracion.EstadoTicket;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Resultado de validar un QR en la puerta")
public record AccesoResponse(

        @Schema(description = "Codigo de resultado legible para el acomodador",
                example = "ACCESO_PERMITIDO")
        String resultado,

        @Schema(description = "Mensaje a mostrar en el dispositivo", example = "Acceso Permitido")
        String mensaje,

        @Schema(example = "12") Long entradaId,
        @Schema(example = "QR-2026-0001") String qrCode,
        @Schema(example = "Rock Fest") String eventoNombre,
        @Schema(example = "Platea") String sectorNombre,
        @Schema(example = "UTILIZADA") EstadoTicket estado,

        @Schema(description = "Hora exacta en que se registro el ingreso")
        Instant fechaIngreso
) {
}