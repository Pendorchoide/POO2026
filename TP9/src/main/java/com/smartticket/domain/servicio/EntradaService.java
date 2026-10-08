package com.smartticket.domain.servicio;

import com.smartticket.domain.entity.Entrada;
import com.smartticket.domain.enumeracion.EstadoTicket;
import com.smartticket.domain.excepcion.ConflictoDeEstadoException;
import com.smartticket.domain.excepcion.RecursoNoEncontradoException;
import com.smartticket.domain.repository.EntradaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntradaService {

    private static final String RECURSO = "Entrada";

    private final EntradaRepository entradaRepository;

    public EntradaService(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }

    @Transactional(readOnly = true)
    public List<Entrada> listar() {
        return entradaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Entrada buscarPorId(Long id) {
        return entradaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, id));
    }

    @Transactional(readOnly = true)
    public Entrada buscarPorQr(String qrCode) {
        return entradaRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new RecursoNoEncontradoException("Entrada con QR " + qrCode + " no existe"));
    }

    @Transactional(readOnly = true)
    public List<Entrada> listarPorEventoYSector(Long eventoId, Long sectorId) {
        return entradaRepository.findByEventoIdAndSectorId(eventoId, sectorId);
    }

    @Transactional(readOnly = true)
    public long contarDisponibles(Long eventoId, Long sectorId) {
        return entradaRepository.countByEventoIdAndSectorIdAndEstado(
                eventoId, sectorId, EstadoTicket.DISPONIBLE);
    }

    @Transactional
    public Entrada crear(Entrada entrada) {
        entrada.setId(null);
        if (entradaRepository.findByQrCode(entrada.getQrCode()).isPresent()) {
            throw new ConflictoDeEstadoException("Ya existe una entrada con el QR " + entrada.getQrCode());
        }
        entrada.setFechaIngreso(null);
        return entradaRepository.save(entrada);
    }

    @Transactional
    public Entrada actualizar(Long id, Entrada datos) {
        Entrada existente = buscarPorId(id);
        existente.setEstado(datos.getEstado());
        existente.setFechaIngreso(datos.getFechaIngreso());
        existente.setEvento(datos.getEvento());
        existente.setSector(datos.getSector());
        existente.setVenta(datos.getVenta());
        return entradaRepository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        Entrada entrada = buscarPorId(id);
        if (entrada.getEstado() != EstadoTicket.DISPONIBLE) {
            throw new ConflictoDeEstadoException(
                    "No se puede eliminar la Entrada " + id + " en estado " + entrada.getEstado());
        }
        entradaRepository.delete(entrada);
    }
}