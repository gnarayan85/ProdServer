
package com.str.engg.repo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;

import com.str.engg.model.Graph;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveStructEnggRepository extends ReactiveMongoRepository<Graph, Long> {

	/**
	 * Derived query selecting by {@code lastname}.
	 *
	 * @param lastname
	 * @return
	 */
	Flux<Graph> findByGraphCode(String graphCode);

	/**
	 * String query selecting one entity.
	 *
	 * @param lastname
	 * @return
	 *//*
	@Query("{ 'firstname': ?0, 'lastname': ?1}")
	Mono<Graph> findByFirstnameAndLastname(String firstname, String lastname);

	*//**
	 * Derived query selecting by {@code lastname}. {@code lastname} uses deferred resolution that does not require
	 * blocking to obtain the parameter value.
	 *
	 * @param lastname
	 * @return
	 *//*
	Flux<Graph> findByLastname(Mono<String> lastname);

	*//**
	 * Derived query selecting by {@code firstname} and {@code lastname}. {@code firstname} uses deferred resolution that
	 * does not require blocking to obtain the parameter value.
	 *
	 * @param firstname
	 * @param lastname
	 * @return
	 *//*
	Mono<Graph> findByFirstnameAndLastname(Mono<String> firstname, String lastname);

	*//**
	 * Use a tailable cursor to emit a stream of entities as new entities are written to the capped collection.
	 * 
	 * @return
	 *//*
	@Tailable
	Flux<Graph> findWithTailableCursorBy();*/
}
