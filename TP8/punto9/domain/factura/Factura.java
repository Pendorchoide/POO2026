package domain.factura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.descuento.Descuento;

public class Factura {
    private final TipoFactura tipo;
    private final List<LineaFactura> lineas = new ArrayList<>();
    private final List<Descuento> descuentos = new ArrayList<>();

    public Factura(TipoFactura tipo) {
        this.tipo = tipo;
    }

    public TipoFactura getTipo() { return this.tipo; }
    public List<LineaFactura> getLineas() { return Collections.unmodifiableList(this.lineas); }
    public List<Descuento> getDescuentos() { return Collections.unmodifiableList(this.descuentos); }

    public void agregarLinea(LineaFactura linea) {
        lineas.add(new LineaFactura(linea));
    }

    public void agregarDescuento(Descuento descuento) {
        this.descuentos.add(descuento);
    }
}