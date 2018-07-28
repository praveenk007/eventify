package com.ifttt.iftttservice;

import com.ifttt.iftttservice.models.MongoConfiguration;
import com.mongodb.MongoCredential;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@SpringBootApplication
public class IftttServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IftttServiceApplication.class, args);
	}

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(final MongoClient mongo, MongoConfiguration mongoConfiguration) {
        return new ReactiveMongoTemplate(mongoDbFactory(mongo, mongoConfiguration), "master");
    }

    private MongoClient mongoDbFactory(final MongoClient mongo, MongoConfiguration mongoConfiguration) {
        MongoCredential credential = getCredential(mongoConfiguration);
        MongoClientSettings betaSettings = mongo.getSettings();
        MongoClientSettings settings = getSettings(credential, betaSettings);
        System.out.println("Reactive client created!");
        MongoClient mc =  MongoClients.create(settings);
        System.out.println(mc.getSettings().toString());
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
