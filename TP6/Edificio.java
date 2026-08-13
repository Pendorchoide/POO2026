package TP6;

public class Edificio implements ImpactoEcologico {
    private String direccion;
    private double altura;
    private double area;
    private double consumoAnualGasNatural; // en metros cúbicos por año

    public Edificio(String direccion, double altura, double area, double consumoAnualGasNatural) {
        this.direccion = direccion;
        this.altura = altura;
        this.area = area;
        this.consumoAnualGasNatural = consumoAnualGasNatural;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getAltura() {
        return altura;
    }

    public double getArea() {
        return area;
    }

    public double getConsumoAnualGasNatural() {
        return consumoAnualGasNatural;
    }

    @Override
    public double getImpact() {
        // Impacto ecológico basado en consumo de gas natural
        // 1 m³ de gas natural produce aproximadamente 2.04 kg de CO2
        return consumoAnualGasNatural * 2.04;
    }

    @Override
    public String toString() {
        return "Edificio en " + direccion + " (Altura: " + altura + "m, Área: " + area + "m²)";
    }
    
}
