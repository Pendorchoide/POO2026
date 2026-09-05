package infra.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.impuesto.Impuesto;
import domain.producto.Producto;
import domain.producto.ProductoRepository;

public class ProductoInMemoryRepository implements ProductoRepository {
    private final Map<String, Producto> productos = new HashMap<>();

    @Override
    public Producto guardarProducto(Producto producto) {
        productos.put(producto.getId(), producto);
        return producto;
    }

    @Override
    public List<Producto> obtenerProductos() {
        return new ArrayList<>(this.productos.values());
    }

    @Override
    public Producto obtenerProductoPorId(String id) {
        return this.productos.get(id);
    }

    @Override
    public Producto actualizarProducto(String id, String nombre, Impuesto iva, double precio) {
        Producto producto = new Producto(id, nombre, precio, iva);
        return guardarProducto(producto);
    }

    @Override
    public boolean eliminarProducto(String id) {
        return this.productos.remove(id) != null;
    }
}