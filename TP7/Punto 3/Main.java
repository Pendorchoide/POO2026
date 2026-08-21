import Envio.*;
import Envio.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Envio> envios = new ArrayList<>();
        envios.add(new CorreoLocalOCAEnvio(20));
        envios.add(new FedExEnvio(15));
        envios.add(new AndreaniEnvio(10));

        IEnvioVisitor impresor = new ImpresorDetallesVisitor();
        envios.forEach(e -> e.aceptar(impresor));
    }
}