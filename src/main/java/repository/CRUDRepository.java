package repository;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T> {
    void add(T entity);
    void delete(T entity);
    Optional<T> findById(String id);
    List<T> findAll();

}
