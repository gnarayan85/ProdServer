package com.str.engg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.str.engg.functional.handler.StructEnggMainHandler;
import com.str.engg.model.Graph;

import reactor.core.publisher.Mono;

@RestController
public class GraphController {

	@Autowired
	private StructEnggMainHandler graphHandler;
	
	/* @RequestMapping(value= "/api/graph/post", method = RequestMethod.POST)
	Mono<ServerResponse> create(@RequestBody Mono<Graph> graph) {
		return graphHandler.postGraph(graph);
	}*/

	 @RequestMapping(value= "/api/graph", method = RequestMethod.GET)
	public List<Graph> list() {
		/*return 		
		ResponseEntity
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(graphHandler.getAll());*/
		 return graphHandler.getAll();
	}
}
