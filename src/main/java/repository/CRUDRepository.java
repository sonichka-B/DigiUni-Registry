package repository;

import java.util.List;

public interface CRUDRepository<T> {
    void add(T entity);
    void delete(T entity);
    List<T> findAll();

}
