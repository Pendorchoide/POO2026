package com.smartticket.domain.repository;

import com.smartticket.domain.entity.Entrada;
import com.smartticket.domain.enumeracion.EstadoTicket;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {

    Optional<Entrada> findByQrCode(String qrCode);

    List<Entrada> findByEventoIdAndSectorId(Long eventoId, Long sectorId);

    long countByEventoIdAndSectorIdAndEstado(Long eventoId, Long sectorId, EstadoTicket estado);

    /**
     * Trae las entradas DISPONIBLES bloqueandolas hasta que cierre la transaccion
     * (SELECT ... FOR UPDATE). Es lo que hace que dos pagos simultaneos por las mismas
     * butacas no se pisen: el segundo espera a que el primero libere el lock.
     * El orden por id es obligatorio: evita deadlock entre transacciones concurrentes.
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select e
              from Entrada e
             where e.evento.id = :eventoId
               and e.sector.id = :sectorId
               and e.estado = :estado
             order by e.id
            """)
    List<Entrada> bloquearPorEventoSectorYEstado(
            @Param("eventoId") Long eventoId,
            @Param("sectorId") Long sectorId,
            @Param("estado") EstadoTicket estado);
}