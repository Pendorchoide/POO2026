package impresor.adaptador;

import factura.Factura;
import impresor.puerto.PuertoSalida;

public class ImpresorConsolaAdapter implements PuertoSalida {

    @Override
    public void imprimir(Factura factura) {
        String formatoCabecera = "| %-14s | %-9s | %-12s | %-11s | %-7s | %-11s |\n";
        String formatoFila     = "| %-14.14s | %9d | $%11.2f | $%10.2f | %-6.1f%% | $%11.2f |\n";
        String formatoPie      = "| %-65s | $%11.2f |\n";
        String separador = "+----------------+-----------+--------------+-------------+---------+--------------+";

        System.out.println(separador);
        System.out.printf("| TIPO FACTURA: %-63s    |\n", factura.getTipo());
        System.out.println(separador);

        System.out.printf(formatoCabecera, "Nombre", "Cantidad", "Precio U.", "Descuento L.", "IVA", "Neto Linea");
        System.out.println(separador);

        factura.getLineas().forEach(linea -> {
            double descuentoLinea = linea.getNetoSinDescuento() - linea.getNeto();
            double porcentajeIva = linea.getProducto().getTasaIva().getValor() * 100;

            System.out.printf(formatoFila,
                linea.getProducto().getNombre(),
                linea.getCantidad(),
                linea.getProducto().getNeto(),
                descuentoLinea,
                porcentajeIva,
                linea.getNeto()
            );
        });

        System.out.println(separador);

        System.out.printf(formatoPie, "SUBTOTAL NETO:", factura.getNeto());
        System.out.printf(formatoPie, "TOTAL IVA:", factura.getTotalIVA());
        System.out.printf(formatoPie, "TOTAL BRUTO (A PAGAR):", factura.getBruto());
        System.out.println(separador + "\n");
    }
}
