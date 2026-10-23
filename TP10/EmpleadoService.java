import java.util.ArrayList;
import java.util.List;

class EmpleadoService {
    private static List<Empleado> empleados = new ArrayList<>();
    private static int proximoLegajo = 1;

    public static Boolean agregar(Empleado empleado) {
        empleado.setLegajo(proximoLegajo++);
        return empleados.add(empleado);
    }

    public static Empleado mayorSueldo() {
        // Lógica omitida para brevedad
        return empleados.get(0);
    }

    public static Double sueldoPromedio() {
        return 0.0; // Lógica omitida
    }

    public static void eliminar(String nombre) {
        empleados.removeIf(e -> e.getNombre().equals(nombre));
    }
}