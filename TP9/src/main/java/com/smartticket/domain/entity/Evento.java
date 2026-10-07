package com.smartticket.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Recital, partido, obra, etc. Se realiza en un unico Lugar.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "eventos")
public class Evento extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 600)
    private String descripcion;

    @Column(name = "fecha_evento", nullable = false)
    private LocalDate fechaEvento;

    /** Un Evento se lleva a cabo en un solo Lugar. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lugar_id", nullable = false)
    private Lugar lugar;

    /**
     * Precio definido por la productora para cada sector DISPONIBLE de este Lugar,
     * para este Evento en particular. Un precio por par (evento, sector).
     */
    @OneToMany(mappedBy = "evento")
    private List<PrecioEvento> precios = new ArrayList<>();

    /** Entradas generadas al crear el Evento, segun la capacidad de cada Sector. */
    @OneToMany(mappedBy = "evento")
    private List<Entrada> entradas = new ArrayList<>();
}