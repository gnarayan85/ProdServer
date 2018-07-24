package com.str.engg.repo;

import org.springframework.stereotype.Repository;

import com.str.engg.design.model.Project;
import com.str.engg.model.Graph;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProjectRepository {

	public Project postProject(Project project);
	public Flux<Project> getAllProjects();
	public Mono<Project> getProjectByProjectNumber(int projectNumber);
	public void deleteProject(int ProjectNumber);
}
