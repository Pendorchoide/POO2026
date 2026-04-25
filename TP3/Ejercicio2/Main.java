package TP3.Ejercicio2;

public class Main {
    public static void main(String[] args) {
        GestorEmpleado gestor = new GestorEmpleado();
        Vistas vistas = new Vistas(gestor);

        vistas.menu();
    }
}
