package TP4.Ejercicio3.Parte_mala_practica;
// Superclase con atributos expuestos a la jerarquía
class Archivo {
    protected String nombre;
    protected double pesoEnMB;

    public Archivo(String nombre, double pesoEnMB) {
        this.nombre = nombre;
        this.pesoEnMB = pesoEnMB;
        System.out.println("creando archivo genérico...");
    }
}

// Subclase que hereda los atributos protegidos
class ArchivoVideo extends Archivo {
    private int duracionEnSegundos;

    public ArchivoVideo(String nombre, double pesoEnMB, int duracionEnSegundos) {
        super(nombre, pesoEnMB);
        this.duracionEnSegundos = duracionEnSegundos;
        System.out.println("creando archivo de video...");
    }

    // Método que rompe las invariantes de clase debido al acceso directo
    public void forzarValoresInvalidos() {
        // Violación directa del encapsulamiento: no hay filtros ni validaciones
        this.nombre = null;       // Estado inválido: Nombre nulo
        this.pesoEnMB = -450.5;   // Estado inválido: Peso negativo
    }
}

// Clase de ejecución (Simula el mismo paquete)
public class MainMalaPractica {
    public static void main(String[] args) {
        ArchivoVideo video = new ArchivoVideo("clase_herencia.mp4", 120.0, 3600);
        
        // Ejecución del método que corrompe el estado interno
        video.forzarValoresInvalidos();
        
        System.out.println("--- ESTADO CORRUMPIDO ---");
        System.out.println("Nombre: " + video.nombre);
        System.out.println("Peso: " + video.pesoEnMB + " MB");
    }
}