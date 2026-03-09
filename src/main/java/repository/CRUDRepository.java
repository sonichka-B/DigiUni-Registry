package repository;

import java.util.List;

public interface CRUDRepository<T> {
    void add(T entity);
    void delete(T entity);
    T findById(String id);
    T findByName(String name);
    List<T> findAll();

}
