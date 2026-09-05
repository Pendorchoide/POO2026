package presentation.puerto;

import domain.calculo.ResumenFactura;

public interface PuertoSalida {
    void imprimir(ResumenFactura resumen);
}