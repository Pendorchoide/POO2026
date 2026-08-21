package Envio;
import Envio.interfaces.*;

public abstract class Envio {
    private double peso;

    public Envio(double peso) {
        this.peso = peso;
    }

    public double getPeso() {
        return this.peso;
    }

    abstract public double calcularCosto();
    public abstract void aceptar(IEnvioVisitor visitor);
}