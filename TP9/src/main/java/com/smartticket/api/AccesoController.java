package com.smartticket.api;

import com.smartticket.api.dto.AccesoRequest;
import com.smartticket.api.dto.AccesoResponse;
import com.smartticket.domain.servicio.AccesoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CU 03 - Control de acceso en puerta.
 *
 * <p>SRP: el acomodador manda un QR y recibe una decision. El controller no
 * compara estados ni graba horas: eso lo hace {@link AccesoService}.</p>
 */
@RestController
@RequestMapping("/api/v1/acceso")
@Tag(name = "Acceso", description = "Validacion del QR en la puerta de ingreso")
public class AccesoController {

    private final AccesoService accesoService;

    public AccesoController(AccesoService accesoService) {
        this.accesoService = accesoService;
    }

    @Operation(
            summary = "Valida un QR escaneado",
            description = "Si la entrada esta EMITIDA la pasa a UTILIZADA, fija la hora de ingreso "
                    + "y responde Acceso Permitido. Si ya se uso, no esta emitida o no existe, rechaza.")
    @ApiResponse(responseCode = "200", description = "Acceso Permitido")
    @ApiResponse(responseCode = "404", description = "Ticket Invalido o Inexistente")
    @ApiResponse(responseCode = "409",
            description = "Entrada ya utilizada, o Ticket no emitido / Falta de pago")
    @PostMapping("/validar")
    public ResponseEntity<AccesoResponse> validar(@Valid @RequestBody AccesoRequest request) {
        return ResponseEntity.ok(accesoService.validarIngreso(request.qrCode()));
    }
}