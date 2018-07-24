package com.str.engg.functional.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.str.engg.design.model.Project;
import com.str.engg.model.Graph;
import com.str.engg.repo.ProjectRepository;

import reactor.core.publisher.Flux;
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
    public Project postProject(Project project) {
        return projectRepository.postProject(project);
    }
    
    public void deleteProject(int ProjectNumber){
    	projectRepository.deleteProject(ProjectNumber);
    }
    
    
    public List<Project> getAll() {
    	// fetch all candidates from repository
    	Flux<Project> projects = projectRepository.getAllProjects();
    	List<Project> projectList = projects.collectList().block();
    	return projectList;
    }
    
    public Project  getProjectByProjectNumber(int projectNumber){
    	return projectRepository.getProjectByProjectNumber(projectNumber).block();
    }
}