package TP3.Ejercicio3;

import java.util.HashMap;
import java.util.Map;
import TP3.Ejercicio3.Continente;
import TP3.Ejercicio3.Pais;

public class Seeder {

    public static Map<String, Continente> inicializarDatos() {

        Map<String, Continente> continentes = new HashMap<>();

        // =========================
        // CONTINENTES
        // =========================

        Continente america = new Continente("America");
        Continente europa = new Continente("Europa");

        // =========================
        // AMÉRICA
        // =========================

        // ARGENTINA
        Pais argentina = new Pais("Argentina", "Buenos Aires", 2780400);
        argentina.agregarProvincia(new Provincia("Buenos Aires"));
        argentina.agregarProvincia(new Provincia("Córdoba"));
        argentina.agregarProvincia(new Provincia("Santa Fe"));
        argentina.agregarProvincia(new Provincia("Mendoza"));
        argentina.agregarProvincia(new Provincia("Tucumán"));

        // BRASIL
        Pais brasil = new Pais("Brasil", "Brasilia", 8515767);
        brasil.agregarProvincia(new Provincia("São Paulo"));
        brasil.agregarProvincia(new Provincia("Río de Janeiro"));
        brasil.agregarProvincia(new Provincia("Bahía"));
        brasil.agregarProvincia(new Provincia("Minas Gerais"));
        brasil.agregarProvincia(new Provincia("Paraná"));

        // CHILE
        Pais chile = new Pais("Chile", "Santiago", 756102);
        chile.agregarProvincia(new Provincia("Valparaíso"));
        chile.agregarProvincia(new Provincia("Biobío"));
        chile.agregarProvincia(new Provincia("Atacama"));
        chile.agregarProvincia(new Provincia("Los Lagos"));

        // URUGUAY
        Pais uruguay = new Pais("Uruguay", "Montevideo", 176215);
        uruguay.agregarProvincia(new Provincia("Montevideo"));
        uruguay.agregarProvincia(new Provincia("Canelones"));
        uruguay.agregarProvincia(new Provincia("Maldonado"));
        uruguay.agregarProvincia(new Provincia("Salto"));

        // PARAGUAY
        Pais paraguay = new Pais("Paraguay", "Asunción", 406752);
        paraguay.agregarProvincia(new Provincia("Central"));
        paraguay.agregarProvincia(new Provincia("Alto Paraná"));
        paraguay.agregarProvincia(new Provincia("Itapúa"));

        // BOLIVIA
        Pais bolivia = new Pais("Bolivia", "Sucre", 1098581);
        bolivia.agregarProvincia(new Provincia("La Paz"));
        bolivia.agregarProvincia(new Provincia("Santa Cruz"));
        bolivia.agregarProvincia(new Provincia("Cochabamba"));

        // PERÚ
        Pais peru = new Pais("Peru", "Lima", 1285216);
        peru.agregarProvincia(new Provincia("Lima"));
        peru.agregarProvincia(new Provincia("Arequipa"));
        peru.agregarProvincia(new Provincia("Cusco"));

        // COLOMBIA
        Pais colombia = new Pais("Colombia", "Bogotá", 1141748);
        colombia.agregarProvincia(new Provincia("Antioquia"));
        colombia.agregarProvincia(new Provincia("Cundinamarca"));
        colombia.agregarProvincia(new Provincia("Valle del Cauca"));

        // VENEZUELA
        Pais venezuela = new Pais("Venezuela", "Caracas", 916445);
        venezuela.agregarProvincia(new Provincia("Zulia"));
        venezuela.agregarProvincia(new Provincia("Carabobo"));
        venezuela.agregarProvincia(new Provincia("Miranda"));

        // MÉXICO
        Pais mexico = new Pais("Mexico", "Ciudad de México", 1964375);
        mexico.agregarProvincia(new Provincia("Jalisco"));
        mexico.agregarProvincia(new Provincia("Yucatán"));
        mexico.agregarProvincia(new Provincia("Nuevo León"));
        mexico.agregarProvincia(new Provincia("Veracruz"));

        // Agregar países a América
        america.agregarPais(argentina);
        america.agregarPais(brasil);
        america.agregarPais(chile);
        america.agregarPais(uruguay);
        america.agregarPais(paraguay);
        america.agregarPais(bolivia);
        america.agregarPais(peru);
        america.agregarPais(colombia);
        america.agregarPais(venezuela);
        america.agregarPais(mexico);

        // Límites
        argentina.agregarLimitrofe(chile);
        argentina.agregarLimitrofe(brasil);
        argentina.agregarLimitrofe(uruguay);
        argentina.agregarLimitrofe(paraguay);
        argentina.agregarLimitrofe(bolivia);

        brasil.agregarLimitrofe(argentina);
        brasil.agregarLimitrofe(uruguay);
        brasil.agregarLimitrofe(paraguay);
        brasil.agregarLimitrofe(colombia);
        brasil.agregarLimitrofe(venezuela);
        brasil.agregarLimitrofe(peru);

        chile.agregarLimitrofe(argentina);
        chile.agregarLimitrofe(peru);
        chile.agregarLimitrofe(bolivia);

        uruguay.agregarLimitrofe(argentina);
        uruguay.agregarLimitrofe(brasil);

        paraguay.agregarLimitrofe(argentina);
        paraguay.agregarLimitrofe(brasil);
        paraguay.agregarLimitrofe(bolivia);

        bolivia.agregarLimitrofe(argentina);
        bolivia.agregarLimitrofe(chile);
        bolivia.agregarLimitrofe(paraguay);
        bolivia.agregarLimitrofe(peru);

        peru.agregarLimitrofe(chile);
        peru.agregarLimitrofe(bolivia);
        peru.agregarLimitrofe(brasil);
        peru.agregarLimitrofe(colombia);

        colombia.agregarLimitrofe(venezuela);
        colombia.agregarLimitrofe(peru);
        colombia.agregarLimitrofe(brasil);

        venezuela.agregarLimitrofe(colombia);
        venezuela.agregarLimitrofe(brasil);

        // =========================
        // EUROPA
        // =========================

        // ESPAÑA
        Pais españa = new Pais("España", "Madrid", 505990);
        españa.agregarProvincia(new Provincia("Barcelona"));
        españa.agregarProvincia(new Provincia("Valencia"));
        españa.agregarProvincia(new Provincia("Sevilla"));

        // FRANCIA
        Pais francia = new Pais("Francia", "París", 551695);
        francia.agregarProvincia(new Provincia("Normandía"));
        francia.agregarProvincia(new Provincia("Bretaña"));
        francia.agregarProvincia(new Provincia("Occitania"));

        // ALEMANIA
        Pais alemania = new Pais("Alemania", "Berlín", 357588);
        alemania.agregarProvincia(new Provincia("Baviera"));
        alemania.agregarProvincia(new Provincia("Sajonia"));
        alemania.agregarProvincia(new Provincia("Hesse"));

        // ITALIA
        Pais italia = new Pais("Italia", "Roma", 301340);
        italia.agregarProvincia(new Provincia("Lombardía"));
        italia.agregarProvincia(new Provincia("Sicilia"));
        italia.agregarProvincia(new Provincia("Toscana"));

        // PORTUGAL
        Pais portugal = new Pais("Portugal", "Lisboa", 92212);
        portugal.agregarProvincia(new Provincia("Oporto"));
        portugal.agregarProvincia(new Provincia("Algarve"));

        // REINO UNIDO
        Pais reinoUnido = new Pais("Reino Unido", "Londres", 243610);
        reinoUnido.agregarProvincia(new Provincia("Inglaterra"));
        reinoUnido.agregarProvincia(new Provincia("Escocia"));
        reinoUnido.agregarProvincia(new Provincia("Gales"));

        // POLONIA
        Pais polonia = new Pais("Polonia", "Varsovia", 312696);
        polonia.agregarProvincia(new Provincia("Mazovia"));
        polonia.agregarProvincia(new Provincia("Silesia"));

        // SUECIA
        Pais suecia = new Pais("Suecia", "Estocolmo", 450295);
        suecia.agregarProvincia(new Provincia("Escania"));
        suecia.agregarProvincia(new Provincia("Uppland"));

        // SUIZA
        Pais suiza = new Pais("Suiza", "Berna", 41285);
        suiza.agregarProvincia(new Provincia("Zúrich"));
        suiza.agregarProvincia(new Provincia("Ginebra"));

        // Agregar países a Europa
        europa.agregarPais(españa);
        europa.agregarPais(francia);
        europa.agregarPais(alemania);
        europa.agregarPais(italia);
        europa.agregarPais(portugal);
        europa.agregarPais(reinoUnido);
        europa.agregarPais(polonia);
        europa.agregarPais(suecia);
        europa.agregarPais(suiza);

        // Límites Europa
        españa.agregarLimitrofe(francia);
        españa.agregarLimitrofe(portugal);

        francia.agregarLimitrofe(españa);
        francia.agregarLimitrofe(alemania);
        francia.agregarLimitrofe(italia);
        francia.agregarLimitrofe(suiza);

        alemania.agregarLimitrofe(francia);
        alemania.agregarLimitrofe(polonia);
        alemania.agregarLimitrofe(suiza);

        italia.agregarLimitrofe(francia);
        italia.agregarLimitrofe(suiza);

        portugal.agregarLimitrofe(españa);

        polonia.agregarLimitrofe(alemania);

        suiza.agregarLimitrofe(francia);
        suiza.agregarLimitrofe(alemania);
        suiza.agregarLimitrofe(italia);

        // =========================
        // GUARDAR EN MAP
        // =========================

        continentes.put(america.getNombre().toLowerCase(), america);
        continentes.put(europa.getNombre().toLowerCase(), europa);

        return continentes;
    }
}