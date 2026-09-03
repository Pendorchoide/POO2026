package producto;

import iva.TasaIva;

public final class Producto {
    private final String nombre;
    private final TasaIva iva;
    private final double precio;

    public Producto(String nombre, double precio, TasaIva iva) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.nombre = nombre;
        this.precio = precio;
        this.iva = iva;
    }

    public String getNombre() { return this.nombre; }
    public TasaIva getTasaIva() { return this.iva; }
    public double getNeto() { return this.precio; }
}
