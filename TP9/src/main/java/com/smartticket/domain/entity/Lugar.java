package com.smartticket.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Recinto fisico donde se realiza un Evento (estadio, teatro, anfiteatro).
 * No define precios ni capacidad: la capacidad es de cada Sector.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lugares")
public class Lugar extends BaseEntity {

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(length = 200)
    private String direccion;

    @Column(length = 80)
    private String ciudad;

    /** Un Lugar tiene multiples Sectores. Capacidad total = suma de capacidades. */
    @OneToMany(mappedBy = "lugar")
    private List<Sector> sectores = new ArrayList<>();

    /** Un Lugar hosts multiples Eventos. */
    @OneToMany(mappedBy = "lugar")
    private List<Evento> eventos = new ArrayList<>();
}