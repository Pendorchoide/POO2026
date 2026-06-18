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

    public Archivo(String nombre, double pesoEnMB) {           //Constructor con parámetros
        this.nombre = nombre;
        this.pesoEnMB = pesoEnMB;
        System.out.println("creando archivo genérico...");
    }

    public Archivo(String nombre, int pesoEnMB) {           //Constructor con parámetros
        this.nombre = nombre;
        this.pesoEnMB = pesoEnMB;
        System.out.println("creando archivo genérico...");
    }

    public Archivo(){                                           //Constructor sin parámetros
        System.out.println("creando archivo genérico (sin parametros)...");
    }

    public String imprimirInformacion() {                               // Metodo sin parámetros
        return "Archivo: " + nombre + ", Peso: " + pesoEnMB + " MB";
    }

    public String imprimirInformacion(String datoExtra) {       //metodo con un parámetro adicional
        return "Archivo: " + nombre + ", Peso: " + pesoEnMB + " MB, Dato Extra: " + datoExtra;
    }

        public String imprimirInformacion(int datoExtra) {       //metodo con un parámetro adicional
        return "Archivo: " + nombre + ", Peso: " + pesoEnMB + " MB, Dato Extra: " + datoExtra;
    }
}

// Subclase ArchivoVideo
class ArchivoVideo extends Archivo {
    private int duracionEnSegundos;

public ArchivoVideo(String nombre, double pesoEnMB, int duracionEnSegundos) {
        //super(nombre, pesoEnMB);
        this.duracionEnSegundos = duracionEnSegundos;
        System.out.println("creando archivo de video...");
    }
}
