package com.smartticket.api;

import com.smartticket.api.dto.PrecioEventoRequest;
import com.smartticket.domain.entity.PrecioEvento;
import com.smartticket.domain.servicio.PrecioEventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/precios-evento")
@Tag(name = "Precios por evento", description = "Precio fijado por la productora para un sector en un evento")
public class PrecioEventoController {

    private final PrecioEventoService precioEventoService;

    public PrecioEventoController(PrecioEventoService precioEventoService) {
        this.precioEventoService = precioEventoService;
    }

    @Operation(summary = "Fija el precio de un sector en un evento",
            description = "Es el dato que consume la cotizacion. Un solo precio por par (evento, sector).")
    @ApiResponse(responseCode = "201", description = "Precio creado")
    @ApiResponse(responseCode = "409", description = "Ya existe un precio para ese par")
    @PostMapping
    public ResponseEntity<PrecioEvento> crear(@Valid @RequestBody PrecioEventoRequest request) {
        PrecioEvento creado = precioEventoService.crear(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();
        return ResponseEntity.created(location).body(creado);
    }

    @Operation(summary = "Lista los precios de un evento")
    @ApiResponse(responseCode = "200", description = "Listado por evento")
    @GetMapping(params = "eventoId")
    public List<PrecioEvento> listarPorEvento(
            @Parameter(description = "Id del evento") @RequestParam Long eventoId) {
        return precioEventoService.listarPorEvento(eventoId);
    }

    @Operation(summary = "Busca un precio por id")
    @ApiResponse(responseCode = "200", description = "Precio encontrado")
    @ApiResponse(responseCode = "404", description = "Precio inexistente")
    @GetMapping("/{id}")
    public PrecioEvento obtener(@PathVariable Long id) {
        return precioEventoService.buscarPorId(id);
    }

    @Operation(summary = "Actualiza el monto de un precio")
    @ApiResponse(responseCode = "200", description = "Precio actualizado")
    @ApiResponse(responseCode = "404", description = "Precio inexistente")
    @PutMapping("/{id}")
    public PrecioEvento actualizar(@PathVariable Long id, @Valid @RequestBody PrecioEventoRequest request) {
        return precioEventoService.actualizar(id, request);
    }

    @Operation(summary = "Elimina un precio")
    @ApiResponse(responseCode = "204", description = "Precio eliminado")
    @ApiResponse(responseCode = "404", description = "Precio inexistente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        precioEventoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}