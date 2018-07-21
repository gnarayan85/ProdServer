package com.str.engg.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.str.engg.design.model.Project;
import com.str.engg.repo.ProjectRepository;
import com.str.engg.repo.ReactiveStructEnggDesignRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class StructEnggDesignRepositoryImpl implements ProjectRepository{
	
	@Autowired
	private final ReactiveStructEnggDesignRepository reactiveStructEnggDesignRepository;
	
	public StructEnggDesignRepositoryImpl(ReactiveStructEnggDesignRepository reactiveStructEnggDesignRepository) {
		this.reactiveStructEnggDesignRepository = reactiveStructEnggDesignRepository;
	}

	@Override
	public Mono<Void> postProject(Mono<Project> project) {
		Mono<Project> projecthMono =  project.doOnNext(Project -> {
			reactiveStructEnggDesignRepository.insert(Project).subscribe();
            System.out.println("########### POST:" + Project);
        });
		
		return projecthMono.then();
	}

	@Override
	public Flux<Project> getAllProjects() {
		
		return reactiveStructEnggDesignRepository.findAll();
	}

	@Override
	public Mono<Project> getProjectByProjectNumber(int projectNumber) {
		
		return reactiveStructEnggDesignRepository.findByProjectNumber(projectNumber);
	}


	
}
