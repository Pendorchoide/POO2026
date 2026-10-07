package com.smartticket.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;

@Schema(description = "Resultado del inicio de pago: id de la transaccion y datos para redirigir al gateway")
public record IniciarPagoResponse(

        @Schema(example = "10") Long ventaId,
        @Schema(example = "VTA-2026-000010") String codigoVenta,
        @Schema(description = "Estado de la venta tras iniciar el pago", example = "PENDIENTE") String estadoVenta,
        @Schema(example = "45000.00") BigDecimal monto,
        @Schema(description = "Token devuelto por la pasarela para confirmar el pago")
        String paymentToken,
        @Schema(description = "URL de la pasarela donde el Cliente completa el pago", example = "https://pay.example.com/abc")
        String checkoutUrl,
        @Schema(description = "Instante limite para completar el pago") Instant expiresAt
) {
}