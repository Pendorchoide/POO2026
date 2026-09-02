package TP8.Punto1;

import java.util.ArrayList;
import java.util.List;

enum TipoComprobante { A, B }

public class Factura {
    private TipoComprobante tipoComprobante;
    private double porcentajeDescuento;
    private List<LineaFactura> lineas;

    public Factura(TipoComprobante tipoComprobante, double porcentajeDescuento) {
        this.tipoComprobante = tipoComprobante;
        this.porcentajeDescuento = porcentajeDescuento;
        this.lineas = new ArrayList<>();
    }

    public void agregarLinea(LineaFactura linea) {
        this.lineas.add(linea);
    }

    public void imprimirFactura() {
        double totalNeto = 0;
        double totalIva = 0;
        double totalFinal = 0;

        for (LineaFactura linea : lineas) {
            totalNeto += linea.getSubtotalNeto();
            totalIva += linea.getMontoIva();
            totalFinal += linea.getSubtotalNeto() + linea.getMontoIva();
        }

        if (this.tipoComprobante == TipoComprobante.B) {
            double montoDescuento = totalFinal * this.porcentajeDescuento;
            totalFinal -= montoDescuento;
        }

        System.out.println("Total a Pagar: $" + totalFinal);
        if (this.tipoComprobante == TipoComprobante.A) {
            System.out.println("Detalle IVA: $" + totalIva);
        }
    }

    public TipoComprobante getTipoComprobante() {
        return this.tipoComprobante;
    }

    public List<LineaFactura> getLineas() {
        return this.lineas;
    }
}
