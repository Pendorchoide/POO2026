package com.smartticket.api;

import com.smartticket.api.dto.IniciarPagoRequest;
import com.smartticket.api.dto.IniciarPagoResponse;
import com.smartticket.domain.servicio.PagoService;
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
 * CU 02 - Procesamiento de pago y emision de entradas.
 *
 * <p>SRP: el controller no chequea stock, no llama a la pasarela y no cambia
 * estados. Recibe el pedido, delega en {@link PagoService} y devuelve 201.</p>
 */
@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Pago de entradas y emision de los QR de acceso")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @Operation(
            summary = "Paga y emite las entradas",
            description = "Valida disponibilidad, consulta la pasarela simulada y, si esta aprueba, "
                    + "crea la Venta PAGADA y pasa las entradas a EMITIDA devolviendo sus QR.")
    @ApiResponse(responseCode = "201", description = "Pago aprobado: venta PAGADA con QRs emitidos")
    @ApiResponse(responseCode = "400", description = "Cantidad de entradas menor o igual a cero")
    @ApiResponse(responseCode = "402", description = "Pago denegado por la pasarela: no se registro nada")
    @ApiResponse(responseCode = "404", description = "Cliente, evento o sector inexistente")
    @ApiResponse(responseCode = "409", description = "Los lugares seleccionados ya no se encuentran disponibles")
    @PostMapping
    public ResponseEntity<IniciarPagoResponse> pagar(@Valid @RequestBody IniciarPagoRequest request) {
        return ResponseEntity.status(201).body(pagoService.pagar(request));
    }
}