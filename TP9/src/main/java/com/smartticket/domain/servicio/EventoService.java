package com.smartticket.domain.servicio;

import com.smartticket.domain.entity.Evento;
import com.smartticket.domain.excepcion.ConflictoDeEstadoException;
import com.smartticket.domain.excepcion.RecursoNoEncontradoException;
import com.smartticket.domain.repository.EventoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoService {

    private static final String RECURSO = "Evento";

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Transactional(readOnly = true)
    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Evento> listarPorLugar(Long lugarId) {
        return eventoRepository.findByLugarId(lugarId);
    }

    @Transactional(readOnly = true)
    public Evento buscarPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, id));
    }

    @Transactional
    public Evento crear(Evento evento) {
        evento.setId(null);
        if (evento.getFechaEvento() == null) {
            throw new ConflictoDeEstadoException("El evento no tiene fecha");
        }
        return eventoRepository.save(evento);
    }

    @Transactional
    public Evento actualizar(Long id, Evento datos) {
        Evento existente = buscarPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setFechaEvento(datos.getFechaEvento());
        existente.setLugar(datos.getLugar());
        return eventoRepository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        Evento existente = buscarPorId(id);
        if (!existente.getEntradas().isEmpty()) {
            throw new ConflictoDeEstadoException(
                    "No se puede eliminar el Evento " + id + ": ya tiene entradas generadas");
        }
        eventoRepository.delete(existente);
    }
}