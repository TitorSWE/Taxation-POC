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