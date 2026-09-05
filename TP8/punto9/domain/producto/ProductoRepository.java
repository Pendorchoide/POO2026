package domain.producto;

import java.util.List;

import domain.impuesto.Impuesto;

public interface ProductoRepository {
    Producto guardarProducto(Producto producto);
    List<Producto> obtenerProductos();
    Producto obtenerProductoPorId(String id);
    Producto actualizarProducto(String id, String nombre, Impuesto iva, double precio);
    boolean eliminarProducto(String id);
}