package TP3.Ejercicio4.application;

import java.util.List;

import TP3.Ejercicio4.domain.entities.Cancha;
import TP3.Ejercicio4.domain.ports.Repository;

public class CanchaService {
    private Repository<Cancha> repo;
    private int nextCanchaId = 1;

    public CanchaService(Repository<Cancha> repo){
        this.repo = repo;
    }

    public Cancha createCancha(){
        int id = nextCanchaId++;
        Cancha cancha = Cancha.create(id);
        this.repo.add(cancha);

        return cancha;
    }

    public List<Cancha> getAll(){
        return this.repo.getAll();
    }
}
