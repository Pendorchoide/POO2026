package TP3.Ejercicio3;

import java.util.HashMap;
import java.util.Map;

public class Continente {
    private String nombre;
    private Map<String, Pais> paises; // clave = nombre en lowercase

    public Continente(String nombre) {
        this.nombre = nombre;
        this.paises = new HashMap<>();
    }

    public void agregarPais(Pais p){
        paises.put(p.getNombre().toLowerCase(), p);
    }

    public Pais getPais(String nombre){
        return paises.get(nombre.toLowerCase());
    }

    public Map<String, Pais> getPaises(){
        return paises;
    }

    public String getNombre(){
        return this.nombre;
    }

    public Map<String, Pais> getColeccionPaises() {
        return paises;
    }
}