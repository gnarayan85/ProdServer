
package com.str.engg.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.str.engg.design.model.Project;

@Repository
public interface ReactiveStructEnggDesignRepository extends ReactiveMongoRepository<Project, Integer> {

	
}
