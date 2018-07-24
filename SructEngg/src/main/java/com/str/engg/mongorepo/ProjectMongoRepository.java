package com.str.engg.mongorepo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.str.engg.design.model.Project;

public interface ProjectMongoRepository extends MongoRepository<Project, Integer>{
		

}
