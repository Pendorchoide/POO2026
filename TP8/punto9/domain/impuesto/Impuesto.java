package domain.impuesto;

public interface Impuesto {
    double calcular(double neto);
    double getValor();
}