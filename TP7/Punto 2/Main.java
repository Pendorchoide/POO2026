import java.util.ArrayList;
import java.util.List;

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