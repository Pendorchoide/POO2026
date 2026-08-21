package Envio;
import Envio.interfaces.*;

public class AndreaniEnvio extends Envio {
    public AndreaniEnvio(double peso) {
        super(peso);
    }

    @Override
    public double calcularCosto() {
        return this.getPeso() * 20.0;
    }

    @Override
    public void aceptar(IEnvioVisitor visitor) {
        visitor.visitar(this);
    }
}
