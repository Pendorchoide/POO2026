public class Factura {
 private final String nombreCliente;
 private final double montoBase;
 private final String tipoCliente;
 private final double totalFinal;

 public Factura(String nombreCliente, double montoBase, String tipoCliente, double totalFinal) {
  this.nombreCliente = nombreCliente;
  this.montoBase = montoBase;
  this.tipoCliente = tipoCliente;
  this.totalFinal = totalFinal;
 }

 public String getNombreCliente() {
  return nombreCliente;
 }

 public double getMontoBase() {
  return montoBase;
 }

 public String getTipoCliente() {
  return tipoCliente;
 }

 public double getTotalFinal() {
  return totalFinal;
 }
}