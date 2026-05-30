package TP4.Ejercicio1;
public class Motor {
    protected int cilindrada;
    protected String combustible;

    public Motor(int cilindrada, String combustible) {
        this.cilindrada = cilindrada;
        this.combustible = combustible;
    }

    public void encender() {
        System.out.println("Motor encendido.");
    }
}