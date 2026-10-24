package TP10;
import java.util.ArrayList;
import java.util.List;

import TP10.Exceptions.CollectionVaciaException;
import TP10.Exceptions.EnteroPositivoException;
import TP10.Exceptions.NoEncontradoException;


class EmpleadoService {
    private static List<Empleado> empleados = new ArrayList<>();
    private static int proximoLegajo = 1;

    public static Boolean agregar(Empleado empleado) throws EnteroPositivoException{
        empleado.setLegajo(proximoLegajo++);
        return empleados.add(empleado);
    }

    public static Empleado mayorSueldo() throws CollectionVaciaException {
        // Lógica omitida para brevedad
        if (empleados.isEmpty()){
            throw new CollectionVaciaException("No hay empleados.");
        }
        return empleados.get(0);
    }

    public static Double sueldoPromedio() throws CollectionVaciaException {
        if (empleados.isEmpty()){
            throw new CollectionVaciaException("No hay empleados.");
        }
        return 0.0; // Lógica omitida
    }

    public static void eliminar(String nombre) throws  CollectionVaciaException,  NoEncontradoException{
        if (empleados.isEmpty()){
            throw new CollectionVaciaException("No hay empleados.");
        }
        int index = -1;
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getNombre().equals(nombre)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                throw new NoEncontradoException("No se encontró el empleado.");
        }

        empleados.remove(index);
    }
}