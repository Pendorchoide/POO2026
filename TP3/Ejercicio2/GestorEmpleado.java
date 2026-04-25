package TP3.Ejercicio2;
import java.util.ArrayList;

public class GestorEmpleado {
    private ArrayList<Empleado> empleados = new ArrayList<>();

    public void regitrarEmpleado(Empleado empleado){
        empleados.add(empleado);
    }

    public void quitarEmpleado(int dni){
        empleados.remove(buscarEmpleadoDNI(dni));
    }

    public Empleado buscarEmpleadoDNI(int dni){
        for (Empleado e : empleados){
            if(e.getDNI() == dni){
                return e;
            }
        }
        return null;
    }

    public Empleado empleadoQueMasGana(){
        if (empleados.size() == 0){
            return null;
        }
        Empleado resultado = this.empleados.getFirst();
        for (Empleado e : this.empleados){
            if (e.getSueldo() > resultado.getSueldo()){
                resultado = e;
            }
        }
        return resultado;
    }

    public float sueldoPromedio(){
        if (empleados.size() == 0){
            return 0;
        }
        
        float total =0f;
        for (Empleado e : this.empleados){
            total += e.getSueldo();
        }
        return total/empleados.size();
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
}
