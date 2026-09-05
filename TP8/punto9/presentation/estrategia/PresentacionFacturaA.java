package presentation.estrategia;

import java.util.List;

import domain.calculo.ResumenFactura;

public final class PresentacionFacturaA implements PresentacionResumen {

    @Override
    public List<String[]> filasPie(ResumenFactura resumen) {
        return List.of(
            fila("SUBTOTAL NETO:", resumen.getNeto()),
            fila("TOTAL IVA:", resumen.getIva()),
            fila("TOTAL FINAL:", resumen.getBruto()));
    }

    private String[] fila(String etiqueta, double valor) {
        return new String[] { etiqueta, String.format("%.2f", valor) };
    }
}