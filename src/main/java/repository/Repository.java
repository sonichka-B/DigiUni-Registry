package repository;

import domain.Department;

import java.util.*;

abstract class Repository<T> implements CRUDRepository<T>{
    protected Map<String,T>generalDATA = new HashMap<>();

    protected abstract String getEntityId(T entity);

    @Override
    public void add(T entity) {
        if(entity !=null) {
            generalDATA.put(getEntityId(entity), entity);
        }
    }
    
    @Override
    public void delete(T entity) {
        if(entity !=null) {
            generalDATA.remove(getEntityId(entity));
        }
    }

    @Override
    public Optional<T> findById(String id) {
        if(id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return Optional.ofNullable(generalDATA.get(id));
    }

    @Override
    public Optional<T> findByName(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        return Optional.empty();
    }
    @Override
    public List<T> findAll() {
        return new ArrayList<>(generalDATA.values());
    }

    @Override
    public void addAll(List<T> entities) {
        if(entities !=null) {
            for (T entity : entities) {
                add(entity);
            }
        }
    }

}
