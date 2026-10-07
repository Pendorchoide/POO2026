package com.smartticket.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "el nombre del lugar es obligatorio")
    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(length = 200)
    private String direccion;

    @Column(length = 80)
    private String ciudad;

    /**
     * Un Lugar tiene multiples Sectores. Capacidad total = suma de capacidades.
     * {@code @JsonIgnore} corta el ciclo Lugar <-> Sector al serializar.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "lugar")
    private List<Sector> sectores = new ArrayList<>();

    /** Un Lugar aloja multiples Eventos. */
    @JsonIgnore
    @OneToMany(mappedBy = "lugar")
    private List<Evento> eventos = new ArrayList<>();
}