
package com.str.engg.repo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.repository.reactive.RxJava2CrudRepository;
import org.springframework.stereotype.Repository;

import com.str.engg.model.Graph;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Repository
public interface RxJava2CandidateRepository extends RxJava2CrudRepository<Graph, String> {

	/**
	 * Derived query selecting by {@code lastname}.
	 *
	 * @param lastname
	 * @return
	 */
	Flowable<Graph> findByGraphCode(String graphCode);

	/**
	 * String query selecting one entity.
	 *
	 * @param lastname
	 * @return
	 *//*
	@Query("{ 'firstname': ?0, 'lastname': ?1}")
	Maybe<Graph> findByFirstnameAndLastname(String firstname, String lastname);

	*//**
	 * Derived query selecting by {@code lastname}. {@code lastname} uses deferred resolution that does not require
	 * blocking to obtain the parameter value.
	 *
	 * @param lastname
	 * @return
	 *//*
	Flowable<Graph> findByLastname(Single<String> lastname);

	*//**
	 * Derived query selecting by {@code firstname} and {@code lastname}. {@code firstname} uses deferred resolution which
	 * does not require blocking to obtain the parameter value.
	 *
	 * @param firstname
	 * @param lastname
	 * @return
	 *//*
	Maybe<Graph> findByFirstnameAndLastname(Single<String> firstname, String lastname);

	*//**
	 * Use a tailable cursor to emit a stream of entities as new entities are written to the capped collection.
	 *
	 * @return
	 *//*
	@Tailable
	Flowable<Graph> findWithTailableCursorBy();*/
}
