package com.smartticket.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Precio que la productora define para UN sector en UN evento concreto.
 *
 * <p>Existe como entidad propia y no como atributo de Sector ni de Entrada porque:
 * el precio depende de la combinacion (evento, sector); un Sector reutiliza el mismo
 * precio en todos los eventos, y un Evento tiene un precio distinto por sector.
 * Meterlo en Entrada duplicaria el mismo valor N veces (una por cada entrada generada).
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "precios_evento",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_precio_evento_sector",
                columnNames = {"evento_id", "sector_id"}))
public class PrecioEvento extends BaseEntity {

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sector_id", nullable = false)
    private Sector sector;
}