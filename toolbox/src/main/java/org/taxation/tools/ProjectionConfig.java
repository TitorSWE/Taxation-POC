<<<<<<< HEAD
package org.taxation.tools;

import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProjectionConfig {

    @Autowired
    public void configure(EventProcessingConfigurer config, TokenStore tokenStore) {
        config.usingTrackingEventProcessors();
        config.registerTokenStore(configuration -> tokenStore);
    }
}
=======
package org.taxation.tools;public class ProjectionConfig {
}
>>>>>>> 34f407bdf061ef716a6c180731a0f8926eb95942
