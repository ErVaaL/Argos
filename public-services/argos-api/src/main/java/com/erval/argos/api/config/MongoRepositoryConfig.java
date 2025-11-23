package com.erval.argos.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.erval.argos.mongo.repositories")
/**
 * Enables scanning of Mongo repositories defined in the adapter module.
 */
public class MongoRepositoryConfig {
}
