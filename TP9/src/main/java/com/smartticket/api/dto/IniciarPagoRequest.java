package com.smartticket.api.dto;

import com.smartticket.domain.enumeracion.MedioPago;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Pago de las entradas seleccionadas: cliente, que se compra y con que tarjeta")
public record IniciarPagoRequest(

        @Schema(description = "Id del Cliente que compra", example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "el clienteId es obligatorio")
        Long clienteId,

        @Schema(description = "Id del Evento", example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "el eventoId es obligatorio")
        Long eventoId,

        @Schema(description = "Que se compra, por sector", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotEmpty(message = "debe enviar al menos una linea")
        @Valid
        List<LineaCotizacionRequest> lineas,

        @Schema(description = "Metodo de pago", example = "TARJETA_CREDITO",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "el medioPago es obligatorio")
        MedioPago medioPago,

        @Schema(description = "Datos de la tarjeta para la pasarela simulada",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @Valid
        @NotNull(message = "los datos de la tarjeta son obligatorios")
        DatosDeTarjetaRequest tarjeta
) {
}