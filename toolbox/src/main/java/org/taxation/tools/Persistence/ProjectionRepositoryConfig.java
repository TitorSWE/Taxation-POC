package org.taxation.tools.Persistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProjectionRepositoryConfig<T extends ProjectedEntity> {

    @Value("${projection.repository.type}")
    private String repositoryType;

    @Bean
    @Primary
<<<<<<< HEAD
    public IRepositoryFactory<T> projectionRepositoryFactory(
            InMemoryRepositoryFactory<T> inMemoryFactory,
            MongoRepositoryFactory<T> mongoRepositoryFactory) {
        return switch (repositoryType.toLowerCase()) {
            case "in-memory" -> inMemoryFactory;
            case "mongo" -> mongoRepositoryFactory;
            default -> throw new IllegalArgumentException("Unknown repository type: " + repositoryType);
        };
=======
    public IRepositoryFactory<T> personProjectionRepositoryFactory(
            InMemoryRepositoryFactory<T> inMemoryFactory) {
        switch (repositoryType.toLowerCase()) {
            case "in-memory":
                return inMemoryFactory;
            default:
                throw new IllegalArgumentException("Unknown repository type: " + repositoryType);
        }
>>>>>>> 34f407bdf061ef716a6c180731a0f8926eb95942
    }

    @Bean
    @Primary
<<<<<<< HEAD
    public IRepository<T> projectionRepository(IRepositoryFactory<T> factory) {
=======
    public IRepository<T> personProjectionRepository(IRepositoryFactory<T> factory) {
>>>>>>> 34f407bdf061ef716a6c180731a0f8926eb95942
        return factory.createRepository();
    }
}