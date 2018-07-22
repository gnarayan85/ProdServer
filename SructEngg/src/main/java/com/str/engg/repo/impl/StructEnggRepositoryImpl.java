package com.str.engg.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.str.engg.model.Graph;
import com.str.engg.mongorepo.StructEnggMongoRepository;
import com.str.engg.repo.ReactiveStructEnggRepository;
import com.str.engg.repo.StructEnggRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class StructEnggRepositoryImpl implements StructEnggRepository{
	

	
	@Autowired
	private final ReactiveStructEnggRepository reactiveStructEnggRepository;
	
	@Autowired StructEnggMongoRepository repo;
	
	public StructEnggRepositoryImpl(ReactiveStructEnggRepository reactiveGraphRepository) {
		this.reactiveStructEnggRepository = reactiveGraphRepository;
	}

	@Override
	public Mono<Graph> getGraphById(Long id) {
		return reactiveStructEnggRepository.findById(id);
	}

	@Override
	public Flux<Graph> getAllGraphs() {
		return reactiveStructEnggRepository.findAll();
	}

	@Override
	public void saveGraph(Graph graph) {
		/*Mono<Graph> GraphMono =  monoGraph.doOnNext(Graph -> {
			reactiveStructEnggRepository.insert(Graph).subscribe();
            System.out.println("########### POST:" + Graph);
        });*/
		repo.insert(graph);
	}
	
	@Override
	public Mono<Graph> putGraph(Long id, Mono<Graph> monoGraph) {
		Mono<Graph> GraphMono =  monoGraph.doOnNext(Graph -> {
			// reset Graph.Id
			Graph.setGraphId(id);
			
			// log on console
			System.out.println("########### PUT:" + Graph);
        });
		
		return GraphMono;
	}
	
	@Override
	public Mono<String> deleteGraph(Long id) {
    	return Mono.just("Delete Succesfully!");
	}

	
}
