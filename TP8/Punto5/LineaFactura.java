package TP8.Punto2;

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

    public double getSubtotalNeto(double porcentajeDescuento) {
        double precioConDescuento = this.precioUnitarioDeVenta * (1 - porcentajeDescuento);
        return this.cantidad * precioConDescuento;
    }

    public double getMontoIva(double porcentajeDescuento) {
        return getSubtotalNeto(porcentajeDescuento) * this.porcentajeIvaAplicado;
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