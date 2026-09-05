package domain.descuento;

public final class DescuentoPorCategoria extends DescuentoPorcentaje {
    private final String categoria;

    public DescuentoPorCategoria(String categoria, double porcentaje) {
        super(porcentaje);
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("La categoria no puede estar vacia");
        }
        this.categoria = categoria;
    }

    public String getCategoria() { return categoria; }
}