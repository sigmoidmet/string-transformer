package net.opensource.stringtransformer.integration.harness;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MongoDBContainer;

@TestConfiguration
public class IntegrationTestConfiguration {

    @Bean
    WebTestClient webTestClient(WebApplicationContext applicationContext) {
        return MockMvcWebTestClient.bindToApplicationContext(applicationContext).build();
    }

    @Bean
    MongoDBContainer mongoDBContainer() {
        return new MongoDBContainer("mongo:8");
    }

    @Bean
    DynamicPropertyRegistrar dynamicPropertyRegistrar(MongoDBContainer mongoDbContainer) {
        return registry -> {
            registry.add("spring.data.mongodb.uri", mongoDbContainer::getConnectionString);
            registry.add("spring.data.mongodb.database", () -> "transformers-metrics");
        };
    }
}
