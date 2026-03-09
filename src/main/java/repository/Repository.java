package repository;

import java.util.ArrayList;
import java.util.List;

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
    public T findById(String id) {
        if(id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return null;
    }

    @Override
    public T findByName(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        return null;
    }
    @Override
    public List<T> findAll() {
        return generalDATA;
    }
}
