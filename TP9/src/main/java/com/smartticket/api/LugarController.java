package com.smartticket.api;

import com.smartticket.domain.entity.Lugar;
import com.smartticket.domain.servicio.LugarService;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lugares")
@Tag(name = "Lugares", description = "Recintos donde se realizan los eventos")
public class LugarController {

    private final LugarService lugarService;

    public LugarController(LugarService lugarService) {
        this.lugarService = lugarService;
    }

    @Operation(summary = "Crea un lugar", description = "Devuelve 201 y la cabecera Location con la URL del recurso.")
    @ApiResponse(responseCode = "201", description = "Lugar creado")
    @ApiResponse(responseCode = "400", description = "Validacion fallida")
    @PostMapping
    public ResponseEntity<Lugar> crear(@Valid @RequestBody Lugar lugar) {
        Lugar creado = lugarService.crear(lugar);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();
        return ResponseEntity.created(location).body(creado);
    }

    @Operation(summary = "Lista todos los lugares")
    @ApiResponse(responseCode = "200", description = "Listado completo")
    @GetMapping
    public List<Lugar> listar() {
        return lugarService.listar();
    }

    @Operation(summary = "Busca un lugar por id")
    @ApiResponse(responseCode = "200", description = "Lugar encontrado")
    @ApiResponse(responseCode = "404", description = "Lugar inexistente")
    @GetMapping("/{id}")
    public Lugar obtener(@PathVariable Long id) {
        return lugarService.buscarPorId(id);
    }

    @Operation(summary = "Actualiza un lugar")
    @ApiResponse(responseCode = "200", description = "Lugar actualizado")
    @ApiResponse(responseCode = "404", description = "Lugar inexistente")
    @PutMapping("/{id}")
    public Lugar actualizar(@PathVariable Long id, @Valid @RequestBody Lugar datos) {
        return lugarService.actualizar(id, datos);
    }

    @Operation(summary = "Elimina un lugar")
    @ApiResponse(responseCode = "204", description = "Lugar eliminado")
    @ApiResponse(responseCode = "409", description = "Tiene sectores o eventos asociados")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        lugarService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}