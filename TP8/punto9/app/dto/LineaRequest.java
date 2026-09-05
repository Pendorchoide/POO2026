package app.dto;

import java.util.List;

import domain.descuento.Descuento;
import domain.descuento.DescuentoPorCantidad;

public final class LineaRequest {
    private final String productoId;
    private final int cantidad;
    private final List<Descuento> descuentos;
    private final List<DescuentoPorCantidad> descuentosPorCantidad;

    public LineaRequest(String productoId, int cantidad,
                        List<Descuento> descuentos,
                        List<DescuentoPorCantidad> descuentosPorCantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.descuentos = List.copyOf(descuentos);
        this.descuentosPorCantidad = List.copyOf(descuentosPorCantidad);
    }

    public String getProductoId() { return productoId; }
    public int getCantidad() { return cantidad; }
    public List<Descuento> getDescuentos() { return descuentos; }
    public List<DescuentoPorCantidad> getDescuentosPorCantidad() { return descuentosPorCantidad; }
}