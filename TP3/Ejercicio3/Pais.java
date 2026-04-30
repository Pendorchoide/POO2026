package TP3.Ejercicio3;
import java.util.ArrayList;


public class Pais {
    private String nombre;
    private ArrayList<Provincia> provincias;

    public Pais(String nombre, Provincia provincia) {
        this.nombre = nombre;
        this.provincias = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Provincia> getProvincias() {
        return provincias;
    }

    @Override
    public String toString() {
        return "País: " + nombre + "\n" + provincias.toString();
    }
}
