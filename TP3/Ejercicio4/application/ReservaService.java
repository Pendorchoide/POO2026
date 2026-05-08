package TP3.Ejercicio4.application;

import TP3.Ejercicio4.domain.entities.Cancha;
import TP3.Ejercicio4.domain.enums.Hora;

public class ReservaService {
    private CanchaService canchaService;
    private TurnoService turnoService;

    public ReservaService(CanchaService canchaService, TurnoService turnoService) {
        this.canchaService = canchaService;
        this.turnoService = turnoService;
    }

    public boolean reservar(int canchaId, String nombrePersona, Hora hour) {
        if (!validarCancha(canchaId)) {
            throw new IllegalArgumentException("Cancha inválida. Solo se permiten canchas 1, 2 o 3");
        }

        if (!validarDisponibilidad(canchaId, hour)) {
            return false;
        }

        turnoService.create(canchaId, nombrePersona, hour);
        return true;
    }

    public boolean cancelarReserva(int canchaId, Hora hour) {
        if (!validarCancha(canchaId)) {
            throw new IllegalArgumentException("Cancha inválida. Solo se permiten canchas 1, 2 o 3");
        }

        return turnoService.cancelarReserva(canchaId, hour);
    }

    public boolean validarCancha(int canchaId) {
        return canchaId >= 1 && canchaId <= 3;
    }

    public boolean validarDisponibilidad(int canchaId, Hora hour) {
        return turnoService.getTurnosByCancha(canchaId).stream()
            .noneMatch(t -> t.getHour().equals(hour));
    }

    public java.util.List<Cancha> getCanchas() {
        return canchaService.getAll();
    }

    public java.util.List<TP3.Ejercicio4.domain.entities.Turno> getTurnosByCancha(int canchaId) {
        return turnoService.getTurnosByCancha(canchaId);
    }
}
