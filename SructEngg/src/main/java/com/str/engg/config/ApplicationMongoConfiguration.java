
package com.str.engg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;



@Configuration
@EnableMongoRepositories({"com.str.engg.mongorepo"})
class ApplicationMongoConfiguration extends AbstractMongoConfiguration {

	
	@Override
	@Bean
	public MongoClient mongoClient() {
		MongoClient mongoCLient = new MongoClient("localhost");
		
		return mongoCLient;
	}
	

	@Override
	protected String getDatabaseName() {
		return "structengg";
	}
	
	
	
	@Bean
	  public MongoTemplate normalMongoTemplate() {
	    return new MongoTemplate(mongoClient(), getDatabaseName());
	  }	
}
