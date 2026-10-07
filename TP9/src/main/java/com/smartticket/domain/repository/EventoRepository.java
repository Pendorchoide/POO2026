package com.smartticket.domain.repository;

import com.smartticket.domain.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByLugarId(Long lugarId);

    List<Evento> findByFechaEventoBetween(LocalDate desde, LocalDate hasta);
}