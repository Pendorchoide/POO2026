package com.smartticket.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Porcion fisica del Lugar (Campo, Platea Alta, Platea Baja, VIP).
 * Es el unico que conoce y limita su capacidad maxima.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sectores")
public class Sector extends BaseEntity {

    @Column(nullable = false, length = 80)
    private String nombre;

    /** Capacidad maxima fisica del sector. No depende del Evento. */
    @Column(name = "capacidad_maxima", nullable = false)
    private Integer capacidadMaxima;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lugar_id", nullable = false)
    private Lugar lugar;

    /**
     * Precio de referencia actual del sector.
     * NO es el precio de venta: el precio real se fija por Evento en {@link PrecioEvento}.
     */
    @Column(precision = 12, scale = 2)
    private BigDecimal precioReferencia;
}