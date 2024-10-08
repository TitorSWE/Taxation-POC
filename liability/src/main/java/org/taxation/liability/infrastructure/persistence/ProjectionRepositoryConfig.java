package org.taxation.liability.infrastructure.persistence;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProjectionRepositoryConfig {

    @Value("${projection.repository.type}")
    private String repositoryType;

    @Bean
    @Primary
    public PersonProjectionRepositoryFactory personProjectionRepositoryFactory(
            InMemoryPersonProjectionRepositoryFactory inMemoryFactory) {
        switch (repositoryType.toLowerCase()) {
            case "in-memory":
                return inMemoryFactory;
            default:
                throw new IllegalArgumentException("Unknown repository type: " + repositoryType);
        }
    }

    @Bean
    @Primary
    public PersonProjectionRepository personProjectionRepository(PersonProjectionRepositoryFactory factory) {
        return factory.createRepository();
    }
}