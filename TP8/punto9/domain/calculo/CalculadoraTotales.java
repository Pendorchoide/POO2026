package domain.calculo;

import domain.factura.Factura;

public interface CalculadoraTotales {
    ResumenFactura resumen(Factura factura);
}