package TP3.Ejercicio2;

public class Empleado {
    private String nombre;
    private int dni;
    private double sueldo;
    private boolean ajustado;

    public Empleado(String nombre, int dni, double sueldo) {
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

    public double getSueldo() {
        return sueldo;
    }

    public boolean fueAjustado() {
        return ajustado;
    }

    private void establecerSueldo(double sueldo) {
        final double SALARIO_MINIMO = 300.00;
        if (sueldo < SALARIO_MINIMO) {
            this.sueldo = SALARIO_MINIMO;
            this.ajustado = true;
        } else {
            this.sueldo = sueldo;
            this.ajustado = false;
        }
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", DNI: " + dni + ", Sueldo: " + sueldo;
    }
}
