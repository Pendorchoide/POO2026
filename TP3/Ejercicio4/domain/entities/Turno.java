package TP3.Ejercicio4.domain.entities;

import TP3.Ejercicio4.domain.enums.Hora;

public class Turno {
    private final int id;
    private final String nombrePersona;
    private final int canchaId;
    private final Hora hour;

    private Turno(int id, String nombrePersona, int canchaId, Hora hour) {
        this.id = id;
        this.nombrePersona = nombrePersona;
        this.canchaId = canchaId;
        this.hour = hour;
    }

    public static Turno create(int id, String nombrePersona, int canchaId, Hora hour) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a 0");
        }
        if (nombrePersona == null || nombrePersona.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la persona no puede ser nulo o vacío");
        }
        if (canchaId <= 0) {
            throw new IllegalArgumentException("El ID de la cancha debe ser mayor a 0");
        }
        if (hour == null) {
            throw new IllegalArgumentException("La hora no puede ser nula");
        }
        return new Turno(id, nombrePersona, canchaId, hour);
    }

    public int getId() {
        return id;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public int getCanchaId() {
        return canchaId;
    }

    public Hora getHour() {
        return hour;
    }
}