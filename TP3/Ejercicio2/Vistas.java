package TP3.Ejercicio2;

import java.util.ArrayList;

public class Vistas {
    public static String obtenerMenu() {
        return "1. Registrar Empleado\n"
             + "2. Listar Empleados\n"
             + "3. Quitar Empleado\n"
             + "4. Promedio Sueldo\n"
             + "5. Empleado que mas gana\n"
             + "6. Salir";
    }

    public static String formatearEmpleado(Empleado empleado) {
        if (empleado == null) {
            return "Empleado no encontrado.";
        }
        return "\n--------------------\n" + empleado.toString() + "\n--------------------\n";
    }

    public static String formatearListaEmpleados(ArrayList<Empleado> empleados) {
        if (empleados == null || empleados.isEmpty()) {
            return "No hay empleados registrados.";
        }

        StringBuilder builder = new StringBuilder();
        for (Empleado empleado : empleados) {
            builder.append("\n--------------------\n").append(empleado.toString()).append("\n--------------------\n");
        }
        return builder.toString();
    }
}
