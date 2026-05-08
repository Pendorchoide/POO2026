package TP3.Ejercicio4.application;

import java.util.List;
import java.util.stream.Collectors;

import TP3.Ejercicio4.domain.entities.Turno;
import TP3.Ejercicio4.domain.enums.Hora;
import TP3.Ejercicio4.domain.ports.Repository;

public class TurnoService {
    private Repository<Turno> repo;
    private int nextTurnoId = 1;

    public TurnoService(Repository<Turno> repo) {
        this.repo = repo;
    }

    public Turno create(int canchaId, String nombrePersona, Hora hour) {
        int id = nextTurnoId++;
        Turno turno = Turno.create(id, nombrePersona, canchaId, hour);
        this.repo.add(turno);
        return turno;
    }

    public List<Turno> getTurnosByCancha(int canchaId) {
        return this.repo.getAll()
            .stream()
            .filter(t -> (t.getCanchaId() == canchaId))
            .collect(Collectors.toList());
    }

    public void delete(int id) {
        this.repo.delete(id);
    }

    public boolean cancelarReserva(int canchaId, Hora hour) {
        var turnoOpt = getTurnosByCancha(canchaId).stream()
            .filter(t -> t.getCanchaId() == canchaId && t.getHour().equals(hour))
            .findFirst();
        if (turnoOpt.isPresent()) {
            delete(turnoOpt.get().getId());
            return true;
        }
        return false;
    }
}
