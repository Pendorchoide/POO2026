package com.smartticket.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Los 9 escenarios de las tres Historias de Usuario contra la aplicacion real.
 *
 * <p>El contrato son los <b>mensajes exactos</b> y los <b>codigos HTTP</b> que pide
 * la HU. Si un mensaje cambia, la HU deja de estar implementada.</p>
 */
@SpringBootTest
@AutoConfigureMockMvc
class HistoriasDeUsuarioTest {

    @Autowired
    private MockMvc mvc;

    private final ObjectMapper om = new ObjectMapper();

    /** Un escenario por test: evento propio, asi los conteos no se mezclan. */
    private record Escenario(long eventoId, long plateaId, long vipId, long clienteId) {
    }

    // ---------------------------------------------------------------- helpers

    private Escenario montar() throws Exception {
        long lugarId = crear("/api/v1/lugares",
                Map.of("nombre", "Estadio Unico", "ciudad", "La Plata"));
        long eventoId = crear("/api/v1/eventos", Map.of(
                "nombre", "Rock Fest", "fechaEvento", "2026-12-31",
                "lugar", Map.of("id", lugarId)));
        long plateaId = crear("/api/v1/sectores", Map.of(
                "nombre", "Platea", "capacidadMaxima", 100, "lugar", Map.of("id", lugarId)));
        long vipId = crear("/api/v1/sectores", Map.of(
                "nombre", "VIP", "capacidadMaxima", 100, "lugar", Map.of("id", lugarId)));
        crear("/api/v1/precios-evento",
                Map.of("precio", 12000, "eventoId", eventoId, "sectorId", plateaId));
        crear("/api/v1/precios-evento",
                Map.of("precio", 30000, "eventoId", eventoId, "sectorId", vipId));
        long clienteId = crear("/api/v1/clientes", Map.of(
                "nombre", "Ana", "apellido", "Perez",
                "email", "ana-" + System.nanoTime() + "@mail.com"));
        return new Escenario(eventoId, plateaId, vipId, clienteId);
    }

    private List<Long> crearEntradas(long eventoId, long sectorId, int cantidad) throws Exception {
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            ids.add(crear("/api/v1/entradas", Map.of(
                    "qrCode", "QR-" + UUID.randomUUID(),
                    "estado", "DISPONIBLE",
                    "evento", Map.of("id", eventoId),
                    "sector", Map.of("id", sectorId))));
        }
        return ids;
    }

    private long crear(String uri, Object payload) throws Exception {
        MvcResult result = mvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(payload)))
                .andExpect(status().isCreated())
                .andReturn();
        return om.readTree(json(result)).get("id").asLong();
    }

    private JsonNode cotizar(long eventoId, long sectorId, int cantidad) throws Exception {
        MvcResult result = mvc.perform(post("/api/v1/cotizaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(Map.of(
                                "eventoId", eventoId,
                                "lineas", List.of(Map.of("sectorId", sectorId, "cantidad", cantidad))))))
                .andExpect(status().isOk())
                .andReturn();
        return om.readTree(json(result));
    }

    private ResultActions cotizarSinAssert(long eventoId, long sectorId, int cantidad) throws Exception {
        return mvc.perform(post("/api/v1/cotizaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(Map.of(
                                "eventoId", eventoId,
                                "lineas", List.of(Map.of("sectorId", sectorId, "cantidad", cantidad))))));
    }

    private ResultActions pagar(long eventoId, long clienteId, long sectorId, int cantidad, String tarjeta)
            throws Exception {
        return mvc.perform(post("/api/v1/pagos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(Map.of(
                                "clienteId", clienteId,
                                "eventoId", eventoId,
                                "lineas", List.of(Map.of("sectorId", sectorId, "cantidad", cantidad)),
                                "medioPago", "TARJETA_CREDITO",
                                "tarjeta", Map.of(
                                        "numero", tarjeta,
                                        "titular", "Ana Perez",
                                        "vencimiento", "12/29",
                                        "cvv", "123")))));
    }

    /** Paga una cantidad con tarjeta valida y devuelve el cuerpo de la respuesta. */
    private JsonNode pagarExitoso(long eventoId, long clienteId, long sectorId, int cantidad)
            throws Exception {
        return om.readTree(json(pagar(eventoId, clienteId, sectorId, cantidad, "4111111111111111")
                .andExpect(status().isCreated())
                .andReturn()));
    }

    /** Hora de ingreso tal como quedo persistida en la base. */
    private String fechaIngresoPersistida(long entradaId) throws Exception {
        String body = mvc.perform(get("/api/v1/entradas/" + entradaId))
                .andExpect(status().isOk()).andReturn()
                .getResponse().getContentAsString(StandardCharsets.UTF_8);
        return om.readTree(body).get("fechaIngreso").asText();
    }

    private String disponibles(long eventoId, long sectorId) throws Exception {
        return mvc.perform(get("/api/v1/entradas/disponibles")
                        .param("eventoId", String.valueOf(eventoId))
                        .param("sectorId", String.valueOf(sectorId)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    }

    private String validar(String qrCode) throws Exception {
        return mvc.perform(post("/api/v1/acceso/validar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(Map.of("qrCode", qrCode))))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    }

    private String json(MvcResult result) throws Exception {
        return result.getResponse().getContentAsString(StandardCharsets.UTF_8);
    }

    // ------------------------------------------------------------- HU1: cotizacion

    @Test
    void hu1_cotizacionExitosaConDisponibilidad() throws Exception {
        Escenario e = montar();
        crearEntradas(e.eventoId(), e.plateaId(), 3);

        JsonNode r = cotizar(e.eventoId(), e.plateaId(), 2);

        assertThat(r.get("eventoNombre").asText()).isEqualTo("Rock Fest");
        assertThat(r.get("total").decimalValue()).isEqualByComparingTo("24000");
        assertThat(r.get("subtotal").decimalValue()).isEqualByComparingTo("24000");
        JsonNode linea = r.get("lineas").get(0);
        assertThat(linea.get("precioUnitario").decimalValue()).isEqualByComparingTo("12000");
        assertThat(linea.get("cantidad").asInt()).isEqualTo(2);
        assertThat(linea.get("disponibles").asInt()).isEqualTo(3);
        assertThat(linea.get("sectorNombre").asText()).isEqualTo("Platea");
    }

    @Test
    void hu1_rechazoPorFaltaDeDisponibilidad() throws Exception {
        Escenario e = montar();
        crearEntradas(e.eventoId(), e.vipId(), 1);

        mvc.perform(post("/api/v1/cotizaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(Map.of(
                                "eventoId", e.eventoId(),
                                "lineas", List.of(Map.of("sectorId", e.vipId(), "cantidad", 3))))))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.mensaje")
                        .value("Capacidad insuficiente o entradas no disponibles"));
    }

    @Test
    void hu1_rechazoPorCantidadInvalida() throws Exception {
        Escenario e = montar();
        crearEntradas(e.eventoId(), e.plateaId(), 5);

        mvc.perform(post("/api/v1/cotizaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(Map.of(
                                "eventoId", e.eventoId(),
                                "lineas", List.of(Map.of("sectorId", e.plateaId(), "cantidad", 0))))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje")
                        .value("La cantidad de entradas solicitadas debe ser mayor a cero"));

        cotizarSinAssert(e.eventoId(), e.plateaId(), -1)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje")
                        .value("La cantidad de entradas solicitadas debe ser mayor a cero"));
    }

    // ------------------------------------------------------------- HU2: pago

    @Test
    void hu2_pagoExitosoEmiteLosQr() throws Exception {
        Escenario e = montar();
        List<Long> entradas = crearEntradas(e.eventoId(), e.plateaId(), 3);

        JsonNode r = pagarExitoso(e.eventoId(), e.clienteId(), e.plateaId(), 2);

        assertThat(r.get("estadoVenta").asText()).isEqualTo("PAGADA");
        assertThat(r.get("pasarelaResultado").asText()).isEqualTo("APROBADO");
        assertThat(r.get("codigoVenta").asText()).startsWith("VTA-");
        assertThat(r.get("total").decimalValue()).isEqualByComparingTo("24000");
        assertThat(r.get("entradas")).hasSize(2);
        for (JsonNode emitida : r.get("entradas")) {
            assertThat(emitida.get("estado").asText()).isEqualTo("EMITIDA");
            assertThat(emitida.get("qrCode").asText()).isNotBlank();
        }
        assertThat(disponibles(e.eventoId(), e.plateaId())).isEqualTo("1");

        JsonNode entrada = om.readTree(mvc.perform(
                        get("/api/v1/entradas/" + entradas.get(0)))
                .andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString(StandardCharsets.UTF_8));
        assertThat(entrada.get("estado").asText()).isEqualTo("EMITIDA");
        assertThat(entrada.hasNonNull("venta")).isTrue();
        assertThat(entrada.get("venta").get("estado").asText()).isEqualTo("PAGADA");
    }

    @Test
    void hu2_pagoDenegadoPorLaPasarelaNoDejaRastro() throws Exception {
        Escenario e = montar();
        List<Long> entradas = crearEntradas(e.eventoId(), e.plateaId(), 2);

        pagar(e.eventoId(), e.clienteId(), e.plateaId(), 1, "4111111111110000")
                .andExpect(status().isPaymentRequired())
                .andExpect(jsonPath("$.mensaje").value("Pago denegado"));

        // No se emite nada: las entradas siguen DISPONIBLE y no nace ninguna venta.
        for (Long id : entradas) {
            JsonNode entrada = om.readTree(mvc.perform(get("/api/v1/entradas/" + id))
                    .andExpect(status().isOk()).andReturn().getResponse()
                    .getContentAsString(StandardCharsets.UTF_8));
            assertThat(entrada.get("estado").asText()).isEqualTo("DISPONIBLE");
        }
        String ventas = json(mvc.perform(get("/api/v1/ventas")
                        .param("clienteId", String.valueOf(e.clienteId())))
                .andExpect(status().isOk()).andReturn());
        assertThat(om.readTree(ventas).size()).isZero();
        assertThat(disponibles(e.eventoId(), e.plateaId())).isEqualTo("2");
    }

    @Test
    void hu2_seAbortaPorDisponibilidadAntesDeConsultarLaPasarela() throws Exception {
        Escenario e = montar();
        crearEntradas(e.eventoId(), e.plateaId(), 1);

        // Primer pago: consume la unica entrada.
        pagar(e.eventoId(), e.clienteId(), e.plateaId(), 1, "4111111111111111")
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.estadoVenta").value("PAGADA"));

        // Segundo: quedo sin disponibilidad, asi que no debe llegar a la pasarela.
        pagar(e.eventoId(), e.clienteId(), e.plateaId(), 1, "4111111111111111")
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.mensaje")
                        .value("Los lugares seleccionados ya no se encuentran disponibles"));

        // Prueba de que no llego: solo existe la venta del primer intento.
        String ventas = json(mvc.perform(get("/api/v1/ventas")
                        .param("clienteId", String.valueOf(e.clienteId())))
                .andExpect(status().isOk()).andReturn());
        assertThat(om.readTree(ventas).size()).isEqualTo(1);
    }

    // ------------------------------------------------------------- HU3: acceso

    @Test
    void hu3_ingresoExitoso() throws Exception {
        Escenario e = montar();
        crearEntradas(e.eventoId(), e.plateaId(), 1);

        JsonNode pago = pagarExitoso(e.eventoId(), e.clienteId(), e.plateaId(), 1);
        String qr = pago.get("entradas").get(0).get("qrCode").asText();

        JsonNode r = om.readTree(validar(qr));

        assertThat(r.get("resultado").asText()).isEqualTo("ACCESO_PERMITIDO");
        assertThat(r.get("mensaje").asText()).isEqualTo("Acceso Permitido");
        assertThat(r.get("estado").asText()).isEqualTo("UTILIZADA");
        assertThat(r.get("fechaIngreso").asText()).isNotBlank();
        assertThat(r.get("eventoNombre").asText()).isEqualTo("Rock Fest");
    }

    @Test
    void hu3_rechazoPorReutilizacionDelQr() throws Exception {
        Escenario e = montar();
        crearEntradas(e.eventoId(), e.plateaId(), 1);

        JsonNode pago = pagarExitoso(e.eventoId(), e.clienteId(), e.plateaId(), 1);
        long entradaId = pago.get("entradas").get(0).get("entradaId").asLong();
        String qr = pago.get("entradas").get(0).get("qrCode").asText();

        JsonNode primero = om.readTree(validar(qr));
        assertThat(primero.get("resultado").asText()).isEqualTo("ACCESO_PERMITIDO");

        String horaRegistrada = fechaIngresoPersistida(entradaId);

        // El segundo escaneo es rechazado...
        mvc.perform(post("/api/v1/acceso/validar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(Map.of("qrCode", qr))))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.mensaje").value("Entrada ya utilizada"));

        // ...y no piso la hora del primer ingreso.
        assertThat(fechaIngresoPersistida(entradaId)).isEqualTo(horaRegistrada);
    }

    @Test
    void hu3_rechazoPorQrInexistente() throws Exception {
        mvc.perform(post("/api/v1/acceso/validar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(Map.of("qrCode", "QR-FALSO-123"))))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje").value("Ticket Inválido o Inexistente"));
    }

    @Test
    void hu3_rechazoPorEntradaNoPagada() throws Exception {
        Escenario e = montar();
        List<Long> entradas = crearEntradas(e.eventoId(), e.plateaId(), 1);
        String qr = om.readTree(mvc.perform(get("/api/v1/entradas/" + entradas.get(0)))
                .andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString(StandardCharsets.UTF_8)).get("qrCode").asText();

        mvc.perform(post("/api/v1/acceso/validar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(Map.of("qrCode", qr))))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.mensaje").value("Ticket no emitido / Falta de pago"));
    }
}