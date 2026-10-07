package com.smartticket.api.dto;

import com.smartticket.domain.enumeracion.MedioPago;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Datos para iniciar el pago de una venta pendiente")
public record IniciarPagoRequest(

        @Schema(description = "Id de la venta PENDIENTE a pagar", example = "10",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "el ventaId es obligatorio")
        Long ventaId,

        @Schema(description = "Id del Cliente que compra", example = "7",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "el clienteId es obligatorio")
        Long clienteId,

        @Schema(description = "Metodo de pago", example = "TARJETA_CREDITO")
        @NotNull(message = "el medioPago es obligatorio")
        MedioPago medioPago
) {
}