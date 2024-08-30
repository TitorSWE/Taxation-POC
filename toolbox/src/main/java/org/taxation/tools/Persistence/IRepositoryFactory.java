package org.taxation.tools.Persistence;

public interface IRepositoryFactory<T extends ProjectedEntity> {
    IRepository<T> createRepository();
}
