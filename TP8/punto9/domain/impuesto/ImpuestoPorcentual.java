package domain.impuesto;

abstract class ImpuestoPorcentual implements Impuesto {
    private final double valor;

    ImpuestoPorcentual(double valor) {
        if (valor < 0.0 || valor > 1.0) {
            throw new IllegalArgumentException("La tasa debe estar entre 0.0 y 1.0");
        }
        this.valor = valor;
    }

    @Override
    public double calcular(double neto) {
        return neto * this.valor;
    }

    @Override
    public double getValor() {
        return this.valor;
    }
}