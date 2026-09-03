package TP8.Punto2;

public class ImpresorFacturaConsola {
    public void imprimir(Factura factura) {
        System.out.println("Total neto: $" + factura.getTotalNeto());
        System.out.println("IVA 21%: $" + factura.getTotalIva(0.21));
        System.out.println("IVA 10.5%: $" + factura.getTotalIva(0.105));
        System.out.println("Total IVA: $" + factura.getTotalIva());
        System.out.println("Total a Pagar: $" + factura.getTotalFinal());
    }
}