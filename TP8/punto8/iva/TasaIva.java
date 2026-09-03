package iva;

public enum TasaIva {
    GENERAL(0.21),
    REDUCIDA(0.15),
    ADICIONAL(0.27),
    EXENTO(0.0);

    private final double valor;

    private TasaIva(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }
}
