package domain.factura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.descuento.Descuento;
import domain.descuento.DescuentoPorCantidad;

public final class LineaFactura {
    private final String id;
    private final int cantidad;

    private final String productoId;
    private final double productoPrecioNeto;     // productoPrecioNeto & productoTasaIva almacenados
    private final double productoTasaIvaValor;   // en linea para no perderlo si el precio o iva cambian

    private final List<Descuento> descuentosPorcentuales = new ArrayList<>();
    private final List<DescuentoPorCantidad> descuentosPorCantidad = new ArrayList<>();

    public LineaFactura(
        String id,
        int cantidad,
        String productoId, 
        double productoPrecioNeto,
        double productoTasaIvaValor
    ) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        this.id = id;
        this.cantidad = cantidad;
        this.productoId = productoId;
        this.productoPrecioNeto = productoPrecioNeto;
        this.productoTasaIvaValor = productoTasaIvaValor;
    }

    public LineaFactura(LineaFactura otra) {
        this.id = otra.id;
        this.cantidad = otra.cantidad;
        this.productoId = otra.productoId;
        this.productoPrecioNeto = otra.productoPrecioNeto;
        this.productoTasaIvaValor = otra.productoTasaIvaValor;
        this.descuentosPorcentuales.addAll(otra.descuentosPorcentuales);
        this.descuentosPorCantidad.addAll(otra.descuentosPorCantidad);
    }

    public String getProductoId() { return this.productoId; }
    public int getCantidad() { return cantidad; }
    public double getProductoNeto() { return this.productoPrecioNeto; }
    public double getProductoIva() { return this.productoTasaIvaValor; }
    public List<Descuento> getDescuentosPorcentuales() { return Collections.unmodifiableList(this.descuentosPorcentuales); }
    public List<DescuentoPorCantidad> getDescuentosPorCantidad() { return Collections.unmodifiableList(this.descuentosPorCantidad); }

    public void agregarDescuento(Descuento descuento) {
        this.descuentosPorcentuales.add(descuento);
    }

    public void agregarDescuentoPorCantidad(DescuentoPorCantidad descuento) {
        this.descuentosPorCantidad.add(descuento);
    }
}