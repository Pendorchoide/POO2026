package Ejercicio4;

public class Main {
    public static void main(String[] args) {
        Lienzo lienzo = new Lienzo();

        Cuadrado elemento1 = new Cuadrado("#FF0000", new Punto(5, 5), "Capa1", 10);
        Rectangulo elemento2 = new Rectangulo("#00FF00", new Punto(10, 10), "Capa2", 20, 10);   
        Elipse elemento3 = new Elipse("#FFFF00", new Punto(15, 15), "Capa3", 10, 5);
        Circulo elemento4 = new Circulo("#FF00FF", new Punto(20, 20), "Capa4", 7);  

        lienzo.agregarElemento(elemento1);
        lienzo.agregarElemento(elemento2);
        lienzo.agregarElemento(elemento3);
        lienzo.agregarElemento(elemento4);

        System.out.println("Elementos en el lienzo:");
        lienzo.mostrarElementos();

        System.out.println("\nCambiando color global a #808080...");
        lienzo.cambioColorGlobal("#808080");

        System.out.println("\nMoviendo todos los elementos al origen...");
        lienzo.moverAlOrigenGlobal();
        
        System.out.println("\nElementos después de los cambios:");
        lienzo.mostrarElementos();

    }
    
}
