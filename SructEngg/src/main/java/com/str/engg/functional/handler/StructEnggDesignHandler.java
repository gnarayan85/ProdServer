package com.str.engg.functional.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.str.engg.design.model.Project;
import com.str.engg.repo.ProjectRepository;

import reactor.core.publisher.Mono;

@Component
public class StructEnggDesignHandler {
	
	@Autowired
	private final ProjectRepository projectRepository;

	public StructEnggDesignHandler(ProjectRepository repository) {
		this.projectRepository = repository;
	}

    /**
     * POST a Project
     */
    public Mono<ServerResponse> postProject(Mono<Project> project) {
    	
    	projectRepository.postProject(project);
        return ServerResponse.ok().build();
    }
    
    
    
//    /**
//     *	PUT a Project
//     */
//    public Mono<ServerResponse> putProject(ServerRequest request) {
//    	// parse id from path-variable
//    	long candidateId = Long.valueOf(request.pathVariable("id"));
//    	
//    	// get candidate data from request object
//    	Mono<Project> candidate = request.bodyToMono(Project.class);
//    	
//		// get candidate from repository 
//		Mono<Project> responseMono = projectRepository.save(candidate);
//		
//		// build response
//		return responseMono
//                .flatMap(cust -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(cust)));
//    }
//
//    /**
//     *	DELETE a Project
//     */
//    public Mono<ServerResponse> deleteProject(ServerRequest request) {
//    	// parse id from path-variable
//    	long candidateId = Long.valueOf(request.pathVariable("id"));
//    	
//    	// get candidate from repository 
//    	Mono<String> responseMono = projectRepository.deleteProject(candidateId);
//    	
//    	// build response
//		return responseMono
//                .flatMap(strMono -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(fromObject(strMono)));
//    }
}