package org.taxation.liability.infrastructure;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    @Bean
    public TokenStore tokenStore(MongoClient client) {
        return MongoTokenStore.builder()
                .mongoTemplate(DefaultMongoTemplate.builder()
                        .mongoDatabase(client)
                        .build())
                .serializer(JacksonSerializer.builder().build())
                .build();
    }
}