public class GestorFacturacion {
 private final String nombreCliente;
 private final double montoBase;
 private final String tipoCliente;
 private final CalculadorFacturacion calculador;
 private final RepositorioFactura repositorio;
 private final ImpresorFactura impresor;

 public GestorFacturacion(String nombre, double monto, String tipo) {
  this(nombre, monto, tipo, new CalculadorFacturacion(), new RepositorioFactura(), new ImpresorFactura());
 }

 public GestorFacturacion(String nombre, double monto, String tipo,
						  CalculadorFacturacion calculador,
						  RepositorioFactura repositorio,
						  ImpresorFactura impresor) {
  this.nombreCliente = nombre;
  this.montoBase = monto;
  this.tipoCliente = tipo;
  this.calculador = calculador;
  this.repositorio = repositorio;
  this.impresor = impresor;
 }

 public void procesarYGuardarFactura() {
  Factura factura = calculador.calcular(nombreCliente, montoBase, tipoCliente);
  try {
   repositorio.guardar(factura);
  } catch (Exception e) {
   System.out.println("Error bd: " + e.getMessage());
  }
  impresor.imprimir(factura);
 }
}
