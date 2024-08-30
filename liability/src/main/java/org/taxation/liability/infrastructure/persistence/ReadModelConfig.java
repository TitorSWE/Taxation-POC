package org.taxation.liability.infrastructure.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReadModelConfig {

    @Bean
    public String databaseName(){
        return "persons";
    }

    @Bean
    public Class<?> getMappingClass() throws ClassNotFoundException {
        return Class.forName("org.taxation.liability.infrastructure.persistence.ProjectedPerson");
    }

}
