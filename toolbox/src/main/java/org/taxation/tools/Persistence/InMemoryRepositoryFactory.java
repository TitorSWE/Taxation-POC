package org.taxation.tools.Persistence;

import org.springframework.stereotype.Component;

@Component
public class InMemoryRepositoryFactory<T extends ProjectedEntity> implements IRepositoryFactory<T> {

    @Override
    public IRepository<T> createRepository() {
        return new InMemoryRepository<T>();
    }
}