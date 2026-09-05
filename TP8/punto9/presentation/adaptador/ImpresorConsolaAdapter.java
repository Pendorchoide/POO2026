package presentation.adaptador;

import java.util.Map;

import domain.calculo.DetalleLinea;
import domain.calculo.ResumenFactura;
import domain.factura.TipoFactura;
import presentation.estrategia.PresentacionResumen;
import presentation.puerto.PuertoSalida;

public final class ImpresorConsolaAdapter implements PuertoSalida {
    private final Map<TipoFactura, PresentacionResumen> presentaciones;

    public ImpresorConsolaAdapter(Map<TipoFactura, PresentacionResumen> presentaciones) {
        this.presentaciones = presentaciones;
    }

    @Override
    public void imprimir(ResumenFactura resumen) {
        String formatoCabecera = "| %-10s | %-9s | %-12s | %-11s | %-7s | %-11s |\n";
        String formatoFila     = "| %-10.10s | %9d | $%11.2f | $%10.2f | %-6.1f%% | $%11.2f |\n";
        String formatoPie      = "| %-58s | $%11.2f |\n";
        String separador = "+------------+-----------+--------------+-------------+---------+--------------+";

        System.out.println(separador);
        System.out.printf("| TIPO FACTURA: %-63s    |\n", resumen.getTipo());
        System.out.println(separador);

        System.out.printf(formatoCabecera, "Codigo", "Cantidad", "Precio U.", "Descuento L.", "IVA", "Neto Linea");
        System.out.println(separador);

        for (DetalleLinea detalle : resumen.getDetalles()) {
            System.out.printf(formatoFila,
                detalle.getCodigo(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getDescuento(),
                detalle.getTasaIva() * 100,
                detalle.getNeto());
        }

        System.out.println(separador);

        for (String[] fila : this.presentaciones.get(resumen.getTipo()).filasPie(resumen)) {
            System.out.printf(formatoPie, fila[0], Double.parseDouble(fila[1]));
        }

        System.out.println(separador + "\n");
    }
}