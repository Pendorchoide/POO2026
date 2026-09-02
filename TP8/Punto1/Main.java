package TP8.Punto1;

public class Main {
    public static void main(String[] args) {
        Producto teclado = new Producto("Teclado", 2500, 0.21);
        Producto monitor = new Producto("Monitor", 5000, 0.21);

        LineaFactura linea1 = new LineaFactura(teclado, 2);
        LineaFactura linea2 = new LineaFactura(monitor, 1);

        Factura facturaA = new Factura(TipoComprobante.A, 0.0);
        facturaA.agregarLinea(linea1);
        facturaA.agregarLinea(linea2);

        System.out.println("Factura A:");
        facturaA.imprimirFactura();

        System.out.println();

        Factura facturaB = new Factura(TipoComprobante.B, 0.10);
        facturaB.agregarLinea(linea1);
        facturaB.agregarLinea(linea2);

        System.out.println("Factura B:");
        facturaB.imprimirFactura();
    }
}
