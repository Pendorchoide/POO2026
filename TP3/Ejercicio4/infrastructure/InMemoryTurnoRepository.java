package TP3.Ejercicio4.infrastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import TP3.Ejercicio4.domain.entities.Turno;
import TP3.Ejercicio4.domain.ports.Repository;

public class InMemoryTurnoRepository implements Repository<Turno> {
    HashMap<Integer, Turno> turnos = new HashMap<>();

    @Override
    public void add(Turno turno) {
        this.turnos.put(turno.getId(), turno);
    }

    @Override
    public void delete(int id) {
        this.turnos.remove(id);
    }

    @Override
    public List<Turno> getAll() {
        return new ArrayList<>(this.turnos.values());
    }

    @Override
    public Turno getById(int id) {
        return this.turnos.get(id);
    }
}
