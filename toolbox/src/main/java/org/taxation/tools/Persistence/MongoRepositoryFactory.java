package org.taxation.tools.Persistence;

import com.mongodb.client.MongoClient;
import jakarta.annotation.PostConstruct;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoRepositoryFactory<T extends ProjectedEntity> implements IRepositoryFactory<T> {

    private final MongoTemplate mongoTemplate;
    private final Class<T> clazz;

    @Value("${projection.repository.database-name}")
    private String mongoDatabaseName;

    public MongoRepositoryFactory(MongoClient mongoClient, String mongoDatabaseName, Class<T> tClass) {
        this.mongoTemplate = new MongoTemplate(mongoClient, mongoDatabaseName);
        this.clazz = tClass;
    }

    @Override
    public IRepository<T> createRepository() {
        return new MongoRepository<>(this.mongoTemplate, this.clazz);
    }
}
