package com.smartticket.domain.pasarela;

import com.smartticket.domain.enumeracion.ResultadoPago;

/**
 * Lo que la pasarela responde. Viaja desde el adaptador hasta el caso de uso.
 */
public record RespuestaPasarela(ResultadoPago resultado, String referencia, String motivo) {

    public boolean aprobada() {
        return resultado == ResultadoPago.APROBADO;
    }
}