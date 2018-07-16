package com.str.engg.repo;

import org.springframework.stereotype.Repository;

import com.str.engg.design.model.Project;

import reactor.core.publisher.Mono;

@Repository
public interface ProjectRepository {

	public Mono<Void> postProject(Mono<Project> project);
}
