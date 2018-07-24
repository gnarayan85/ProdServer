package com.str.engg.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.str.engg.design.model.Project;
import com.str.engg.mongorepo.ProjectMongoRepository;
import com.str.engg.repo.ProjectRepository;
import com.str.engg.repo.ReactiveStructEnggDesignRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class StructEnggDesignRepositoryImpl implements ProjectRepository{
	
	@Autowired
	private final ReactiveStructEnggDesignRepository reactiveStructEnggDesignRepository;
	
	@Autowired
	ProjectMongoRepository projectRepo;
	
	public StructEnggDesignRepositoryImpl(ReactiveStructEnggDesignRepository reactiveStructEnggDesignRepository) {
		this.reactiveStructEnggDesignRepository = reactiveStructEnggDesignRepository;
	}

	@Override
	public Project postProject(Project project) {
		
		return projectRepo.save(project);
	}

	@Override
	public Flux<Project> getAllProjects() {
		
		return reactiveStructEnggDesignRepository.findAll();
	}

	@Override
	public Mono<Project> getProjectByProjectNumber(int projectNumber) {
		
		return reactiveStructEnggDesignRepository.findByProjectNumber(projectNumber);
	}

	@Override
	public void deleteProject(int ProjectNumber) {
		Project project = reactiveStructEnggDesignRepository.findByProjectNumber(ProjectNumber).block();
		projectRepo.delete(project);
		
	}


	
}
