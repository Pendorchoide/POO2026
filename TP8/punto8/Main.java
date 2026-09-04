import descuento.concreto.*;
import factura.*;
import impresor.adaptador.ImpresorConsolaAdapter;
import impresor.puerto.PuertoSalida;
import iva.*;
import producto.*;

public class Main {
    public static void main(String[] args) {
        Producto teclado = new Producto("Teclado", 2500, TasaIva.GENERAL);
        Producto monitor = new Producto("Monitor", 1000, TasaIva.GENERAL);
        Producto servicio = new Producto("Servicio", 5000, TasaIva.REDUCIDA);
        Producto leche = new Producto("Leche", 1000, TasaIva.EXENTO);

        DescuentoTresPorDos descuentoTresPorDos = new DescuentoTresPorDos();

        LineaFactura lineaTeclados = new LineaFactura(teclado, 3);
        lineaTeclados.agregarDescuentoPorCantidad(descuentoTresPorDos);

        LineaFactura lineaMonitores = new LineaFactura(monitor, 4);
        lineaMonitores.agregarDescuentoPorCantidad(descuentoTresPorDos);

        LineaFactura lineaServicios = new LineaFactura(servicio, 5);
        LineaFactura lineaLeches = new LineaFactura(leche, 6);

        Factura facturaA = new Factura(TipoFactura.A);
        facturaA.agregarLinea(lineaTeclados);
        facturaA.agregarLinea(lineaMonitores);
        facturaA.agregarLinea(lineaServicios);
        facturaA.agregarLinea(lineaLeches);

        Factura facturaB = new Factura(TipoFactura.B);
        facturaB.agregarLinea(lineaTeclados);
        facturaB.agregarLinea(lineaMonitores);
        facturaB.agregarLinea(lineaServicios);
        facturaB.agregarLinea(lineaLeches);
        facturaB.agregarDescuento(new DescuentoJubilados());

        PuertoSalida impresor = new ImpresorConsolaAdapter();
        impresor.imprimir(facturaA);
        impresor.imprimir(facturaB);
    }
}
