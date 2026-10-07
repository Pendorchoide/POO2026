package com.smartticket.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Verifica el flujo CRUD completo contra el contexto real de la aplicacion
 * (H2 en memoria + Hibernate), no contra mocks: lo que se prueba es la
 * respuesta HTTP que de verdad recibe el cliente.
 */
@SpringBootTest
@AutoConfigureMockMvc
class FlujoCrudTest {

    @Autowired
    private MockMvc mvc;

    /** Sobre y parsea JSON de las requests/responses: no hace falta que sea un bean. */
    private final ObjectMapper om = new ObjectMapper();

    // ---------------------------------------------------------------- helpers

    private long crearLugar(String nombre) throws Exception {
        return crear("/api/v1/lugares", Map.of("nombre", nombre, "ciudad", "La Plata"));
    }

    private long crear(String uri, Object payload) throws Exception {
        MvcResult result = mvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(payload)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString(uri + "/")))
                .andReturn();
        return leerId(result);
    }

    private long leerId(MvcResult result) throws Exception {
        String json = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        return om.readTree(json).get("id").asLong();
    }

    private String json(MvcResult result) throws Exception {
        return result.getResponse().getContentAsString(StandardCharsets.UTF_8);
    }

    // ------------------------------------------------------------------ tests

    @Test
    void lugarSePuedeCrearLeerActualizarYEliminar() throws Exception {
        long id = crearLugar("Teatro Gran Rex");

        mvc.perform(get("/api/v1/lugares/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Teatro Gran Rex"));

        mvc.perform(get("/api/v1/lugares"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.id == " + id + ")]").exists());

        mvc.perform(put("/api/v1/lugares/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(Map.of("nombre", "Teatro Renovado", "ciudad", "CABA"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Teatro Renovado"));

        mvc.perform(delete("/api/v1/lugares/" + id))
                .andExpect(status().isNoContent());

        mvc.perform(get("/api/v1/lugares/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void responde400ConLosCamposQueFallaron() throws Exception {
        mvc.perform(post("/api/v1/lugares")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(Map.of("nombre", ""))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.campos", hasKey("nombre")));
    }

    @Test
    void responde404CuandoElRecursoNoExiste() throws Exception {
        mvc.perform(get("/api/v1/eventos/999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje").value("Evento con id 999999 no existe"));
    }

    @Test
    void noSeEliminaUnLugarQueTieneSectores() throws Exception {
        long lugarId = crearLugar("Estadio Unico");

        long sectorId = crear("/api/v1/sectores", Map.of(
                "nombre", "Popular",
                "capacidadMaxima", 500,
                "lugar", Map.of("id", lugarId)));

        mvc.perform(delete("/api/v1/lugares/" + lugarId))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.mensaje").value(
                        "No se puede eliminar el Lugar " + lugarId + ": tiene sectores o eventos asociados"));

        mvc.perform(delete("/api/v1/sectores/" + sectorId))
                .andExpect(status().isNoContent());
    }

    @Test
    void laVentaSoloPasaDePendienteAOtraVezUnaVezCerrada() throws Exception {
        long clienteId = crear("/api/v1/clientes", Map.of(
                "nombre", "Ana", "apellido", "Perez", "email", "ana-" + System.nanoTime() + "@mail.com"));
        long ventaId = crear("/api/v1/ventas", Map.of(
                "codigo", "V-" + System.nanoTime(),
                "estado", "PENDIENTE",
                "cliente", Map.of("id", clienteId)));

        mvc.perform(patch("/api/v1/ventas/" + ventaId + "/estado").param("estado", "PAGADA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("PAGADA"))
                .andExpect(jsonPath("$.fechaCierre").isNotEmpty());

        mvc.perform(patch("/api/v1/ventas/" + ventaId + "/estado").param("estado", "CANCELADA"))
                .andExpect(status().isConflict());

        mvc.perform(delete("/api/v1/ventas/" + ventaId))
                .andExpect(status().isConflict());
    }

    @Test
    void elCodigoQrDeLaEntradaNoSePuedeRepetir() throws Exception {
        long lugarId = crearLugar("Anfiteatro");
        long eventoId = crear("/api/v1/eventos", Map.of(
                "nombre", "Festival", "fechaEvento", "2026-12-31", "lugar", Map.of("id", lugarId)));
        long sectorId = crear("/api/v1/sectores", Map.of(
                "nombre", "General", "capacidadMaxima", 200, "lugar", Map.of("id", lugarId)));
        String qr = "QR-" + System.nanoTime();

        long entradaId = crear("/api/v1/entradas", Map.of(
                "qrCode", qr,
                "estado", "DISPONIBLE",
                "evento", Map.of("id", eventoId),
                "sector", Map.of("id", sectorId)));

        mvc.perform(get("/api/v1/entradas/qr/" + qr))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(entradaId));

        mvc.perform(post("/api/v1/entradas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(Map.of(
                                "qrCode", qr,
                                "estado", "DISPONIBLE",
                                "evento", Map.of("id", eventoId),
                                "sector", Map.of("id", sectorId)))))
                .andExpect(status().isConflict());

        // Una entrada DISPONIBLE todavia no ingreso al estadio.
        mvc.perform(patch("/api/v1/entradas/" + entradaId + "/ingreso"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.mensaje").value(
                        "La entrada " + entradaId + " aun no fue emitida: no puede ingresar"));

        mvc.perform(get("/api/v1/entradas/disponibles")
                        .param("eventoId", String.valueOf(eventoId))
                        .param("sectorId", String.valueOf(sectorId)))
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse()
                        .getContentAsString(StandardCharsets.UTF_8)).isEqualTo("1"));
    }

    @Test
    void swaggerExponeTodosLosRecursos() throws Exception {
        MvcResult result = mvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andReturn();

        String doc = json(result);
        assertThat(doc).contains("\"openapi\"");

        List<String> requeridos = List.of(
                "/api/v1/lugares", "/api/v1/sectores", "/api/v1/eventos",
                "/api/v1/entradas", "/api/v1/clientes", "/api/v1/ventas",
                "/api/v1/cotizaciones", "/api/v1/pagos");
        for (String path : requeridos) {
            assertThat(doc)
                    .as("el path %s debe estar documentado en Swagger", path)
                    .contains("\"" + path + "\"");
        }
    }
}