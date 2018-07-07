package com.str.engg.repo;

import com.str.engg.model.Graph;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StructEnggRepository {
	public Mono<Graph> getGraphById(Long id);
	public Flux<Graph> getAllGraphs();
	public Mono<Void> saveGraph(Mono<Graph> graph);
	public Mono<Graph> putGraph(Long id, Mono<Graph> graph);
	public Mono<String> deleteGraph(Long id);
}
