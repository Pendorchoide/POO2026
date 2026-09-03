package factura;

import java.util.ArrayList;
import java.util.List;
import descuento.interfaz.Descuento;
import descuento.interfaz.DescuentoPorCantidad;
import producto.Producto;

public final class LineaFactura {
    private final Producto producto;
    private final int cantidad;
    private final List<Descuento> descuentosPorcentuales = new ArrayList<>();
    private final List<DescuentoPorCantidad> descuentosPorCantidad = new ArrayList<>();

    public LineaFactura(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public LineaFactura(LineaFactura otra) {
        this.producto = otra.producto;
        this.cantidad = otra.cantidad;
        this.descuentosPorcentuales.addAll(otra.descuentosPorcentuales);
        this.descuentosPorCantidad.addAll(otra.descuentosPorCantidad);
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }

    public double getNetoSinDescuento() {
        return this.cantidad * this.producto.getNeto();
    }

    public double getNeto() {
        double total = getNetoSinDescuento();

        double descuentoPorCantidad = this.descuentosPorCantidad.stream()
            .mapToDouble(d -> d.aplicar(this.cantidad, this.producto.getNeto()))
            .sum();

        double baseConDescuentoCantidad = Math.max(0.0, total - descuentoPorCantidad);

        double descuentoPorcentual = this.descuentosPorcentuales.stream()
            .mapToDouble(d -> d.aplicar(baseConDescuentoCantidad))
            .sum();

        return Math.max(0.0, baseConDescuentoCantidad - descuentoPorcentual);
    }

    public double getBruto() {
        double tasaIva = this.producto.getTasaIva().getValor();
        return getNeto() * (1 + tasaIva);
    }

    public double getDiferencialIVA() {
        return getBruto() - getNeto();
    }

    public void agregarDescuento(Descuento descuento) {
        this.descuentosPorcentuales.add(descuento);
    }

    public void agregarDescuentoPorCantidad(DescuentoPorCantidad descuento) {
        this.descuentosPorCantidad.add(descuento);
    }
}
