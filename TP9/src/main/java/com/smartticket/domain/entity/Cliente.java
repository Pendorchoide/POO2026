package com.smartticket.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Comprador. Inicia una Venta y es responsable del pago.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente extends BaseEntity {

    @NotBlank(message = "el nombre es obligatorio")
    @Column(nullable = false, length = 80)
    private String nombre;

    @NotBlank(message = "el apellido es obligatorio")
    @Column(nullable = false, length = 80)
    private String apellido;

    @NotBlank(message = "el email es obligatorio")
    @Email(message = "el email no tiene formato valido")
    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(length = 20)
    private String telefono;

    @Column(length = 20)
    private String documento;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    /** Historial de ventas del Cliente. */
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Venta> ventas = new ArrayList<>();
}