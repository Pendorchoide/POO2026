package app;

import java.util.List;

import domain.impuesto.Impuesto;
import domain.producto.Producto;
import domain.producto.ProductoRepository;

public class ServicioProducto {
    private int idCounter = 0;
    private final ProductoRepository repo;

    public ServicioProducto(ProductoRepository repo) {
        this.repo = repo;
    }

    public Producto crearProducto(String nombre, double precio, Impuesto iva) {
        Producto producto = new Producto(String.valueOf(idCounter), nombre, precio, iva);
        this.idCounter++;
        
        return this.repo.guardarProducto(producto);
    }

    public List<Producto> obtenerProductos() {
        return this.repo.obtenerProductos();
    }

    public Producto obtenerProductoPorId(String id) {
        return this.repo.obtenerProductoPorId(id);
    }

    public Producto actulizarProducto(String id, String nombre, Impuesto iva, double precio) {
        return this.repo.actualizarProducto(id, nombre, iva, precio);
    }

    public boolean eliminarProducto(String id) {
        return this.repo.eliminarProducto(id);
    }
}