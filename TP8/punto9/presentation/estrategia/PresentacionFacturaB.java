package presentation.estrategia;

import java.util.List;

import domain.calculo.ResumenFactura;

public final class PresentacionFacturaB implements PresentacionResumen {

    @Override
    public List<String[]> filasPie(ResumenFactura resumen) {
        return List.of(
            fila("TOTAL NETO:", resumen.getNeto()),
            fila("TOTAL IVA:", resumen.getIva()),
            fila("TOTAL FINAL (PRECIO UNICO):", resumen.getBruto()),
            fila("DESCUENTO TOTAL:", resumen.getDescuentoTotal()));
    }

    private String[] fila(String etiqueta, double valor) {
        return new String[] { etiqueta, String.format("%.2f", valor) };
    }
}