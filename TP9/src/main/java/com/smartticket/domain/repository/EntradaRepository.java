package com.smartticket.domain.repository;

import com.smartticket.domain.entity.Entrada;
import com.smartticket.domain.enumeracion.EstadoTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {

    Optional<Entrada> findByQrCode(String qrCode);

    List<Entrada> findByEventoIdAndSectorId(Long eventoId, Long sectorId);

    long countByEventoIdAndSectorIdAndEstado(Long eventoId, Long sectorId, EstadoTicket estado);
}