package factura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import descuento.interfaz.Descuento;

public class Factura {
    private final TipoFactura tipo;
    private final List<LineaFactura> lineas = new ArrayList<>();
    private final List<Descuento> descuentos = new ArrayList<>();

    public Factura(TipoFactura tipo) {
        this.tipo = tipo;
    }

    public TipoFactura getTipo() { return this.tipo; }
    public List<LineaFactura> getLineas() { return Collections.unmodifiableList(this.lineas); }

    public double getNeto() {
        double netoLineas = lineas.stream()
            .mapToDouble(LineaFactura::getNeto)
            .sum();

        double descuentoGlobal = this.descuentos.stream()
            .mapToDouble(d -> d.aplicar(netoLineas))
            .sum();

        return Math.max(0.0, netoLineas - descuentoGlobal);
    }

    public double getBruto() {
        double brutoLineas = lineas.stream()
            .mapToDouble(LineaFactura::getBruto)
            .sum();

        double descuentoGlobal = this.descuentos.stream()
            .mapToDouble(d -> d.aplicar(brutoLineas))
            .sum();

        return Math.max(0.0, brutoLineas - descuentoGlobal);
    }

    public double getTotalIVA() {
        return getBruto() - getNeto();
    }

    public void agregarLinea(LineaFactura linea) {
        lineas.add(new LineaFactura(linea));
    }

    public void agregarDescuento(Descuento descuento) {
        this.descuentos.add(descuento);
    }
}
