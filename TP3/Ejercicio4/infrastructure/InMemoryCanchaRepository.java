package TP3.Ejercicio4.infrastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import TP3.Ejercicio4.domain.entities.Cancha;
import TP3.Ejercicio4.domain.ports.Repository;

public class InMemoryCanchaRepository implements Repository<Cancha> {
    HashMap<Integer, Cancha> canchas = new HashMap<>();

    @Override
    public void add(Cancha cancha) {
        this.canchas.put(cancha.getId(), cancha);
    }

    @Override
    public void delete(int id) {
        this.canchas.remove(id);
    }

    @Override
    public List<Cancha> getAll() {
        return new ArrayList<>(this.canchas.values());
    }

    @Override
    public Cancha getById(int id) {
        return this.canchas.get(id);
    }
}
