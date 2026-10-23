import java.util.ArrayList;
import java.util.List;

public class Empleado {
    private Integer legajo;
    private String nombre;
    private Integer sueldo;

    public Empleado(String nombre, Integer sueldo) {
        this.nombre = nombre;
        this.sueldo = sueldo;
    }
    
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setSueldo(Integer sueldo) { this.sueldo = sueldo; }
    public void setLegajo(Integer legajo) { this.legajo = legajo; }
    public String getNombre() { return nombre; }
    public Integer getSueldo() { return sueldo; }
}
