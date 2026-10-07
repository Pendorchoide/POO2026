package com.smartticket.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartticket.domain.enumeracion.EstadoVenta;
import com.smartticket.domain.enumeracion.MedioPago;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Transaccion iniciada por un Cliente. Agrupa las entradas seleccionadas y
 * maneja su propio estado de forma independiente del estado de cada Entrada.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ventas")
public class Venta extends BaseEntity {

    /** Identificador legible por el cliente, distinto del id interno. */
    @NotBlank(message = "el codigo de venta es obligatorio")
    @Column(name = "codigo", nullable = false, unique = true, length = 32)
    private String codigo;

    @NotNull(message = "el estado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoVenta estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "medio_pago", length = 30)
    private MedioPago medioPago;

    @Column(name = "fecha_cierre")
    private Instant fechaCierre;

    /** Un Cliente puede iniciar multiples Ventas. */
    @NotNull(message = "el cliente es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    /**
     * Entradas agrupadas por esta Venta.
     * {@code @JsonIgnore} corta el ciclo Venta <-> Entrada y evita que al listar
     * una venta el JSON arrastre todas sus entradas con su venta otra vez.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "venta")
    private List<Entrada> entradas = new ArrayList<>();
}