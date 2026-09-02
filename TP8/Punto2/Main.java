package TP8.Punto2;

public class Main {
    public static void main(String[] args) {
        Producto teclado = new Producto("Teclado", 2500, 0.21);
        Producto monitor = new Producto("Monitor", 5000, 0.21);
        Producto servicio = new Producto("Servicio", 1000, 0.105);

        LineaFactura linea1 = new LineaFactura(teclado, 2);
        LineaFactura linea2 = new LineaFactura(monitor, 1);
        LineaFactura linea3 = new LineaFactura(servicio, 1);

        Factura facturaA = new Factura(TipoComprobante.A, 0.0);
        facturaA.agregarLinea(linea1);
        facturaA.agregarLinea(linea2);
        facturaA.agregarLinea(linea3);

        System.out.println("Factura A:");
        ImpresorFacturaConsola impresor = new ImpresorFacturaConsola();
        impresor.imprimir(facturaA);

        System.out.println();

        Factura facturaB = new Factura(TipoComprobante.B, 0.10);
        facturaB.agregarLinea(linea1);
        facturaB.agregarLinea(linea2);
        facturaB.agregarLinea(linea3);

        System.out.println("Factura B:");
        impresor.imprimir(facturaB);
    }
}
