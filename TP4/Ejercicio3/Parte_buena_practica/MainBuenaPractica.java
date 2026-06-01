// Superclase con encapsulamiento hermético
class Archivo {
    private String nombre;
    private double pesoEnMB;

    public Archivo(String nombre, double pesoEnMB) {
        // Se reutilizan los setters para asegurar la validación desde la instanciación
        setNombre(nombre);
        setPesoEnMB(pesoEnMB);
        System.out.println("creando archivo genérico...");
    }

    // Setter protegido: Solo accesible para la clase y sus descendientes, protegiendo la invariante
    protected final void setNombre(String nombre) {
        // Cláusula de guarda para evitar estados inconsistentes
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: El nombre no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    protected final void setPesoEnMB(double pesoEnMB) {
        // Cláusula de guarda para asegurar lógica de negocio
        if (pesoEnMB <= 0) {
            throw new IllegalArgumentException("Error: El peso del archivo debe ser mayor a 0 MB.");
        }
        this.pesoEnMB = pesoEnMB;
    }

    // Getters públicos para permitir la lectura segura del estado
    public String getNombre() { return this.nombre; }
    public double getPesoEnMB() { return this.pesoEnMB; }
}

// Subclase que opera bajo el paradigma de paso de mensajes controlados
class ArchivoVideo extends Archivo {
    private int duracionEnSegundos;

    public ArchivoVideo(String nombre, double pesoEnMB, int duracionEnSegundos) {
        super(nombre, pesoEnMB);
        this.duracionEnSegundos = duracionEnSegundos;
        System.out.println("creando archivo de video...");
    }

    // Intento de modificación del estado a través de la interfaz permitida
    public void intentarModificacion(String nuevoNombre, double nuevoPeso) {
        // La subclase ya no accede a las variables; envía un mensaje al padre
        setNombre(nuevoNombre); 
        setPesoEnMB(nuevoPeso);
    }
}

// Clase de ejecución
public class MainBuenaPractica {
    public static void main(String[] args) {
        try {
            ArchivoVideo videoCorrecto = new ArchivoVideo("video_clase.mp4", 85.5, 180);
            
            System.out.println("--- Intentando asignación de datos inválidos ---");
            // Esta línea disparará la excepción controlada por la superclase
            videoCorrecto.intentarModificacion("", -20.0); 
            
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción capturada con éxito: " + e.getMessage());
            System.out.println("Resultado: El sistema rechazó la corrupción del estado del objeto.");
        }
    }
}