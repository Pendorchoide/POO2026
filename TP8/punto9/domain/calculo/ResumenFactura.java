package domain.calculo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.factura.TipoFactura;

public final class ResumenFactura {
    private final TipoFactura tipo;
    private final List<DetalleLinea> detalles;
    private final double neto;
    private final double iva;
    private final double bruto;
    private final double descuentoTotal;

    public ResumenFactura(TipoFactura tipo, List<DetalleLinea> detalles,
                          double neto, double iva, double bruto, double descuentoTotal) {
        this.tipo = tipo;
        this.detalles = Collections.unmodifiableList(new ArrayList<>(detalles));
        this.neto = neto;
        this.iva = iva;
        this.bruto = bruto;
        this.descuentoTotal = descuentoTotal;
    }

    public TipoFactura getTipo() { return tipo; }
    public List<DetalleLinea> getDetalles() { return detalles; }
    public double getNeto() { return neto; }
    public double getIva() { return iva; }
    public double getBruto() { return bruto; }
    public double getDescuentoTotal() { return descuentoTotal; }
}