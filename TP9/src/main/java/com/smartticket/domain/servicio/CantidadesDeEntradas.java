package com.smartticket.domain.servicio;

import com.smartticket.domain.excepcion.ValidacionDeNegocioException;

/**
 * Regla compartida por cotizar y pagar: la cantidad pedida por linea debe ser
 * mayor a cero. Vive en un solo lado para que las dos HU respondan exactamente
 * lo mismo y no se duplique la condicion.
 */
final class CantidadesDeEntradas {

    /** Mensaje exigido por HU1, escenario 3. */
    static final String MENSAJE_CANTIDAD_INVALIDA =
            "La cantidad de entradas solicitadas debe ser mayor a cero";

    private CantidadesDeEntradas() {
    }

    static int validar(Integer cantidad) {
        if (cantidad == null || cantidad <= 0) {
            throw new ValidacionDeNegocioException(MENSAJE_CANTIDAD_INVALIDA);
        }
        return cantidad;
    }
}