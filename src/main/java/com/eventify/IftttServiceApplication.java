package com.eventify;

import com.eventify.models.MongoConfiguration;
import com.mongodb.MongoCredential;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@SpringBootApplication
public class IftttServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IftttServiceApplication.class, args);
	}

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(final MongoClient mongo, MongoConfiguration mongoConfiguration) {
        System.out.println("Setting up rx mongo template");
        return new ReactiveMongoTemplate(mongoDbFactory(mongo, mongoConfiguration), "master");
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,
                                       MongoMappingContext context) {
        System.out.println("Setting up basic mongo template");
        MappingMongoConverter converter =
                new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);

        return mongoTemplate;

    }

    private MongoClient mongoDbFactory(final MongoClient mongo, MongoConfiguration mongoConfiguration) {
        MongoCredential credential = getCredential(mongoConfiguration);
        MongoClientSettings betaSettings = mongo.getSettings();
        MongoClientSettings settings = getSettings(credential, betaSettings);
        MongoClient mc =  MongoClients.create(settings);
        return mc;
    }

    private MongoClientSettings getSettings(MongoCredential credential, MongoClientSettings settings2) {
        return MongoClientSettings.builder()
                .credential(credential)
                .clusterSettings(settings2.getClusterSettings())
                .serverSettings(settings2.getServerSettings())
                .connectionPoolSettings(settings2.getConnectionPoolSettings())
                .applicationName(settings2.getApplicationName())
                .build();
    }

    private MongoCredential getCredential(MongoConfiguration mongoConfiguration) {
        return MongoCredential.createCredential(mongoConfiguration.getUserName(), mongoConfiguration.getDatabase(), mongoConfiguration.getPassword().toCharArray());
    }
}
