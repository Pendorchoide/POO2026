package com.smartticket.api;

import com.smartticket.api.dto.CotizacionRequest;
import com.smartticket.api.dto.CotizacionResponse;
import com.smartticket.domain.servicio.CotizacionService;
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
 * CU 01 - Cotizacion de entradas.
 *
 * <p>SRP: el controller SOLO recibe la peticion HTTP, valida el formato con
 * {@code @Valid} y delega. No calcula nada, no consulta stock, no tiene {@code if}.</p>
 */
@RestController
@RequestMapping("/api/v1/cotizaciones")
@Tag(name = "Cotizaciones", description = "Calculo de precio y disponibilidad de entradas")
public class CotizacionController {

    private final CotizacionService cotizacionService;

    public CotizacionController(CotizacionService cotizacionService) {
        this.cotizacionService = cotizacionService;
    }

    @Operation(
            summary = "Cotiza entradas (solo lectura)",
            description = "Por cada linea valida que la cantidad sea mayor a cero y que haya entradas "
                    + "DISPONIBLES en el sector, y calcula el total con el precio fijado para ese "
                    + "evento. No reserva ni crea venta: eso pasa al pagar.")
    @ApiResponse(responseCode = "200", description = "Cotizacion calculada con el costo total exacto")
    @ApiResponse(responseCode = "400", description = "Cantidad de entradas menor o igual a cero")
    @ApiResponse(responseCode = "404", description = "Evento, sector o precio inexistente")
    @ApiResponse(responseCode = "409", description = "Capacidad insuficiente o entradas no disponibles")
    @PostMapping
    public ResponseEntity<CotizacionResponse> cotizar(@Valid @RequestBody CotizacionRequest request) {
        return ResponseEntity.ok(cotizacionService.cotizar(request));
    }
}