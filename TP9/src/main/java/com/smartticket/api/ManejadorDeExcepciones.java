package com.smartticket.api;

import com.smartticket.domain.excepcion.ConflictoDeEstadoException;
import com.smartticket.domain.excepcion.PagoRechazadoException;
import com.smartticket.domain.excepcion.RecursoNoEncontradoException;
import com.smartticket.domain.excepcion.ValidacionDeNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Traduce las excepciones del dominio a respuestas HTTP con cuerpo predecible.
 * Sin esto, Spring devuelve un 500 generico cuando falta un recurso.
 *
 * <p>Es la unica pieza que conoce HTTP dentro de toda la capa de dominio: los
 * services lanzan excepciones con significado y aca se mapeean a codigos.</p>
 */
@RestControllerAdvice
public class ManejadorDeExcepciones {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> recursoNoEncontrado(RecursoNoEncontradoException ex) {
        return construir(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ConflictoDeEstadoException.class)
    public ResponseEntity<Map<String, Object>> conflicto(ConflictoDeEstadoException ex) {
        return construir(HttpStatus.CONFLICT, ex.getMessage());
    }

    /** Regla de negocio invalida detectada por un service (cantidad <= 0, sector fuera del evento...). */
    @ExceptionHandler(ValidacionDeNegocioException.class)
    public ResponseEntity<Map<String, Object>> validacionDeNegocio(ValidacionDeNegocioException ex) {
        return construir(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /** La pasarela dijo NO: nada se persistio. */
    @ExceptionHandler(PagoRechazadoException.class)
    public ResponseEntity<Map<String, Object>> pagoRechazado(PagoRechazadoException ex) {
        return construir(HttpStatus.PAYMENT_REQUIRED, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validacion(MethodArgumentNotValidException ex) {
        Map<String, String> campos = new LinkedHashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            campos.putIfAbsent(error.getField(), error.getDefaultMessage());
        }
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("status", HttpStatus.BAD_REQUEST.value());
        cuerpo.put("error", "Validacion fallida");
        cuerpo.put("campos", campos);
        return ResponseEntity.badRequest().body(cuerpo);
    }

    private ResponseEntity<Map<String, Object>> construir(HttpStatus estado, String mensaje) {
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("status", estado.value());
        cuerpo.put("error", estado.getReasonPhrase());
        cuerpo.put("mensaje", mensaje);
        return ResponseEntity.status(estado).body(cuerpo);
    }
}