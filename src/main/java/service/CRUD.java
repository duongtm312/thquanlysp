package service;

import java.util.List;

public interface CRUD<E> {
    List<E> getAll();

    void save(E e);

    E findById(int id);

    void update(int id, E e);

    void remove(int id);

}
