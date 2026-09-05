package domain.descuento;

public interface DescuentoPorCantidad {
    double aplicar(int cantidad, double precioUnitario);
}
