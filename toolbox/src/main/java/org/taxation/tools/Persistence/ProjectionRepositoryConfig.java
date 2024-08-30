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
    public IRepositoryFactory<T> projectionRepositoryFactory(
            InMemoryRepositoryFactory<T> inMemoryFactory,
            MongoRepositoryFactory<T> mongoRepositoryFactory) {
        return switch (repositoryType.toLowerCase()) {
            case "in-memory" -> inMemoryFactory;
            case "mongo" -> mongoRepositoryFactory;
            default -> throw new IllegalArgumentException("Unknown repository type: " + repositoryType);
        };
    }

    @Bean
    @Primary
    public IRepository<T> projectionRepository(IRepositoryFactory<T> factory) {
        return factory.createRepository();
    }
}