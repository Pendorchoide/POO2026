package TP6;

public class Bicicleta implements ImpactoEcologico {

    private String marca;
    private String modelo;
    private double velocidadMaxima;
    private String tipo;
    
    public Bicicleta(String marca, String modelo, double velocidadMaxima, String tipo) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidadMaxima = velocidadMaxima;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }
    
    @Override
    public double getImpact() {
        // Las bicicletas no producen emisiones de CO2 durante su uso
        // Retorna 0 ya que no consumen combustibles fósiles
        return 0.0;
    }

    @Override
    public String toString() {
        return "Bicicleta " + marca + " " + modelo + " (Tipo: " + tipo + ", Vel. máx: " + velocidadMaxima + " km/h)";
    }
}
