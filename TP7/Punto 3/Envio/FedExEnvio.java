package Envio;
import Envio.interfaces.*;

public class FedExEnvio extends Envio implements IRastreable, IExportable {
    public FedExEnvio(double peso) {
        super(peso);
    }

    @Override
    public double calcularCosto() {
        return (this.getPeso() * 50.0) + 100.0;
    }

    @Override
    public String rastrearPaqueteSatelital() {
        return "Coordenadas: -34.6037, -58.3816 (En transito)";
    }

    @Override
    public String generarReporteAduana() {
        return "Generando declaración jurada de exportación.";
    }

    @Override
    public void aceptar(IEnvioVisitor visitor) {
        visitor.visitar(this);
    }
}
