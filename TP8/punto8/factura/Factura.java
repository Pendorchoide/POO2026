package factura;

import descuento.interfaz.Descuento;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public double getNeto() {
        return lineas.stream()
            .mapToDouble(linea -> linea.getNetoConDescuentos(this.descuentos))
            .sum();
    }

    public double getBruto() {
        return lineas.stream()
            .mapToDouble(linea -> linea.getBrutoConDescuentos(this.descuentos))
            .sum();
    }

    public double getTotalIVA() {
        return lineas.stream()
            .mapToDouble(linea -> linea.getIvaConDescuentos(this.descuentos))
            .sum();
    }

    public double getDescuentoTotal() {
        return lineas.stream()
            .mapToDouble(linea -> linea.getDescuentoTotalConDescuentos(this.descuentos))
            .sum();
    }

    public void agregarLinea(LineaFactura linea) {
        lineas.add(new LineaFactura(linea));
    }

    public void agregarDescuento(Descuento descuento) {
        this.descuentos.add(descuento);
    }
}
