package com.smartticket.domain.repository;

import com.smartticket.domain.entity.PrecioEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrecioEventoRepository extends JpaRepository<PrecioEvento, Long> {

    /** El precio que la productora fijo para UN sector dentro de UN evento. */
    Optional<PrecioEvento> findByEventoIdAndSectorId(Long eventoId, Long sectorId);

    List<PrecioEvento> findByEventoId(Long eventoId);
}