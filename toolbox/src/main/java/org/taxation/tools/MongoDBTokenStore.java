<<<<<<< HEAD
package org.taxation.tools;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
class MongoDBTokenStore {

    @Value("${projection.token-store.url}")
    private String mongoUrl;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUrl);
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
=======
package org.taxation.tools;public class MongoDBTokenStore {
}
>>>>>>> 34f407bdf061ef716a6c180731a0f8926eb95942
