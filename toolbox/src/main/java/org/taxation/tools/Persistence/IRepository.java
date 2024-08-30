package org.taxation.tools.Persistence;

import java.util.List;

public interface IRepository<T extends ProjectedEntity> {
    void save(T projectedEntity);
    T findById(String id);
    List<T> findAll();
}
