package org.taxation.liability.infrastructure.persistence;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryRepository<T extends ProjectedEntity> implements IRepository<T> {
    private final Map<String, T> store = new ConcurrentHashMap<>();

    @Override
    public void save(T projectedEntity) {
        store.put(projectedEntity.getId(), projectedEntity);
    }

    @Override
    public T findById(String id) {
        return store.get(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }
}
