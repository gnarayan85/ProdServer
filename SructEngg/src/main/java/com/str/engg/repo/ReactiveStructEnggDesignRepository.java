
package com.str.engg.repo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.str.engg.design.model.Project;

import reactor.core.publisher.Mono;

@Repository
public interface ReactiveStructEnggDesignRepository extends ReactiveMongoRepository<Project, Integer> {

	@Query("{ 'projectNumber': ?0}")
	Mono<Project> findByProjectNumber(int projectNumber);
}
