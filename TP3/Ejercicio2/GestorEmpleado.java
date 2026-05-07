package TP3.Ejercicio2;

import java.util.ArrayList;

public class GestorEmpleado {
    private ArrayList<Empleado> empleados;

    public GestorEmpleado() {
        empleados = new ArrayList<>();
    }

    public void registrarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public boolean existeEmpleadoDNI(int dni) {
        return buscarEmpleadoDNI(dni) != null;
    }

    public void quitarEmpleado(int dni) {
        Empleado empleado = buscarEmpleadoDNI(dni);
        if (empleado != null) {
            empleados.remove(empleado);
        }
    }

    public Empleado buscarEmpleadoDNI(int dni) {
        for (Empleado e : empleados) {
            if (e.getDni() == dni) {
                return e;
            }
        }
        return null;
    }

    public Empleado empleadoQueMasGana() {
        if (empleados.isEmpty()) {
            return null;
        }
        Empleado mejor = empleados.get(0);
        for (Empleado e : empleados) {
            if (e.getSueldo() > mejor.getSueldo()) {
                mejor = e;
            }
        }
        return mejor;
    }

    public float sueldoPromedio() {
        if (empleados.isEmpty()) {
            return 0f;
        }

        float total = 0f;
        for (Empleado e : empleados) {
            total += e.getSueldo();
        }
        return total / empleados.size();
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
}
