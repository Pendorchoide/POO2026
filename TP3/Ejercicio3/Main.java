package TP3.Ejercicio3;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Ya viene indexado por nombre
        Map<String, Continente> continentes = Seeder.inicializarDatos();

        // Mapa global de países
        Map<String, Pais> paises = new HashMap<>();

        for (Continente c : continentes.values()) {
            for (Pais p : c.getPaises().values()) {
                paises.put(p.getNombre().toLowerCase(), p);
            }
        }

        Scanner sc = new Scanner(System.in);

        // 1. Países de un continente
        System.out.println("Ingrese el nombre de un continente:");
        String nombreContinente = sc.nextLine();

        Continente cont = continentes.get(nombreContinente.toLowerCase());

        if (cont != null) {
            System.out.println("Países en " + cont.getNombre() + ":");
            for (Pais p : cont.getPaises().values()) {
                System.out.println("- " + p.getNombre());
            }
        } else {
            System.out.println("Continente no encontrado");
        }

        // 2. Provincias de un país
        System.out.println("\nIngrese el nombre de un país:");
        String nombrePais = sc.nextLine();

        Pais pais = paises.get(nombrePais.toLowerCase());

        if (pais != null) {
            System.out.println("Provincias de " + pais.getNombre() + ":");
            for (Provincia prov : pais.getProvincias().values()) {
                System.out.println("- " + prov.getNombre());
            }
        } else {
            System.out.println("País no encontrado");
        }

        // 3. Países limítrofes
        System.out.println("\nIngrese el nombre de un país para ver sus limítrofes:");
        String nombrePaisLim = sc.nextLine();

        Pais paisLim = paises.get(nombrePaisLim.toLowerCase());

        if (paisLim != null) {
            System.out.println("Países limítrofes de " + paisLim.getNombre() + ":");
            for (Pais p : paisLim.getPaisesLimitrofes().values()) {
                System.out.println("- " + p.getNombre());
            }
        } else {
            System.out.println("País no encontrado");
        }

        // 4. Países ordenados por superficie
        System.out.println("\nPresione una tecla para ver países ordenados por superficie...");
        sc.nextLine();
        List<Pais> listaPaises = new ArrayList<>(paises.values());

        listaPaises.sort((a, b) -> Float.compare(b.getSuperficie(), a.getSuperficie()));


        for (Pais p : listaPaises) {
            System.out.println(p.getNombre() + " - " + p.getSuperficie());
        }

        // 5. Comparación de superficies
        System.out.println("\nIngrese el primer país:");
        String p1 = sc.nextLine();

        System.out.println("Ingrese el segundo país:");
        String p2 = sc.nextLine();

        Pais pais1 = paises.get(p1.toLowerCase());
        Pais pais2 = paises.get(p2.toLowerCase());

        if (pais1 == null || pais2 == null) {
            System.out.println("Uno o ambos países no fueron encontrados");
        } else {
            if (pais1.getSuperficie() > pais2.getSuperficie()) {
                System.out.println(pais1.getNombre() + " es más grande que " + pais2.getNombre());
            } else if (pais1.getSuperficie() < pais2.getSuperficie()) {
                System.out.println(pais2.getNombre() + " es más grande que " + pais1.getNombre());
            } else {
                System.out.println("Tienen la misma superficie");
            }
        }

        sc.close();
    }
}