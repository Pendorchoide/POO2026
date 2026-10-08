package com.smartticket.domain.servicio;

import com.smartticket.api.dto.PrecioEventoRequest;
import com.smartticket.domain.entity.Evento;
import com.smartticket.domain.entity.PrecioEvento;
import com.smartticket.domain.entity.Sector;
import com.smartticket.domain.excepcion.ConflictoDeEstadoException;
import com.smartticket.domain.excepcion.RecursoNoEncontradoException;
import com.smartticket.domain.repository.EventoRepository;
import com.smartticket.domain.repository.PrecioEventoRepository;
import com.smartticket.domain.repository.SectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Alta de precios por (evento, sector). Sin esto, cotizar no tiene con que calcular:
 * el precio no vive en Sector porque cambia de evento en evento.
 */
@Service
public class PrecioEventoService {

    private static final String RECURSO = "Precio";

    private final PrecioEventoRepository precioEventoRepository;
    private final EventoRepository eventoRepository;
    private final SectorRepository sectorRepository;

    public PrecioEventoService(PrecioEventoRepository precioEventoRepository,
                               EventoRepository eventoRepository,
                               SectorRepository sectorRepository) {
        this.precioEventoRepository = precioEventoRepository;
        this.eventoRepository = eventoRepository;
        this.sectorRepository = sectorRepository;
    }

    @Transactional(readOnly = true)
    public List<PrecioEvento> listarPorEvento(Long eventoId) {
        return precioEventoRepository.findByEventoId(eventoId);
    }

    @Transactional(readOnly = true)
    public PrecioEvento buscarPorId(Long id) {
        return precioEventoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, id));
    }

    @Transactional
    public PrecioEvento crear(PrecioEventoRequest request) {
        precioEventoRepository.findByEventoIdAndSectorId(request.eventoId(), request.sectorId())
                .ifPresent(precio -> {
                    throw new ConflictoDeEstadoException(
                            "Ya existe un precio para el sector " + request.sectorId()
                                    + " en el evento " + request.eventoId());
                });

        Evento evento = eventoRepository.findById(request.eventoId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Evento", request.eventoId()));
        Sector sector = sectorRepository.findById(request.sectorId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Sector", request.sectorId()));
        if (!sector.getLugar().getId().equals(evento.getLugar().getId())) {
            throw new RecursoNoEncontradoException(
                    "El sector " + sector.getId() + " no pertenece al lugar del evento " + evento.getId());
        }

        PrecioEvento precio = new PrecioEvento();
        precio.setPrecio(request.precio());
        precio.setEvento(evento);
        precio.setSector(sector);
        return precioEventoRepository.save(precio);
    }

    @Transactional
    public PrecioEvento actualizar(Long id, PrecioEventoRequest request) {
        PrecioEvento existente = buscarPorId(id);
        existente.setPrecio(request.precio());
        return precioEventoRepository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        precioEventoRepository.delete(buscarPorId(id));
    }
}