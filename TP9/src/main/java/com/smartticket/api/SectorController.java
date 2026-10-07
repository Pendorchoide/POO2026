package com.smartticket.api;

import com.smartticket.domain.entity.Sector;
import com.smartticket.domain.servicio.SectorService;
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
@RequestMapping("/api/v1/sectores")
@Tag(name = "Sectores", description = "Divisiones de un lugar y su capacidad maxima")
public class SectorController {

    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @Operation(summary = "Crea un sector", description = "Debe referenciar un lugar existente.")
    @ApiResponse(responseCode = "201", description = "Sector creado")
    @ApiResponse(responseCode = "400", description = "Validacion fallida")
    @PostMapping
    public ResponseEntity<Sector> crear(@Valid @RequestBody Sector sector) {
        Sector creado = sectorService.crear(sector);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();
        return ResponseEntity.created(location).body(creado);
    }

    @Operation(summary = "Lista todos los sectores")
    @ApiResponse(responseCode = "200", description = "Listado completo")
    @GetMapping
    public List<Sector> listar() {
        return sectorService.listar();
    }

    @Operation(summary = "Lista los sectores de un lugar")
    @ApiResponse(responseCode = "200", description = "Sectores del lugar")
    @ApiResponse(responseCode = "404", description = "Lugar inexistente")
    @GetMapping(params = "lugarId")
    public List<Sector> listarPorLugar(@Parameter(description = "Id del lugar") @RequestParam Long lugarId) {
        return sectorService.listarPorLugar(lugarId);
    }

    @Operation(summary = "Busca un sector por id")
    @ApiResponse(responseCode = "200", description = "Sector encontrado")
    @ApiResponse(responseCode = "404", description = "Sector inexistente")
    @GetMapping("/{id}")
    public Sector obtener(@PathVariable Long id) {
        return sectorService.buscarPorId(id);
    }

    @Operation(summary = "Actualiza un sector")
    @ApiResponse(responseCode = "200", description = "Sector actualizado")
    @ApiResponse(responseCode = "404", description = "Sector inexistente")
    @PutMapping("/{id}")
    public Sector actualizar(@PathVariable Long id, @Valid @RequestBody Sector datos) {
        return sectorService.actualizar(id, datos);
    }

    @Operation(summary = "Elimina un sector")
    @ApiResponse(responseCode = "204", description = "Sector eliminado")
    @ApiResponse(responseCode = "409", description = "Tiene precios de evento asociados")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        sectorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}