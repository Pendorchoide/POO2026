package TP8.Punto1;

public class LineaFactura {
    private int cantidad;
    private Producto producto;
    private double precioUnitarioDeVenta;
    private double porcentajeIvaAplicado;

    public LineaFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitarioDeVenta = producto.getPrecioBase();
        this.porcentajeIvaAplicado = producto.getPorcentajeIva();
    }

    public double getSubtotalNeto() {
        return this.cantidad * this.precioUnitarioDeVenta;
    }

    public double getMontoIva() {
        return getSubtotalNeto() * this.porcentajeIvaAplicado;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public double getPrecioUnitarioDeVenta() {
        return this.precioUnitarioDeVenta;
    }

    public double getPorcentajeIvaAplicado() {
        return this.porcentajeIvaAplicado;
    }
}