package TP8.Punto2;

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

    public double getTotalNeto() {
        double totalNeto = 0;
        for (LineaFactura linea : lineas) {
            totalNeto += linea.getSubtotalNeto(getPorcentajeDescuentoAplicado());
        }
        return totalNeto;
    }

    public double getTotalIva(double porcentajeIva) {
        double totalIva = 0;
        for (LineaFactura linea : lineas) {
            if (Double.compare(linea.getPorcentajeIvaAplicado(), porcentajeIva) == 0) { //compara dos valores double de forma segura para evitar problemas con valores NaN o signos
                totalIva += linea.getMontoIva(getPorcentajeDescuentoAplicado());
            }
        }
        return totalIva;
    }

    public double getTotalIva() {
        double totalIva = 0;
        for (LineaFactura linea : lineas) {
            totalIva += linea.getMontoIva(getPorcentajeDescuentoAplicado());
        }
        return totalIva;
    }

    public double getTotalFinal() {
        return getTotalNeto() + getTotalIva();
    }

    private double getPorcentajeDescuentoAplicado() {
        return this.tipoComprobante == TipoComprobante.B ? this.porcentajeDescuento : 0;
    }

    public TipoComprobante getTipoComprobante() {
        return this.tipoComprobante;
    }

    public List<LineaFactura> getLineas() {
        return this.lineas;
    }
}
