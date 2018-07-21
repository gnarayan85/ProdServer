package com.str.engg.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;

import com.str.engg.model.Graph;
import com.str.engg.repo.ReactiveStructEnggRepository;
import com.str.engg.repo.StructEnggRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class StructEnggRepositoryImpl implements StructEnggRepository{
	

	
	@Autowired
	private final ReactiveStructEnggRepository reactiveStructEnggRepository;
	
	@Autowired ReactiveMongoTemplate template;
	
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
	public Mono<Void> saveGraph(Mono<Graph> monoGraph) {
		Mono<Graph> GraphMono =  monoGraph.doOnNext(Graph -> {
			reactiveStructEnggRepository.insert(Graph).subscribe();
			template.save(Graph);
            System.out.println("########### POST:" + Graph);
        });
		reactiveStructEnggRepository.insert(monoGraph.block());
		
		return GraphMono.then();
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
