package Ejercicio5;
import java.util.ArrayList;


public class Lienzo {
    private ArrayList<ElementoGrafico> elementos;
    
    public Lienzo() {
        this.elementos = new ArrayList<>();
    }

    public ArrayList<ElementoGrafico> getElementos() {
        return elementos;
    }

    

    public void agregarElemento(ElementoGrafico elemento) {
        this.elementos.add(elemento);
    }

    public void eliminarElemento(ElementoGrafico elemento) {
        this.elementos.remove(elemento);
    }

    public void mostrarElementos() {
        for (ElementoGrafico elemento : elementos) {
            System.out.println(elemento);
        }
    }

    
    public void cambioColorGlobal(String nuevoColorHex) {
        for (ElementoGrafico elemento : elementos) {
            elemento.setColorHex(nuevoColorHex);
        }
    }

    public void moverAlOrigenGlobal() {
        for (ElementoGrafico elemento : elementos) {
            elemento.moverA(new Punto(0, 0));
        }
    }



    
    public double calcularAreaTotal() {
        double areaTotal = 0;
        for (ElementoGrafico elemento : elementos) {
            areaTotal += elemento.calcularArea();
        }
        return areaTotal;
    }

}
