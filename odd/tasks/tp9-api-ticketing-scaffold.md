# TP9 — Scaffold API de ticketing (Spring Boot + JPA + H2 + OpenAPI)

## Objetivo

Generar el esqueleto de una API REST de venta y control de acceso a eventos, con las
dependencias Maven declaradas, seis entidades JPA mapeadas y cuatro controladores
vacíos listos para que el usuario programe la lógica de negocio.

## Problema

TP9 arranca desde cero: no existe `pom.xml`, ni paquete base, ni configuración de
persistencia. Sin ese andamiaje no se puede ejercitar el dominio de ticketing.

## Por qué

El usuario pidió explícitamente el scaffold y dopingó el alcance: "NO escribas la lógica
de negocio" y "genera solo los @RestController vacíos". El valor de esta tarea es la
estructura y el mapeo, no el comportamiento.

## Alcance

- `pom.xml` con Spring Boot 4.1.1, Spring Data JPA, H2 en memoria, springdoc-openapi 3.1.1.
- 6 entidades JPA: `Lugar`, `Sector`, `Evento`, `Entrada`, `Cliente`, `Venta`.
- 2 enums de estado: `EstadoEntrada`, `EstadoPago`.
- 4 controladores vacíos: cotizar, iniciar-pago, webhook, escanear-acceso.
- Clase `@SpringBootApplication` y `application.yml` con H2 en memoria.

## Fuera de alcance (explícito)

- Cuerpos de método con lógica de negocio, validaciones, cálculos de precio.
- Repositorios `JpaRepository` (siguiente paso natural, no pedido).
- DTOs, mappers, capa de servicio, manejo de errores global.
- Tests.

## Restricciones

- Java 17 como baseline de `maven.compiler.release` (toolchain local: Java 26.0.1).
- Sin Lombok: POJOs con getters/setters explícitos y constructor sin argumentos
  protegido, coherente con el estilo de TP8.
- Sin `Co-Authored-By` ni atribución de IA.
- Commits en español, estilo Conventional Commits del repo.

## Decisiones y supuestos

| Decisión | Valor | Razón |
|---|---|---|
| Versión Spring Boot | 4.1.1 | Última estable en Maven Central. `4.2.0-M2` es milestone, no estable. |
| Versión springdoc | 3.1.1 | Serie 3.x es la obligatoria para Spring Boot 4.x. La serie 2.9.x es para Spring Boot 3.x. |
| Alternativa documentada | Spring Boot 3.5.16 + springdoc 2.9.1 | Si el material de cátedra o el profesor apunta a la línea 3.x, es un cambio de dos versiones en el pom. |
| Paquete base | `com.poo.tp9` | Alineado con el artifactId y el scope de commit `tp9`. |
| Paquetes | `domain` (entidades), `api` (controladores) | Estructura por capa simple, coherente con lo pedido. |
| Identidad de tablas | `GenerationType.IDENTITY` | Funciona de forma nativa con H2 sin configurar secuencias. |

## Tarea

- [x] **T1 — `pom.xml`**: parent `spring-boot-starter-parent` 4.1.1, starters
      web/data-jpa/validation/test, H2 en runtime, springdoc 3.1.1, plugin de Spring Boot.
- [x] **T2 — Entidades y enums**: 6 `@Entity` con atributos básicos, sin setters de
      negocio; `EstadoEntrada` y `EstadoPago` como enums planos.
- [x] **T3 — Controladores vacíos**: 4 `@RestController`, firmas Annotation +
      `UnsupportedOperationException` para que el cuerpo sea realmente vacío y compile.
- [x] **T4 — Bootstrap y configuración**: `@SpringBootApplication` en `com.poo.tp9` +
      `application.yml` con datasource H2 en memoria y `ddl-auto: update`.

## Criterios de aceptación

- [ ] El proyecto resuelve dependencias sin conflictos de versión. **Pendiente del
      usuario**: no se pudo ejecutar Maven para resolver el árbol.
- [ ] La aplicación arranca, crea el esquema H2 y expone `/swagger-ui.html`.
      **Pendiente del usuario**: requiere `mvn spring-boot:run`.
- [x] Las 6 entidades tienen tabla, clave primaria y relaciones mapeadas. Verificado por
      readback: `@Entity`, `@Table`, `@Id` + `@GeneratedValue(IDENTITY)`, constructor
      `protected` sin argumentos, getter y setter en los 35 campos.
- [x] Los 4 controladores exponen las rutas acordadas y compilan sin cuerpo de negocio.
      Verificado: cada método tiene exactamente un `throw new UnsupportedOperationException`.
- [x] Ninguna clase contiene cálculo, validación ni regla de negocio. Verificado por
      barrido de firmas: cero coincidencias, cero referencias a Lombok.

## Verificación

- **Compilación**: `mvn -q compile` — **no ejecutable en esta máquina, Maven no está
  instalado y no existe `~/.m2`**. La verificación real queda pendiente del usuario.
- **Readback estructural**: ejecutado por el padre. 15 archivos presentes, sin
  sobrantes; anotaciones JPA correctas; 4 controladores con rutas correctas; cero
  lógica de negocio; cero Lombok.
- **Revisión nativa (RDD)**: el switch está `on` (decided by default), pero el STATUS
  devolvió `immutable_review_transport_unsupported` con `next_action: stop`. Se preserva
  el resultado tipado de "no disponible" y no se desactiva el switch del usuario.
- **Arranque**: pendiente de `mvn spring-boot:run` en una máquina con Maven.

## Progreso

Documento creado antes de la primera escritura de código, según el protocolo ODD.
Ruta de implementación: **delegated direct** (un solo escritor, 11+ archivos no triviales
disparan el writer trigger). Las cuatro tareas quedaron completas en un único trabajo
delegado y verificadas por readback estructural del padre.

Sin commit: el usuario tiene su propia convención de commits manuales
(`add punto 9`, `fix punto 8`) y está en `main`. La decisión de branchear y commitear
es suya.

## Riesgos anotados para el usuario

1. `Venta` tiene `@OneToMany(cascade = CascadeType.ALL)` sin `orphanRemoval` ni método
   `addEntrada`. `ALL` incluye `REMOVE`, así que quitar una entrada de la colección no la
   desvincula. Es exactamente el andamiaje pedido; el helper de la colección es del
   usuario.
2. `setEntradas(null)` provoca fallo en el flush, porque la colección es el lado
   propietario con cascada.
3. Se usaron nombres de columna en `snake_case` (`capacidad_total`, `precio_base`,
   `monto_total`, `estado_pago`) para ser consistente con las FK ya especificadas. Si el
   material de cátedra espera el default camelCase de Hibernate, son esas 4 líneas.

## Próximo paso

Que el usuario programe los 4 controladores. Siguiente paso natural del dominio, no
pedido en esta tarea: repositorios `JpaRepository` para las 6 entidades.
