package com.smartticket.api;

import com.smartticket.domain.entity.Venta;
import com.smartticket.domain.enumeracion.EstadoVenta;
import com.smartticket.domain.servicio.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping("/api/v1/ventas")
@Tag(name = "Ventas", description = "Transacciones de compra de entradas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @Operation(summary = "Crea una venta", description = "El codigo de venta debe ser unico y el estado inicial es PENDIENTE.")
    @ApiResponse(responseCode = "201", description = "Venta creada")
    @ApiResponse(responseCode = "400", description = "Validacion fallida")
    @ApiResponse(responseCode = "409", description = "Codigo ya usado")
    @PostMapping
    public ResponseEntity<Venta> crear(@Valid @RequestBody Venta venta) {
        Venta creada = ventaService.crear(venta);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creada.getId())
                .toUri();
        return ResponseEntity.created(location).body(creada);
    }

    @Operation(summary = "Lista todas las ventas")
    @ApiResponse(responseCode = "200", description = "Listado completo")
    @GetMapping
    public List<Venta> listar() {
        return ventaService.listar();
    }

    @Operation(summary = "Lista las ventas de un cliente")
    @ApiResponse(responseCode = "200", description = "Ventas del cliente")
    @ApiResponse(responseCode = "404", description = "Cliente inexistente")
    @GetMapping(params = "clienteId")
    public List<Venta> listarPorCliente(
            @Parameter(description = "Id del cliente") @RequestParam Long clienteId) {
        return ventaService.listarPorCliente(clienteId);
    }

    @Operation(summary = "Lista las ventas por estado")
    @ApiResponse(responseCode = "200", description = "Ventas filtradas por estado")
    @GetMapping(params = "estado")
    public List<Venta> listarPorEstado(
            @Parameter(description = "Estado de la venta") @RequestParam EstadoVenta estado) {
        return ventaService.listarPorEstado(estado);
    }

    @Operation(summary = "Busca una venta por id")
    @ApiResponse(responseCode = "200", description = "Venta encontrada")
    @ApiResponse(responseCode = "404", description = "Venta inexistente")
    @GetMapping("/{id}")
    public Venta obtener(@PathVariable Long id) {
        return ventaService.buscarPorId(id);
    }

    @Operation(summary = "Busca una venta por su codigo")
    @ApiResponse(responseCode = "200", description = "Venta encontrada")
    @ApiResponse(responseCode = "404", description = "Codigo inexistente")
    @GetMapping("/codigo/{codigo}")
    public Venta obtenerPorCodigo(@PathVariable String codigo) {
        return ventaService.buscarPorCodigo(codigo);
    }

    @Operation(summary = "Cierra la venta",
            description = "Transicion valida: PENDIENTE -> PAGADA o CANCELADA.")
    @ApiResponse(responseCode = "200", description = "Estado actualizado")
    @ApiResponse(responseCode = "409", description = "La venta ya no esta PENDIENTE")
    @PatchMapping("/{id}/estado")
    public Venta cambiarEstado(
            @PathVariable Long id,
            @Parameter(description = "Nuevo estado") @RequestParam EstadoVenta estado) {
        return ventaService.cambiarEstado(id, estado);
    }

    @Operation(summary = "Actualiza una venta")
    @ApiResponse(responseCode = "200", description = "Venta actualizada")
    @ApiResponse(responseCode = "404", description = "Venta inexistente")
    @PutMapping("/{id}")
    public Venta actualizar(@PathVariable Long id, @Valid @RequestBody Venta datos) {
        return ventaService.actualizar(id, datos);
    }

    @Operation(summary = "Elimina una venta")
    @ApiResponse(responseCode = "204", description = "Venta eliminada")
    @ApiResponse(responseCode = "409", description = "Solo se pueden eliminar ventas CANCELADAS")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ventaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}