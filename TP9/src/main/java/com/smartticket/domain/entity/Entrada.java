package com.smartticket.domain.entity;

import com.smartticket.domain.enumeracion.EstadoTicket;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 * Entrada fisica que ya existe en el sistema.
 * Se genera al crear el Evento, tantas como la capacidad del Sector, y trae su codigo QR.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "entradas")
public class Entrada extends BaseEntity {

    /** Codigo QR unico que se escanea en el acceso. */
    @Column(name = "qr_code", nullable = false, unique = true, length = 64)
    private String qrCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoTicket estado;

    /** Instante exacto del ingreso al lugar. Nulo mientras no se haya escaneado el QR. */
    @Column(name = "fecha_ingreso")
    private Instant fechaIngreso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    /** Sector que le corresponde: permite contar y rastrear por sector. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sector_id", nullable = false)
    private Sector sector;

    /** Venta a la que quedo asignada. Nulo mientras la entrada este DISPONIBLE. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id")
    private Venta venta;
}