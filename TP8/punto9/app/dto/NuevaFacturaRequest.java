package app.dto;

import java.util.List;

import domain.descuento.Descuento;
import domain.factura.TipoFactura;

public final class NuevaFacturaRequest {
    private final TipoFactura tipo;
    private final List<LineaRequest> lineas;
    private final List<Descuento> descuentosFactura;

    public NuevaFacturaRequest(TipoFactura tipo, List<LineaRequest> lineas,
                               List<Descuento> descuentosFactura) {
        this.tipo = tipo;
        this.lineas = List.copyOf(lineas);
        this.descuentosFactura = List.copyOf(descuentosFactura);
    }

    public TipoFactura getTipo() { return tipo; }
    public List<LineaRequest> getLineas() { return lineas; }
    public List<Descuento> getDescuentosFactura() { return descuentosFactura; }
}