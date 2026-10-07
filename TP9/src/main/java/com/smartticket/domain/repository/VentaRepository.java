package com.smartticket.domain.repository;

import com.smartticket.domain.entity.Venta;
import com.smartticket.domain.enumeracion.EstadoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    Optional<Venta> findByCodigo(String codigo);

    List<Venta> findByClienteId(Long clienteId);

    List<Venta> findByEstado(EstadoVenta estado);
}