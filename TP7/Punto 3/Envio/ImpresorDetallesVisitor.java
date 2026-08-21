package Envio;
import Envio.interfaces.*;

public class ImpresorDetallesVisitor implements IEnvioVisitor {

    private void imprimirComun(Envio envio) {
        System.out.println("\nCosto: " + envio.calcularCosto());
    }

    @Override
    public void visitar(CorreoLocalOCAEnvio oca) {
        imprimirComun(oca);
        System.out.println("Rastreo: No rastreable");
        System.out.println("Exportable: No exportable");
    }

    @Override
    public void visitar(FedExEnvio fedex) {
        imprimirComun(fedex);
        System.out.println("Rastreo: " + fedex.rastrearPaqueteSatelital());
        System.out.println("Exportable: " + fedex.generarReporteAduana());
    }

    @Override
    public void visitar(AndreaniEnvio andreani) {
        imprimirComun(andreani);
        System.out.println("Rastreo: No rastreable");
        System.out.println("Exportable: No exportable");
    }
}