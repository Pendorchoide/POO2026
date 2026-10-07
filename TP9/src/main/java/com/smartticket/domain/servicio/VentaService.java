package com.smartticket.domain.servicio;

import com.smartticket.domain.entity.Venta;
import com.smartticket.domain.enumeracion.EstadoVenta;
import com.smartticket.domain.excepcion.ConflictoDeEstadoException;
import com.smartticket.domain.excepcion.RecursoNoEncontradoException;
import com.smartticket.domain.repository.VentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VentaService {

    private static final String RECURSO = "Venta";

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Transactional(readOnly = true)
    public List<Venta> listar() {
        return ventaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Venta> listarPorEstado(EstadoVenta estado) {
        return ventaRepository.findByEstado(estado);
    }

    @Transactional(readOnly = true)
    public List<Venta> listarPorCliente(Long clienteId) {
        return ventaRepository.findByClienteId(clienteId);
    }

    @Transactional(readOnly = true)
    public Venta buscarPorId(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, id));
    }

    @Transactional(readOnly = true)
    public Venta buscarPorCodigo(String codigo) {
        return ventaRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RecursoNoEncontradoException("Venta " + codigo + " no existe"));
    }

    @Transactional
    public Venta crear(Venta venta) {
        venta.setId(null);
        if (ventaRepository.findByCodigo(venta.getCodigo()).isPresent()) {
            throw new ConflictoDeEstadoException("Ya existe una venta con el codigo " + venta.getCodigo());
        }
        venta.setFechaCierre(null);
        return ventaRepository.save(venta);
    }

    @Transactional
    public Venta actualizar(Long id, Venta datos) {
        Venta existente = buscarPorId(id);
        existente.setCodigo(datos.getCodigo());
        existente.setEstado(datos.getEstado());
        existente.setMedioPago(datos.getMedioPago());
        existente.setFechaCierre(datos.getFechaCierre());
        existente.setCliente(datos.getCliente());
        return ventaRepository.save(existente);
    }

    /**
     * Transicion de estado valida de la venta: PENDIENTE -> PAGADA o CANCELADA.
     * PAGADA y CANCELADA son estados finales: ya no se tocan.
     */
    @Transactional
    public Venta cambiarEstado(Long id, EstadoVenta nuevoEstado) {
        Venta venta = buscarPorId(id);
        if (venta.getEstado() != EstadoVenta.PENDIENTE) {
            throw new ConflictoDeEstadoException(
                    "La venta " + id + " esta en estado " + venta.getEstado() + ": no se puede modificar");
        }
        venta.setEstado(nuevoEstado);
        if (nuevoEstado == EstadoVenta.PAGADA || nuevoEstado == EstadoVenta.CANCELADA) {
            venta.setFechaCierre(java.time.Instant.now());
        }
        return ventaRepository.save(venta);
    }

    @Transactional
    public void eliminar(Long id) {
        Venta venta = buscarPorId(id);
        if (venta.getEstado() != EstadoVenta.CANCELADA) {
            throw new ConflictoDeEstadoException(
                    "Solo se pueden eliminar ventas CANCELADAS, la venta " + id + " esta en " + venta.getEstado());
        }
        ventaRepository.delete(venta);
    }
}