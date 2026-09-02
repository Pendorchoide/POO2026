package TP8.Punto1;

public class Producto {
    private String nombre; 
    private double precioBase;
    private double porcentajeIva;

    public Producto(String nombre, double precioBase, double porcentajeIva){
         this.nombre = nombre;
         this.precioBase = precioBase;
         this.porcentajeIva = porcentajeIva;
    }

    // Getters
    String getNombre() { return this.nombre; } 
    double getPrecioBase() { return this.precioBase; }
    double getPorcentajeIva(){ return this.porcentajeIva; }
}