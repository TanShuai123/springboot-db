package com.tan.springbootmongodb.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories(basePackages = "com.tan.springbootmongodb.repository")
@PropertySource("classpath:datasource.properties")
public class DataSourceConfig extends AbstractMongoConfiguration {
    @Autowired
    private Environment environment;

    @Override
    public String getDatabaseName() {
        return environment.getRequiredProperty("mongo.name");
    }

    @Override
    @Bean
    public MongoClient mongoClient(){
        ServerAddress serverAddress = new ServerAddress(environment.getRequiredProperty("mongo.host"));
        List<MongoCredential> credentialList = new ArrayList<>();
        return new MongoClient(serverAddress, credentialList);
    }
}
