import java.util.Map;

import domain.calculo.CalculadoraTotales;
import domain.calculo.CalculadoraTotalesEstandar;
import domain.factura.FacturaRepository;
import domain.factura.TipoFactura;
import domain.impuesto.Iva;
import domain.producto.ProductoRepository;
import app.ServicioFactura;
import app.ServicioProducto;

import infra.persistence.FacturaInMemoryRepository;
import infra.persistence.ProductoInMemoryRepository;

import presentation.puerto.PuertoSalida;
import presentation.adaptador.ImpresorConsolaAdapter;
import presentation.controlador.ControladorFactura;
import presentation.estrategia.PresentacionFacturaA;
import presentation.estrategia.PresentacionFacturaB;
import presentation.estrategia.PresentacionResumen;
import presentation.AppConsola;

public class Main {
    public static void main(String[] args) {
        ProductoRepository repoProducto = new ProductoInMemoryRepository();
        ServicioProducto servicioProducto = new ServicioProducto(repoProducto);

        servicioProducto.crearProducto("Teclado", 2500, Iva.GENERAL);
        servicioProducto.crearProducto("Monitor", 1000, Iva.GENERAL);
        servicioProducto.crearProducto("Servicio", 5000, Iva.REDUCIDA);
        servicioProducto.crearProducto("Leche", 1000, Iva.EXENTO);

        FacturaRepository repoFactura = new FacturaInMemoryRepository();
        CalculadoraTotales calculadora = new CalculadoraTotalesEstandar();
        ServicioFactura servicioFactura = new ServicioFactura(repoFactura, repoProducto, calculadora);

        Map<TipoFactura, PresentacionResumen> presentaciones = Map.of(
            TipoFactura.A, new PresentacionFacturaA(),
            TipoFactura.B, new PresentacionFacturaB()
        );

        PuertoSalida puertoSalida = new ImpresorConsolaAdapter(presentaciones);
        ControladorFactura controlador = new ControladorFactura(servicioFactura, puertoSalida);

        new AppConsola(controlador).run();
    }
}