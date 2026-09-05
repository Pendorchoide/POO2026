package domain.factura;

import domain.descuento.Descuento;
import domain.descuento.DescuentoPorCantidad;
import domain.producto.Producto;

public class FacturaBuilder {
    private Factura factura;
    private LineaFactura lineaEnConstruccion;

    public FacturaBuilder() {}

    public FacturaBuilder buildLinea(Producto producto, int cantidad) {
        commitLineaPendiente();

        this.lineaEnConstruccion = new LineaFactura(
            "TODO",
            cantidad,
            producto.getId(),
            producto.getNeto(),
            producto.getImpuesto().getValor()
        );

        return this;
    }

    public FacturaBuilder aplicarDescuentoLinea(Descuento descuento) {
        lineaActual("aplicarDescuentoLinea").agregarDescuento(descuento);
        return this;
    }

    public FacturaBuilder aplicarDescuentoLinea(DescuentoPorCantidad descuento) {
        lineaActual("aplicarDescuentoLinea").agregarDescuentoPorCantidad(descuento);
        return this;
    }

    public FacturaBuilder aplicarDescuentoFactura(Descuento descuento) {
        facturaActual("aplicarDescuentoFactura").agregarDescuento(descuento);
        return this;
    }

    public FacturaBuilder setType(TipoFactura tipo) {
        if (this.factura != null) {
            throw new IllegalStateException("El tipo de factura ya fue definido");
        }
        this.factura = new Factura(tipo);
        return this;
    }

    public Factura build() {
        commitLineaPendiente();
        return facturaActual("build");
    }

    public FacturaBuilder resetBuilder() {
        this.factura = null;
        this.lineaEnConstruccion = null;
        return this;
    }

    private void commitLineaPendiente() {
        if (lineaEnConstruccion != null) {
            facturaActual("commitLineaPendiente").agregarLinea(lineaEnConstruccion);
            lineaEnConstruccion = null;
        }
    }

    private LineaFactura lineaActual(String operacion) {
        if (lineaEnConstruccion == null) {
            throw new IllegalStateException("Debe llamar a buildLinea antes de " + operacion);
        }
        return lineaEnConstruccion;
    }

    private Factura facturaActual(String operacion) {
        if (factura == null) {
            throw new IllegalStateException("Debe llamar a setType antes de " + operacion);
        }
        return factura;
    }
}