public class ImpresorFactura {
 public void imprimir(Factura factura) {
  System.out.println("FACTURA: " + factura.getNombreCliente() + " | Total: $" + factura.getTotalFinal());
 }
}