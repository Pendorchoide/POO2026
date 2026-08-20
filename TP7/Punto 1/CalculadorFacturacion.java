public class CalculadorFacturacion {
 public Factura calcular(String nombreCliente, double montoBase, String tipoCliente) {
  
    double descuento = 0.0;
  
    if ("VIP".equals(tipoCliente)) {
    descuento = montoBase * 0.20;
    } 
    else if ("REGULAR".equals(tipoCliente)) {
    descuento = montoBase * 0.10;
    }

  return new Factura(nombreCliente, montoBase, tipoCliente, montoBase - descuento);
 }
}