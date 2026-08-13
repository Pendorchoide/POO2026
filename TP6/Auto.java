package TP6;

public class Auto implements ImpactoEcologico {
    
    private String marca;
    private String modelo;
    private double velocidadMaxima;
    private double consumoLitrosPor100km; // Consumo de combustible
    private double kmAnioPorPromedio; 

    public Auto(String marca, String modelo, double velocidadMaxima, double consumoLitrosPor100km, double kmAnioPorPromedio) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidadMaxima = velocidadMaxima;
        this.consumoLitrosPor100km = consumoLitrosPor100km;
        this.kmAnioPorPromedio = kmAnioPorPromedio;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public double getConsumoLitrosPor100km() {
        return consumoLitrosPor100km;
    }

    public double getKmAnioPromedio() {
        return kmAnioPorPromedio;
    }

    @Override
    public double getImpact() {
        // Impacto ecológico basado en consumo de combustible
        // 1 litro de combustible produce aproximadamente 2.31 kg de CO2
        double litrosAnuales = (kmAnioPorPromedio / 100.0) * consumoLitrosPor100km;
        return litrosAnuales * 2.31;
    }

    @Override
    public String toString() {
        return "Auto " + marca + " " + modelo + " (Vel. máx: " + velocidadMaxima + " km/h, Consumo: " + consumoLitrosPor100km + " L/100km)";
    }
}
