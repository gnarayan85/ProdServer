package com.str.engg.mongorepo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.str.engg.model.Graph;

public interface StructEnggMongoRepository extends MongoRepository<Graph, Long>{
		

}
