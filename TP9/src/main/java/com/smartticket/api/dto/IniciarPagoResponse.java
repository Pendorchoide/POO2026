package com.smartticket.api.dto;

import com.smartticket.domain.enumeracion.EstadoTicket;
import com.smartticket.domain.enumeracion.MedioPago;
import com.smartticket.domain.enumeracion.ResultadoPago;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Schema(description = "Pago aprobado: venta PAGADA con los QR de acceso de cada entrada emitida")
public record IniciarPagoResponse(

        @Schema(example = "7") Long ventaId,
        @Schema(example = "VTA-20261007183000123") String codigoVenta,
        @Schema(example = "PAGADA") String estadoVenta,
        @Schema(example = "24000.00") BigDecimal total,
        @Schema(example = "TARJETA_CREDITO") MedioPago medioPago,
        @Schema(description = "Instante en que se acredito el pago") Instant pagadoEn,

        @Schema(description = "Lo que respondio la pasarela simulada")
        ResultadoPago pasarelaResultado,
        @Schema(description = "Referencia de la operacion ante la pasarela")
        String pasarelaReferencia,

        @Schema(description = "Entradas emitidas: sus QR habilitan el ingreso en puerta")
        List<EntradaEmitidaResponse> entradas
) {

    @Schema(description = "Una entrada emitida y su codigo QR")
    public record EntradaEmitidaResponse(
            @Schema(example = "12") Long entradaId,
            @Schema(example = "QR-8F3A...") String qrCode,
            @Schema(example = "2") Long sectorId,
            @Schema(example = "Platea") String sectorNombre,
            @Schema(example = "EMITIDA") EstadoTicket estado
    ) {
    }
}