package presentation.estrategia;

import java.util.List;

import domain.calculo.ResumenFactura;

public interface PresentacionResumen {
    List<String[]> filasPie(ResumenFactura resumen);
}