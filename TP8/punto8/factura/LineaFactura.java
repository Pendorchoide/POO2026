package factura;

import descuento.interfaz.Descuento;
import descuento.interfaz.DescuentoPorCantidad;
import java.util.ArrayList;
import java.util.List;
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

    public double getNetoSinDescuentoPorCantidad() {
        double total = getNetoSinDescuento();
        double descuentoPorCantidad = this.descuentosPorCantidad.stream()
            .mapToDouble(d -> d.aplicar(this.cantidad, this.producto.getNeto()))
            .sum();
        return Math.max(0.0, total - descuentoPorCantidad);
    }

    public double getNetoConDescuentos(List<Descuento> descuentosFactura) {
        double base = getNetoSinDescuentoPorCantidad();

        double descuentoPorcentual = this.descuentosPorcentuales.stream()
            .mapToDouble(d -> d.aplicar(base))
            .sum();

        double baseConDescuentoLinea = Math.max(0.0, base - descuentoPorcentual);

        double descuentoFactura = descuentosFactura.stream()
            .mapToDouble(d -> d.aplicar(baseConDescuentoLinea))
            .sum();

        return Math.max(0.0, baseConDescuentoLinea - descuentoFactura);
    }

    public double getBrutoConDescuentos(List<Descuento> descuentosFactura) {
        return getNetoConDescuentos(descuentosFactura) * (1 + this.producto.getTasaIva().getValor());
    }

    public double getIvaConDescuentos(List<Descuento> descuentosFactura) {
        return getBrutoConDescuentos(descuentosFactura) - getNetoConDescuentos(descuentosFactura);
    }

    public double getDescuentoTotalConDescuentos(List<Descuento> descuentosFactura) {
        return getNetoSinDescuento() - getNetoConDescuentos(descuentosFactura);
    }

    public double getNeto() {
        return getNetoConDescuentos(List.of());
    }

    public double getBruto() {
        return getBrutoConDescuentos(List.of());
    }

    public double getDiferencialIVA() {
        return getIvaConDescuentos(List.of());
    }

    public void agregarDescuento(Descuento descuento) {
        this.descuentosPorcentuales.add(descuento);
    }

    public void agregarDescuentoPorCantidad(DescuentoPorCantidad descuento) {
        this.descuentosPorCantidad.add(descuento);
    }
}
