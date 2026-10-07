package com.smartticket.domain.servicio;

import com.smartticket.domain.entity.Lugar;
import com.smartticket.domain.excepcion.RecursoNoEncontradoException;
import com.smartticket.domain.repository.LugarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LugarService {

    private static final String RECURSO = "Lugar";

    private final LugarRepository lugarRepository;

    public LugarService(LugarRepository lugarRepository) {
        this.lugarRepository = lugarRepository;
    }

    @Transactional(readOnly = true)
    public List<Lugar> listar() {
        return lugarRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Lugar buscarPorId(Long id) {
        return lugarRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, id));
    }

    @Transactional
    public Lugar crear(Lugar lugar) {
        lugar.setId(null);
        return lugarRepository.save(lugar);
    }

    @Transactional
    public Lugar actualizar(Long id, Lugar datos) {
        Lugar existente = buscarPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setDireccion(datos.getDireccion());
        existente.setCiudad(datos.getCiudad());
        return lugarRepository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        Lugar existente = buscarPorId(id);
        if (!existente.getSectores().isEmpty() || !existente.getEventos().isEmpty()) {
            throw new com.smartticket.domain.excepcion.ConflictoDeEstadoException(
                    "No se puede eliminar el Lugar " + id + ": tiene sectores o eventos asociados");
        }
        lugarRepository.delete(existente);
    }
}