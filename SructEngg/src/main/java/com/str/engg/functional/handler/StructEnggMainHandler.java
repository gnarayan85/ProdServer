package com.str.engg.functional.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.str.engg.model.Graph;
import com.str.engg.repo.StructEnggRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class StructEnggMainHandler {
	
	@Autowired
	private final StructEnggRepository structEnggRepository;

	public StructEnggMainHandler(StructEnggRepository repository) {
		this.structEnggRepository = repository;
	}
	
	/**
	 * GET ALL Graphs
	 */
    public List<Graph> getAll() {
    	// fetch all candidates from repository
    	Flux<Graph> graphs = structEnggRepository.getAllGraphs();
    	List<Graph> graphList = graphs.collectList().block();
    	// build response
		//return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(candidates, Graph.class);
    	return graphList;
    }
    
    /**
     * GET a Graph by ID 
     */
    public Mono<ServerResponse> getGraph(ServerRequest request) {
    	// parse path-variable
    	long candidateId = Long.valueOf(request.pathVariable("id"));
    	
    	// build notFound response 
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		
		// get candidate from repository 
		Mono<Graph> candidateMono = structEnggRepository.getGraphById(candidateId);
		
		// build response
		return candidateMono
                .flatMap(candidate -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(candidate)))
                .switchIfEmpty(notFound);
    }
    
    /**
     * POST a Graph
     */
    public void postGraph(Graph graph) {
        structEnggRepository.saveGraph(graph);
    }
    
    
    
    /**
     *	PUT a Graph
     */
    public Mono<ServerResponse> putGraph(ServerRequest request) {
    	// parse id from path-variable
    	long candidateId = Long.valueOf(request.pathVariable("id"));
    	
    	// get candidate data from request object
    	Mono<Graph> candidate = request.bodyToMono(Graph.class);
    	
		// get candidate from repository 
		Mono<Graph> responseMono = structEnggRepository.putGraph(candidateId, candidate);
		
		// build response
		return responseMono
                .flatMap(cust -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(cust)));
    }

    /**
     *	DELETE a Graph
     */
    public Mono<ServerResponse> deleteGraph(ServerRequest request) {
    	// parse id from path-variable
    	long candidateId = Long.valueOf(request.pathVariable("id"));
    	
    	// get candidate from repository 
    	Mono<String> responseMono = structEnggRepository.deleteGraph(candidateId);
    	
    	// build response
		return responseMono
                .flatMap(strMono -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(fromObject(strMono)));
    }
}