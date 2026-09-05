package domain.producto;

import domain.impuesto.Impuesto;

public final class Producto {
    private final String id;
    private final String nombre;
    private final Impuesto impuesto;
    private final double precio;

    public Producto(String id, String nombre, double precio, Impuesto impuesto) {
        this.id = id;

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.nombre = nombre;
        this.precio = precio;
        this.impuesto = impuesto;
    }

    public String getId() { return this.id; };
    public String getNombre() { return this.nombre; }
    public Impuesto getImpuesto() { return this.impuesto; }
    public double getNeto() { return this.precio; }
}