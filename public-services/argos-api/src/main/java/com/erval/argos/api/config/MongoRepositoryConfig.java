package com.erval.argos.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.erval.argos.mongo.repositories")
public class MongoRepositoryConfig {
}
