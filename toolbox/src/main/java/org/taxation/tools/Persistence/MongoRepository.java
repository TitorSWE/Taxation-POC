package org.taxation.tools.Persistence;

import com.mongodb.client.MongoClient;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class MongoRepository<T extends ProjectedEntity> implements IRepository<T>{

    private final MongoTemplate mongoTemplate;
    private final Class<T> entityType;

    public MongoRepository(MongoTemplate mongoTemplate, Class<T> entityType){
        this.mongoTemplate = mongoTemplate;
        this.entityType = entityType;
    }

    @Override
    public void save(T projectedEntity) {
        mongoTemplate.save(projectedEntity);
    }

    @Override
    public T findById(String id) {
        return mongoTemplate.findById(id, entityType);
    }

    @Override
    public List<T> findAll() {
        return mongoTemplate.findAll(entityType);
    }
}
