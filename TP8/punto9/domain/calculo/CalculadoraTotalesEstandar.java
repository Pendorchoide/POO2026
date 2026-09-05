package domain.calculo;

import java.util.List;

import domain.descuento.Descuento;
import domain.factura.Factura;
import domain.factura.LineaFactura;

public final class CalculadoraTotalesEstandar implements CalculadoraTotales {

    @Override
    public ResumenFactura resumen(Factura factura) {
        List<DetalleLinea> detalles = factura.getLineas().stream()
            .map(linea -> detalle(linea, factura.getDescuentos()))
            .toList();

        double neto = detalles.stream().mapToDouble(DetalleLinea::getNeto).sum();
        double iva = detalles.stream().mapToDouble(DetalleLinea::getIva).sum();
        double bruto = neto + iva;
        double descuentoTotal = detalles.stream().mapToDouble(DetalleLinea::getDescuento).sum();

        return new ResumenFactura(factura.getTipo(), detalles, neto, iva, bruto, descuentoTotal);
    }

    private DetalleLinea detalle(LineaFactura linea, List<Descuento> descuentosFactura) {
        int cantidad = linea.getCantidad();
        double precio = linea.getProductoNeto();
        double base = cantidad * precio;

        double descuentoPorCantidad = linea.getDescuentosPorCantidad().stream()
            .mapToDouble(d -> d.aplicar(cantidad, precio))
            .sum();
        double netoBase = Math.max(0.0, base - descuentoPorCantidad);

        double descuentoPorcentual = linea.getDescuentosPorcentuales().stream()
            .mapToDouble(d -> d.aplicar(netoBase))
            .sum();
        double netoLinea = Math.max(0.0, netoBase - descuentoPorcentual);

        double descuentoFactura = descuentosFactura.stream()
            .mapToDouble(d -> d.aplicar(netoLinea))
            .sum();
        double neto = Math.max(0.0, netoLinea - descuentoFactura);

        double iva = neto * linea.getProductoIva();

        return new DetalleLinea(
            linea.getProductoId(),
            cantidad,
            precio,
            linea.getProductoIva(),
            base - neto,
            neto,
            iva);
    }
}