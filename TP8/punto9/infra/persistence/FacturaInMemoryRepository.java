package infra.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.factura.Factura;
import domain.factura.FacturaRepository;

public class FacturaInMemoryRepository implements FacturaRepository {
    private final Map<String, Factura> facturas = new HashMap<>();

    @Override
    public Factura guardarFactura(Factura factura) {
        this.facturas.put(String.valueOf(factura.hashCode()), factura);
        return factura;
    }

    @Override
    public List<Factura> obtenerFacturas() {
        return new ArrayList<>(this.facturas.values());
    }

    @Override
    public Factura obtenerFacturaPorId(String id) {
        return this.facturas.get(id);
    }

    @Override
    public boolean eliminarFactura(String id) {
        return this.facturas.remove(id) != null;
    }
}