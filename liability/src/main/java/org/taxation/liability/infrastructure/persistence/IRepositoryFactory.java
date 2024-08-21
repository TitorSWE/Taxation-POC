package org.taxation.liability.infrastructure.persistence;

public interface IRepositoryFactory<T extends ProjectedEntity> {
    IRepository<T> createRepository();
}
