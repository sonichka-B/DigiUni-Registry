package repository;

import domain.Department;

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
    public Optional<T> findById(String id) {
        if(id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return null;
    }

    @Override
    public Optional<T> findByName(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        return null;
    }
    @Override
    public List<T> findAll() {
        return new ArrayList<>(generalDATA);
    }

    @Override
    public void addAll(List<T> entities) {
        generalDATA.addAll(entities);
    }

}
