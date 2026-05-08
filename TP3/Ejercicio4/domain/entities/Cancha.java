package TP3.Ejercicio4.domain.entities;

public class Cancha {
    private final int id;

    private Cancha(int id) {
        this.id = id;
    }

    public static Cancha create(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a 0");
        }
        return new Cancha(id);
    }

    public int getId() {
        return id;
    }
}