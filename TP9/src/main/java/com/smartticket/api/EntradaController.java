package com.smartticket.api;

import com.smartticket.domain.entity.Entrada;
import com.smartticket.domain.servicio.EntradaService;
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
@RequestMapping("/api/v1/entradas")
@Tag(name = "Entradas", description = "Entradas generadas por evento y sector, con su codigo QR")
public class EntradaController {

    private final EntradaService entradaService;

    public EntradaController(EntradaService entradaService) {
        this.entradaService = entradaService;
    }

    @Operation(summary = "Crea una entrada",
            description = "El codigo QR debe ser unico. El estado inicial es DISPONIBLE.")
    @ApiResponse(responseCode = "201", description = "Entrada creada")
    @ApiResponse(responseCode = "400", description = "Validacion fallida")
    @ApiResponse(responseCode = "409", description = "Ya existe una entrada con ese QR")
    @PostMapping
    public ResponseEntity<Entrada> crear(@Valid @RequestBody Entrada entrada) {
        Entrada creada = entradaService.crear(entrada);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creada.getId())
                .toUri();
        return ResponseEntity.created(location).body(creada);
    }

    @Operation(summary = "Lista todas las entradas")
    @ApiResponse(responseCode = "200", description = "Listado completo")
    @GetMapping
    public List<Entrada> listar() {
        return entradaService.listar();
    }

    @Operation(summary = "Lista las entradas de un evento y sector")
    @ApiResponse(responseCode = "200", description = "Entradas filtradas")
    @GetMapping(params = {"eventoId", "sectorId"})
    public List<Entrada> listarPorEventoYSector(
            @Parameter(description = "Id del evento") @RequestParam Long eventoId,
            @Parameter(description = "Id del sector") @RequestParam Long sectorId) {
        return entradaService.listarPorEventoYSector(eventoId, sectorId);
    }

    @Operation(summary = "Busca una entrada por id")
    @ApiResponse(responseCode = "200", description = "Entrada encontrada")
    @ApiResponse(responseCode = "404", description = "Entrada inexistente")
    @GetMapping("/{id}")
    public Entrada obtener(@PathVariable Long id) {
        return entradaService.buscarPorId(id);
    }

    @Operation(summary = "Busca una entrada por su codigo QR")
    @ApiResponse(responseCode = "200", description = "Entrada encontrada")
    @ApiResponse(responseCode = "404", description = "QR inexistente")
    @GetMapping("/qr/{qrCode}")
    public Entrada obtenerPorQr(@PathVariable String qrCode) {
        return entradaService.buscarPorQr(qrCode);
    }

    @Operation(summary = "Cantidad de entradas DISPONIBLES de un evento y sector")
    @ApiResponse(responseCode = "200", description = "Disponibilidad del sector")
    @GetMapping("/disponibles")
    public long disponibles(
            @RequestParam Long eventoId,
            @RequestParam Long sectorId) {
        return entradaService.contarDisponibles(eventoId, sectorId);
    }

    @Operation(summary = "Actualiza una entrada")
    @ApiResponse(responseCode = "200", description = "Entrada actualizada")
    @ApiResponse(responseCode = "404", description = "Entrada inexistente")
    @PutMapping("/{id}")
    public Entrada actualizar(@PathVariable Long id, @Valid @RequestBody Entrada datos) {
        return entradaService.actualizar(id, datos);
    }

    @Operation(summary = "Elimina una entrada")
    @ApiResponse(responseCode = "204", description = "Entrada eliminada")
    @ApiResponse(responseCode = "409", description = "Solo se pueden eliminar entradas DISPONIBLES")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        entradaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}