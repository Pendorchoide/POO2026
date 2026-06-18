package Ejercicio4;
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



    
    public float calcularAreaTotal() {
        float areaTotal = 0;
        for (ElementoGrafico elemento : elementos) {
            areaTotal += elemento.calcularArea();
        }
        return areaTotal;
    }


    //Posible solución si se quiere mantener la estructura incorrecta

    /* 
    public float calcularAreaTotal() {
        float areaTotal = 0;
        for (ElementoGrafico elemento : elementos) {
            if (elemento instanceof Circulo) {
                areaTotal += ((Circulo) elemento).calcularArea();
            } else if (elemento instanceof Cuadrado) {
                areaTotal += ((Cuadrado) elemento).calcularArea();
            } else if (elemento instanceof Rectangulo) {
                areaTotal += ((Rectangulo) elemento).calcularArea();
            } else if (elemento instanceof Elipse) {
                areaTotal += ((Elipse) elemento).calcularArea();
            }
        }
        return areaTotal;
    }
    */
}
