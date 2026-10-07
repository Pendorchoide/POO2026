package com.smartticket.domain.servicio;

import com.smartticket.domain.entity.Sector;
import com.smartticket.domain.excepcion.ConflictoDeEstadoException;
import com.smartticket.domain.excepcion.RecursoNoEncontradoException;
import com.smartticket.domain.repository.SectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SectorService {

    private static final String RECURSO = "Sector";

    private final SectorRepository sectorRepository;

    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @Transactional(readOnly = true)
    public List<Sector> listar() {
        return sectorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Sector> listarPorLugar(Long lugarId) {
        return sectorRepository.findByLugarId(lugarId);
    }

    @Transactional(readOnly = true)
    public Sector buscarPorId(Long id) {
        return sectorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, id));
    }

    @Transactional
    public Sector crear(Sector sector) {
        sector.setId(null);
        return sectorRepository.save(sector);
    }

    @Transactional
    public Sector actualizar(Long id, Sector datos) {
        Sector existente = buscarPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setCapacidadMaxima(datos.getCapacidadMaxima());
        existente.setPrecioReferencia(datos.getPrecioReferencia());
        existente.setLugar(datos.getLugar());
        return sectorRepository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        Sector existente = buscarPorId(id);
        if (!existente.getPrecios().isEmpty()) {
            throw new ConflictoDeEstadoException(
                    "No se puede eliminar el Sector " + id + ": tiene precios de evento asociados");
        }
        sectorRepository.delete(existente);
    }
}