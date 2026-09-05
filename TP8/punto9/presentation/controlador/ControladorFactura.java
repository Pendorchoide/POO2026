package presentation.controlador;

import app.ServicioFactura;
import app.dto.NuevaFacturaRequest;
import domain.calculo.ResumenFactura;
import presentation.puerto.PuertoSalida;

public final class ControladorFactura {
    private final ServicioFactura servicio;
    private final PuertoSalida puertoSalida;

    public ControladorFactura(ServicioFactura servicio, PuertoSalida puertoSalida) {
        this.servicio = servicio;
        this.puertoSalida = puertoSalida;
    }

    public void crearYMostrar(NuevaFacturaRequest request) {
        ResumenFactura resumen = this.servicio.resumen(this.servicio.crearFactura(request));
        this.puertoSalida.imprimir(resumen);
    }
}