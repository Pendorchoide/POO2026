import java.util.ArrayList;
import java.util.List;

abstract class Envio {
    private double peso;

    public Envio(double peso) {
        this.peso = peso;
    }

    public double getPeso() {
        return this.peso;
    }

    public abstract double obtenerCosto();
}

class OCAEnvio extends Envio {
    public OCAEnvio(double peso) {
        super(peso);
    }

    @Override
    public double obtenerCosto() {
        return this.getPeso() * 15.0;
    }
}

class FedExEnvio extends Envio{
    public FedExEnvio(double peso) {
        super(peso);
    }

    @Override
    public double obtenerCosto() {
        return (this.getPeso() * 50.0) + 100.0;
    }
}

class AndreaniEnvio extends Envio {
    public AndreaniEnvio(double peso) {
        super(peso);
    }

    @Override
    public double obtenerCosto() {
        return this.getPeso() * 20.0;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Envio> envios = new ArrayList<>();
        envios.add(new OCAEnvio(20));
        envios.add(new FedExEnvio(15));
        envios.add(new AndreaniEnvio(10));

        envios.forEach(e -> {
            System.out.println(e.obtenerCosto());
        });
    }
}