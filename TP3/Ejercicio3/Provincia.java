package TP3.Ejercicio3;

public class Provincia {
    private String nombre;

    public Provincia(String nombre, int habitantes) {
        this.nombre = nombre;

    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Provincia: " + nombre;
    }
}
