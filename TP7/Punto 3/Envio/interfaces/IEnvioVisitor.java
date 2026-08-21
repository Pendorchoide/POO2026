package Envio.interfaces;
import Envio.*;

public interface IEnvioVisitor {
    void visitar(CorreoLocalOCAEnvio oca);
    void visitar(FedExEnvio fedex);
    void visitar(AndreaniEnvio andreani);
}
