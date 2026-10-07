package com.smartticket.api;

import com.smartticket.api.dto.IniciarPagoRequest;
import com.smartticket.api.dto.IniciarPagoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CU 02 - Inicio de pago de una venta.
 * Estructura lista: contrato HTTP definido, logica pendiente de implementacion.
 */
@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Inicio y confirmacion del pago de una venta")
public class PagoController {

    @Operation(
            summary = "Inicia el pago de una venta pendiente",
            description = "Registra el medio de pago, pide el checkout a la pasarela y devuelve "
                    + "el token y la URL para que el Cliente complete la operacion.")
    @ApiResponse(responseCode = "201", description = "Pago iniciado contra la pasarela")
    @ApiResponse(responseCode = "404", description = "Venta o cliente inexistente")
    @ApiResponse(responseCode = "409", description = "La venta no esta en PENDIENTE o la reserva caduco")
    @PostMapping
    public ResponseEntity<IniciarPagoResponse> iniciarPago(@Valid @RequestBody IniciarPagoRequest request) {
        // TODO: implementar
        return ResponseEntity.status(501).build();
    }
}