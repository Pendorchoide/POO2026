package domain.descuento;

public final class DescuentoPorVolumen implements DescuentoPorCantidad {
    private final int unidadesMinimas;
    private final double porcentaje;

    public DescuentoPorVolumen(int unidadesMinimas, double porcentaje) {
        if (unidadesMinimas <= 0) {
            throw new IllegalArgumentException("Las unidades minimas deben ser mayores a 0");
        }
        if (porcentaje < 0.0 || porcentaje > 1.0) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0.0 y 1.0");
        }
        this.unidadesMinimas = unidadesMinimas;
        this.porcentaje = porcentaje;
    }

    @Override
    public double aplicar(int cantidad, double precioUnitario) {
        if (cantidad < unidadesMinimas) {
            return 0.0;
        }
        return cantidad * precioUnitario * this.porcentaje;
    }

    public int getUnidadesMinimas() { return unidadesMinimas; }
    public double getPorcentaje() { return porcentaje; }
}