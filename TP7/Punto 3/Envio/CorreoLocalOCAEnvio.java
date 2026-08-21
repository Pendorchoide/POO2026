package Envio;
import Envio.interfaces.*;

public class CorreoLocalOCAEnvio extends Envio {
    public CorreoLocalOCAEnvio(double peso) {
        super(peso);
    }

    @Override
    public double calcularCosto() {
        return this.getPeso() * 15.0;
    }

    @Override
    public void aceptar(IEnvioVisitor visitor) {
        visitor.visitar(this);
    }
}