package com.smartticket.domain.servicio;

import com.smartticket.domain.entity.Cliente;
import com.smartticket.domain.excepcion.ConflictoDeEstadoException;
import com.smartticket.domain.excepcion.RecursoNoEncontradoException;
import com.smartticket.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    private static final String RECURSO = "Cliente";

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional(readOnly = true)
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, id));
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente con email " + email + " no existe"));
    }

    @Transactional
    public Cliente crear(Cliente cliente) {
        cliente.setId(null);
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new ConflictoDeEstadoException("Ya existe un cliente con el email " + cliente.getEmail());
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente actualizar(Long id, Cliente datos) {
        Cliente existente = buscarPorId(id);
        clienteRepository.findByEmail(datos.getEmail())
                .filter(otro -> !otro.getId().equals(id))
                .ifPresent(otro -> {
                    throw new ConflictoDeEstadoException("Ya existe un cliente con el email " + datos.getEmail());
                });
        existente.setNombre(datos.getNombre());
        existente.setApellido(datos.getApellido());
        existente.setEmail(datos.getEmail());
        existente.setTelefono(datos.getTelefono());
        existente.setDocumento(datos.getDocumento());
        existente.setFechaNacimiento(datos.getFechaNacimiento());
        return clienteRepository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        Cliente existente = buscarPorId(id);
        if (!existente.getVentas().isEmpty()) {
            throw new ConflictoDeEstadoException(
                    "No se puede eliminar el Cliente " + id + ": tiene ventas asociadas");
        }
        clienteRepository.delete(existente);
    }
}