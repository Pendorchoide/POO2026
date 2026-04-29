package TP3.Ejercicio2;

public class Empleado {
    private String nombre;
    private int dni;
    private float sueldo;

    public Empleado(String nombre, int dni, float sueldo) {
        this.nombre = nombre;
        this.dni = dni;
        establecerSueldo(sueldo);
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public float getSueldo() {
        return sueldo;
    }

    private void establecerSueldo(float sueldo) {
        final float SALARIO_MINIMO = 300.00f;
        this.sueldo = sueldo < SALARIO_MINIMO ? SALARIO_MINIMO : sueldo;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", DNI: " + dni + ", Sueldo: " + sueldo;
    }
}
