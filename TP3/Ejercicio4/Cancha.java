import java.util.ArrayList;

public class Cancha {
    private int numeroCancha;
    private ArrayList<Turno> turnos;

    // Constructor
    public Cancha(int numeroCancha) {
        this.numeroCancha = numeroCancha;
        this.turnos = new ArrayList<>();
    }

    // Getters
    public int getNumeroCancha() {
        return numeroCancha;
    }

    public ArrayList<Turno> getTurnos() {
        return turnos;
    }

    // Método para reservar un turno
    // Valida que no exista una reserva a esa hora
    // Retorna true si la reserva fue exitosa, false si ya existe una reserva en esa hora
    public boolean reservarTurno(Turno turno) {
        // Validar que no exista ya una reserva para esa hora
        for (Turno t : turnos) {
            if (t.getHora() == turno.getHora()) {
                return false; // Ya existe una reserva en esa hora
            }
        }
        
        // Si no existe, agregar el turno y retornar true
        turnos.add(turno);
        return true;
    }

    // Método para cancelar una reserva por hora
    public boolean cancelarReserva(int hora) {
        for (int i = 0; i < turnos.size(); i++) {
            if (turnos.get(i).getHora() == hora) {
                turnos.remove(i);
                return true;
            }
        }
        return false; // No se encontró una reserva en esa hora
    }

    // Método para ver los horarios disponibles y ocupados
    public void verHorarios() {
        System.out.println("\n=== Cancha " + numeroCancha + " ===");
        
        // Crear una lista de horas ocupadas
        ArrayList<Integer> horasOcupadas = new ArrayList<>();
        for (Turno t : turnos) {
            horasOcupadas.add(t.getHora());
        }
        
        // Mostrar todos los horarios de 14 a 22 (23 es el último que cierra)
        for (int hora = 14; hora < 23; hora++) {
            if (horasOcupadas.contains(hora)) {
                // Buscar la persona que reservó esa hora
                for (Turno t : turnos) {
                    if (t.getHora() == hora) {
                        System.out.println("  " + hora + ":00 - OCUPADO (" + t.getNombrePersona() + ")");
                        break;
                    }
                }
            } else {
                System.out.println("  " + hora + ":00 - LIBRE");
            }
        }
    }
}
