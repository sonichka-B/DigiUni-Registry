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
    public List<T> findAll() {
        return generalDATA;
    }
}
