package org.taxation.liability.infrastructure.persistence;

import java.util.List;

public interface IRepository<T extends ProjectedEntity> {
    void save(T projectedEntity);
    T findById(String id);
    List<T> findAll();
}
