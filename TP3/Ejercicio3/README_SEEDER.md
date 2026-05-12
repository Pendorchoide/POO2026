# Documentación de uso - Clase Seeder

## Descripción
La clase `Seeder.java` inicializa automáticamente todos los datos del Ejercicio 3 con:
- **2 Continentes**: América y Europa
- **16 Países**: 7 en América y 9 en Europa
- **Provincias/Estados/Departamentos**: Principales de cada país
- **Límites**: Establecidos los países limítrofes

## Estructura de Datos

Todos los datos se guardan usando **HashMap** con el nombre (en minúsculas) como clave:

- **Continentes**: `Map<String, Continente>` - clave = nombre del continente en minúsculas
- **Países**: `Map<String, Pais>` dentro de cada Continente - clave = nombre del país en minúsculas
- **Provincias**: `Map<String, Provincia>` dentro de cada País - clave = nombre de la provincia en minúsculas
- **Países Limítrofes**: `Map<String, Pais>` dentro de cada País - clave = nombre del país limítrofe en minúsculas

## Cómo usar

### Opción 1: Acceso simple a todos los datos
```java
// Inicializar todos los datos
Map<String, Continente> continentes = Seeder.inicializarDatos();

// Iterar sobre continentes
for (Continente continente : continentes.values()) {
    System.out.println(continente.getNombre());
}
```

### Opción 2: Acceso directo a elementos específicos
```java
Map<String, Continente> continentes = Seeder.inicializarDatos();

// Obtener un continente
Continente america = continentes.get("américa");

// Obtener un país del continente
Pais argentina = america.getPais("argentina");

// Obtener provincias del país
Map<String, Provincia> provincias = argentina.getProvincias();
Provincia buenosAires = provincias.get("buenos aires");
```

### Opción 3: Iteración completa con acceso a todos los datos
```java
Map<String, Continente> continentes = Seeder.inicializarDatos();

for (Continente continente : continentes.values()) {
    System.out.println("Continente: " + continente.getNombre());
    
    Map<String, Pais> paises = continente.getPaises();
    for (Pais pais : paises.values()) {
        System.out.println("  País: " + pais.getNombre());
        
        // Provincias
        Map<String, Provincia> provincias = pais.getProvincias();
        for (Provincia provincia : provincias.values()) {
            System.out.println("    - " + provincia.getNombre());
        }
        
        // Países limítrofes
        Map<String, Pais> limitrofes = pais.getPaisesLimitrofes();
        for (String nombrePais : limitrofes.keySet()) {
            System.out.println("    Limita con: " + nombrePais);
        }
    }
}
```

## Datos inicializados

### AMÉRICA
- **Argentina**: Buenos Aires, Córdoba, Santa Fe, Mendoza, Tucumán
- **Brasil**: São Paulo, Minas Gerais, Río de Janeiro, Bahía, Río Grande do Sul
- **Chile**: Región Metropolitana, Valparaíso, Bío-Bío, Los Lagos
- **Colombia**: Bogotá, Antioquia, Valle del Cauca, Atlántico
- **Perú**: Lima, Arequipa, Junín, Áncash
- **Venezuela**: Miranda, Zulia, Carabobo, Aragua
- **México**: Ciudad de México, Jalisco, Veracruz, Estado de México

### EUROPA
- **España**: Madrid, Barcelona, Valencia, Sevilla, Vizcaya
- **Francia**: Isla de Francia, Auvernia-Ródano-Alpes, Provenza-Alpes-Costa Azul, Nueva Aquitania
- **Alemania**: Berlín, Baviera, Renania del Norte-Westfalia, Baden-Wurtemberg
- **Italia**: Lazio, Lombardía, Campania, Sicilia
- **Reino Unido**: Inglaterra, Escocia, País de Gales, Irlanda del Norte
- **Portugal**: Lisboa, Porto, Algarve, Covilhã
- **Polonia**: Mazovia, Silesia, Gran Polonia, Pomerania Occidental
- **Suecia**: Estocolmo, Västra Götaland, Escania, Uppland
- **Suiza**: Zúrich, Berna, Basilea-Campestre, Ginebra

## Ventajas del uso de HashMap

✅ **Acceso O(1)**: Búsqueda rápida de elementos por nombre  
✅ **Evita duplicados**: Cada nombre es único dentro del contenedor  
✅ **Búsquedas eficientes**: Puedes recuperar directamente un país, provincia o vecino sin iterar  
✅ **Flexibilidad**: Fácil agregar, remover o actualizar elementos  

## Notas
- La clase Seeder NO modifica el código existente
- Todos los datos están relacionados correctamente (continentes → países → provincias)
- Los límites están establecidos bidireccionalamente
- Los nombres se almacenan en minúsculas como claves para evitar sensibilidad de mayúsculas

