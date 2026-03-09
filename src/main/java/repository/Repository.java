package repository;

import domain.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract class Repository<T> implements CRUDRepository<T>{

    protected List<T> generalDATA = new ArrayList<>();
    
    @Override
    public void add(T entity) {
        generalDATA.add(entity);
    }
    
    @Override
    public void delete(T entity) {
        generalDATA.remove(entity);
    }

    @Override
    public List<T> findAll() {
        return generalDATA;
    }
}
