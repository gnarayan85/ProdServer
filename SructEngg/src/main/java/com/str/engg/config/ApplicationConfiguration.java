
package com.str.engg.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableReactiveMongoRepositories(basePackages = {"com.str.engg.repo", "com.str.engg.model"})
@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class, DataSourceAutoConfiguration.class })
@RequiredArgsConstructor
class ApplicationConfiguration extends AbstractReactiveMongoConfiguration {

	@Bean
	public LoggingEventListener mongoEventListener() {
		return new LoggingEventListener();
	}

	@Override
	@Bean
	public MongoClient reactiveMongoClient() {
		return MongoClients.create(String.format("mongodb://localhost:%d", 27017));
	}

	@Override
	protected String getDatabaseName() {
		return "structengg";
	}
	
	@Bean
	  public ReactiveMongoTemplate reactiveMongoTemplate() {
	    return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
	  }
}
