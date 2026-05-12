package TP3.Ejercicio3;
import java.util.HashMap;
import java.util.Map;

public class Pais {
    private String nombre;
    private String capital;
    private float superficie;

    private Map<String, Provincia> provincias;
    private Map<String, Pais> paisLimitrofe;


    public Pais(String nombre, Provincia provincia) {
        this.nombre = nombre;
        this.provincias = new HashMap<String, Provincia>();
        this.paisLimitrofe = new HashMap<String, Pais>();
    }
    public Pais(String nombre, String capital, float superficie) {
        this.nombre = nombre;
        this.capital = capital;
        this.superficie = superficie;
        this.provincias = new HashMap<String, Provincia>();
        this.paisLimitrofe = new HashMap<String, Pais>();
    }

    public void agregarProvincia(Provincia provincia){
        provincias.put(provincia.getNombre().toLowerCase(), provincia);
    }

    public void agregarLimitrofe(Pais pais){
        paisLimitrofe.put(pais.getNombre().toLowerCase(), pais);
    }

    public String getNombre() {
        return nombre;
    }

    public Map<String, Pais> getPaisesLimitrofes() {
        return paisLimitrofe;
    }

    public Map<String, Provincia> getProvincias() {
        return provincias;
    }

    public float getSuperficie() {
        return superficie;
    }

    @Override
    public String toString() {
        return "País: " + nombre + "\n" + provincias.toString();
    }
}
