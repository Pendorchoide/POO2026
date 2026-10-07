package com.smartticket.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @NotBlank(message = "el nombre del sector es obligatorio")
    @Column(nullable = false, length = 80)
    private String nombre;

    /** Capacidad maxima fisica del sector. No depende del Evento. */
    @NotNull(message = "la capacidad maxima es obligatoria")
    @Min(value = 1, message = "la capacidad maxima debe ser mayor a 0")
    @Column(name = "capacidad_maxima", nullable = false)
    private Integer capacidadMaxima;

    @NotNull(message = "el lugar es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lugar_id", nullable = false)
    private Lugar lugar;

    /**
     * Precio de referencia actual del sector.
     * NO es el precio de venta: el precio real se fija por Evento en {@link PrecioEvento}.
     */
    @Column(precision = 12, scale = 2)
    private BigDecimal precioReferencia;

    /**
     * Precios fijados por cada productora para este sector.
     * {@code @JsonIgnore} corta el ciclo Sector <-> PrecioEvento (evita recursion infinita en JSON).
     */
    @JsonIgnore
    @OneToMany(mappedBy = "sector")
    private List<PrecioEvento> precios = new ArrayList<>();
}