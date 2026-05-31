public class Main {
    public static void main(String[] args) {
        // Crear una instancia de ArchivoVideo
        ArchivoVideo video = new ArchivoVideo("video.mp4", 25.5, 120);
    }
}

// Superclase Archivo
class Archivo {
    private String nombre;
    private double pesoEnMB;

    public Archivo(String nombre, double pesoEnMB) {
        this.nombre = nombre;
        this.pesoEnMB = pesoEnMB;
        System.out.println("creando archivo genérico...");
    }
}

// Subclase ArchivoVideo
class ArchivoVideo extends Archivo {
    private int duracionEnSegundos;

public ArchivoVideo(String nombre, double pesoEnMB, int duracionEnSegundos) {
        super(nombre, pesoEnMB);
        this.duracionEnSegundos = duracionEnSegundos;
        System.out.println("creando archivo de video...");
    }
}
