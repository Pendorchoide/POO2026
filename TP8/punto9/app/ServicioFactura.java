package app;

import java.util.List;

import app.dto.LineaRequest;
import app.dto.NuevaFacturaRequest;
import domain.calculo.CalculadoraTotales;
import domain.calculo.ResumenFactura;
import domain.factura.Factura;
import domain.factura.FacturaBuilder;
import domain.factura.FacturaRepository;
import domain.producto.Producto;
import domain.producto.ProductoRepository;

public class ServicioFactura {
    private final FacturaRepository repoFactura;
    private final ProductoRepository repoProducto;
    private final CalculadoraTotales calculadora;

    public ServicioFactura(
        FacturaRepository repoFactura, 
        ProductoRepository repoProducto,
        CalculadoraTotales calculadora
    ) {
        this.repoFactura = repoFactura;
        this.repoProducto = repoProducto;
        this.calculadora = calculadora;
    }

    public Factura crearFactura(NuevaFacturaRequest request) {
        FacturaBuilder builder = new FacturaBuilder().setType(request.getTipo());

        for (LineaRequest linea : request.getLineas()) {
            Producto producto = this.repoProducto.obtenerProductoPorId(linea.getProductoId());
            if (producto == null) {
                throw new IllegalArgumentException("Producto inexistente: " + linea.getProductoId());
            }
            builder.buildLinea(producto, linea.getCantidad());
            linea.getDescuentos().forEach(builder::aplicarDescuentoLinea);
            linea.getDescuentosPorCantidad().forEach(builder::aplicarDescuentoLinea);
        }

        request.getDescuentosFactura().forEach(builder::aplicarDescuentoFactura);

        return this.repoFactura.guardarFactura(builder.build());
    }

    public ResumenFactura resumen(Factura factura) {
        return this.calculadora.resumen(factura);
    }

    public List<Factura> obtenerFacturas() {
        return this.repoFactura.obtenerFacturas();
    }

    public Factura obtenerFacturaPorId(String id) {
        return this.repoFactura.obtenerFacturaPorId(id);
    }

    public boolean eliminarFactura(String id) {
        return this.repoFactura.eliminarFactura(id);
    }
}