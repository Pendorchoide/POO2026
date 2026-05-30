public class Turno {
    private String nombrePersona;
    private int hora;

    // Constructor
    public Turno(String nombrePersona, int hora) {
        this.nombrePersona = nombrePersona;
        this.hora = hora;
    }

    // Getters
    public String getNombrePersona() {
        return nombrePersona;
    }

    public int getHora() {
        return hora;
    }

    // toString para visualización
    @Override
    public String toString() {
        return hora + ":00 - " + nombrePersona;
    }
}
