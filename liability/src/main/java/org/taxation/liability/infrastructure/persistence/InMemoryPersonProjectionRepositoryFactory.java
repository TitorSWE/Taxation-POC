package org.taxation.liability.infrastructure.persistence;

import org.springframework.stereotype.Component;

@Component
public class InMemoryPersonProjectionRepositoryFactory implements PersonProjectionRepositoryFactory {

    @Override
    public PersonProjectionRepository createRepository() {
        return new InMemoryPersonProjectionRepository();
    }
}