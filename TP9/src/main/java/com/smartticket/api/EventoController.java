package com.smartticket.api;

import com.smartticket.domain.entity.Evento;
import com.smartticket.domain.servicio.EventoService;
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
@RequestMapping("/api/v1/eventos")
@Tag(name = "Eventos", description = "Recitales, partidos, obras y demas eventos programados")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Operation(summary = "Crea un evento",
            description = "Al crearlo se deberian generar las entradas segun la capacidad de cada sector.")
    @ApiResponse(responseCode = "201", description = "Evento creado")
    @ApiResponse(responseCode = "400", description = "Validacion fallida")
    @PostMapping
    public ResponseEntity<Evento> crear(@Valid @RequestBody Evento evento) {
        Evento creado = eventoService.crear(evento);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();
        return ResponseEntity.created(location).body(creado);
    }

    @Operation(summary = "Lista todos los eventos")
    @ApiResponse(responseCode = "200", description = "Listado completo")
    @GetMapping
    public List<Evento> listar() {
        return eventoService.listar();
    }

    @Operation(summary = "Lista los eventos de un lugar")
    @ApiResponse(responseCode = "200", description = "Eventos del lugar")
    @ApiResponse(responseCode = "404", description = "Lugar inexistente")
    @GetMapping(params = "lugarId")
    public List<Evento> listarPorLugar(@Parameter(description = "Id del lugar") @RequestParam Long lugarId) {
        return eventoService.listarPorLugar(lugarId);
    }

    @Operation(summary = "Busca un evento por id")
    @ApiResponse(responseCode = "200", description = "Evento encontrado")
    @ApiResponse(responseCode = "404", description = "Evento inexistente")
    @GetMapping("/{id}")
    public Evento obtener(@PathVariable Long id) {
        return eventoService.buscarPorId(id);
    }

    @Operation(summary = "Actualiza un evento")
    @ApiResponse(responseCode = "200", description = "Evento actualizado")
    @ApiResponse(responseCode = "404", description = "Evento inexistente")
    @PutMapping("/{id}")
    public Evento actualizar(@PathVariable Long id, @Valid @RequestBody Evento datos) {
        return eventoService.actualizar(id, datos);
    }

    @Operation(summary = "Elimina un evento")
    @ApiResponse(responseCode = "204", description = "Evento eliminado")
    @ApiResponse(responseCode = "409", description = "Ya tiene entradas generadas")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        eventoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}