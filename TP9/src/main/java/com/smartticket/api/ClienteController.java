package com.smartticket.api;

import com.smartticket.domain.entity.Cliente;
import com.smartticket.domain.servicio.ClienteService;
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
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "Compradores de entradas")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Crea un cliente", description = "El email debe ser unico.")
    @ApiResponse(responseCode = "201", description = "Cliente creado")
    @ApiResponse(responseCode = "400", description = "Validacion fallida")
    @ApiResponse(responseCode = "409", description = "Email ya registrado")
    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente cliente) {
        Cliente creado = clienteService.crear(cliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();
        return ResponseEntity.created(location).body(creado);
    }

    @Operation(summary = "Lista todos los clientes")
    @ApiResponse(responseCode = "200", description = "Listado completo")
    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listar();
    }

    @Operation(summary = "Busca un cliente por id")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente inexistente")
    @GetMapping("/{id}")
    public Cliente obtener(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @Operation(summary = "Busca un cliente por email")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Email inexistente")
    @GetMapping(params = "email")
    public Cliente obtenerPorEmail(@Parameter(description = "Email a buscar") @RequestParam String email) {
        return clienteService.buscarPorEmail(email);
    }

    @Operation(summary = "Actualiza un cliente")
    @ApiResponse(responseCode = "200", description = "Cliente actualizado")
    @ApiResponse(responseCode = "404", description = "Cliente inexistente")
    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Long id, @Valid @RequestBody Cliente datos) {
        return clienteService.actualizar(id, datos);
    }

    @Operation(summary = "Elimina un cliente")
    @ApiResponse(responseCode = "204", description = "Cliente eliminado")
    @ApiResponse(responseCode = "409", description = "Tiene ventas asociadas")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}