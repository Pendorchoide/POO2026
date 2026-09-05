package domain.factura;

import java.util.List;

public interface FacturaRepository {
    Factura guardarFactura(Factura Factura);
    List<Factura> obtenerFacturas();
    Factura obtenerFacturaPorId(String id);
    boolean eliminarFactura(String id);
} 