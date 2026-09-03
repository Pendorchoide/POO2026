package TP8.Punto2;

public class Producto {
    private String nombre; 
    private double precioBase;
    private double porcentajeIva;

    public Producto(String nombre, double precioBase, double porcentajeIva){
         this.nombre = nombre;
         this.precioBase = precioBase;
         this.porcentajeIva = porcentajeIva;
    }

    String getNombre() { return this.nombre; } 
    double getPrecioBase() { return this.precioBase; }
    double getPorcentajeIva(){ return this.porcentajeIva; }
}