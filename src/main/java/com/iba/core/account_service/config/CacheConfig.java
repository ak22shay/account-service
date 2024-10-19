package com.iba.core.account_service.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CacheConfig {
    @Bean
    public Config hazelcastConfig() {
        // Create a new Hazelcast configuration
        Config config = new Config();
        config.setInstanceName("hazelcast-instance")
                .addMapConfig(new MapConfig()
                        .setName("accountCache") // Cache name for the service
                        .setTimeToLiveSeconds(3600)); // TTL: 1 hour

        return config;
    }

}
