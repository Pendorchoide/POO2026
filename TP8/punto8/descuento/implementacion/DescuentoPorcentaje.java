package descuento.implementacion;

import descuento.interfaz.Descuento;

public abstract class DescuentoPorcentaje implements Descuento {
    private final double porcentaje;

    public DescuentoPorcentaje(double porcentaje) {
        if (porcentaje < 0.0 || porcentaje > 1.0) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0.0 y 1.0");
        }
        this.porcentaje = porcentaje;
    }

    @Override
    public double aplicar(double total) {
        return total * this.porcentaje;
    }
}
