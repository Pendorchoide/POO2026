package com.smartticket.api;

import com.smartticket.api.dto.CotizacionRequest;
import com.smartticket.api.dto.CotizacionResponse;
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
 * Estructura lista: contrato HTTP definido, logica pendiente de implementacion.
 */
@RestController
@RequestMapping("/api/v1/cotizaciones")
@Tag(name = "Cotizaciones", description = "Calculo de precio y disponibilidad de entradas")
public class CotizacionController {

    @Operation(
            summary = "Cotiza entradas y las reserva",
            description = "Toma un evento y las lineas por sector, valida disponibilidad contra la "
                    + "capacidad del sector, calcula el total con el precio del evento y deja la venta en PENDIENTE.")
    @ApiResponse(responseCode = "201", description = "Cotizacion creada con las entradas reservadas")
    @ApiResponse(responseCode = "404", description = "Evento o sector inexistente")
    @ApiResponse(responseCode = "409", description = "No hay entradas disponibles para alguna linea")
    @PostMapping
    public ResponseEntity<CotizacionResponse> cotizar(@Valid @RequestBody CotizacionRequest request) {
        // TODO: implementar
        return ResponseEntity.status(501).build();
    }
}