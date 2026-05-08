package TP3.Ejercicio4.domain.ports;
import java.util.List;

public interface Repository<T> {
    void add(T entity);
    void delete(int id);
    List<T> getAll();
    T getById(int id);
}