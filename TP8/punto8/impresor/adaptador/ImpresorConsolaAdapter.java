package impresor.adaptador;

import factura.Factura;
import factura.TipoFactura;
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
            double descuentoLinea = linea.getNetoSinDescuento() - linea.getNetoConDescuentos(factura.getDescuentos());
            double porcentajeIva = linea.getProducto().getTasaIva().getValor() * 100;

            System.out.printf(formatoFila,
                linea.getProducto().getNombre(),
                linea.getCantidad(),
                linea.getProducto().getNeto(),
                descuentoLinea,
                porcentajeIva,
                linea.getNetoConDescuentos(factura.getDescuentos())
            );
        });

        System.out.println(separador);

        if (factura.getTipo() == TipoFactura.A) {
            System.out.printf(formatoPie, "SUBTOTAL NETO:", factura.getNeto());
            System.out.printf(formatoPie, "TOTAL IVA:", factura.getTotalIVA());
            System.out.printf(formatoPie, "TOTAL FINAL:", factura.getBruto());
        } else {
            System.out.printf(formatoPie, "TOTAL NETO:", factura.getNeto());
            System.out.printf(formatoPie, "TOTAL IVA:", factura.getTotalIVA());
            System.out.printf(formatoPie, "TOTAL FINAL (PRECIO ÚNICO):", factura.getBruto());
            System.out.printf(formatoPie, "DESCUENTO TOTAL:", factura.getDescuentoTotal());
        }

        System.out.println(separador + "\n");
    }
}
