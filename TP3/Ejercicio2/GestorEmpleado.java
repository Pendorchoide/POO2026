package TP3.Ejercicio2;

import java.util.ArrayList;

public class GestorEmpleado {
    private ArrayList<Empleado> empleados;

    public GestorEmpleado() {
        empleados = new ArrayList<>();
    }

    public boolean registrarEmpleado(Empleado empleado) {
        if (existeEmpleadoDNI(empleado.getDni())) {
            return false; // No registrar si ya existe
        }
        empleados.add(empleado);
        return true;
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

    private Empleado buscarEmpleadoDNI(int dni) {
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

    public double sueldoPromedio() {
        if (empleados.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (Empleado e : empleados) {
            total += e.getSueldo();
        }
        return total / empleados.size();
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
}
