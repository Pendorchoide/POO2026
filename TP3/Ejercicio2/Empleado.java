package TP3.Ejercicio2;

public class Empleado {
    private String nombre;
    private int dni;
    private float sueldo;

    Empleado(String nombre, int dni, float sueldo){
        this.nombre = nombre;
        this.dni = dni;
        establecerSueldo(sueldo);
    }

    public float getSueldo(){
        return this.sueldo;
    }

        public float getDNI(){
        return this.dni;
    }

    private void establecerSueldo(float sueldo){
        final float SALARIO_MINIMO = 300.00f;

        if (sueldo < SALARIO_MINIMO) {
            this.sueldo = SALARIO_MINIMO;
        }
        else{
            this.sueldo = sueldo;
        }
    }

    @Override
    public String toString() {
        return "Nombre: "+ this.nombre + "\nDNI: " + this.dni + "\nSueldo: " + this.sueldo;
    }
}
