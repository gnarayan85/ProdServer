package com.str.engg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.str.engg.design.model.Project;
import com.str.engg.design.service.AnalysisService;
import com.str.engg.functional.handler.StructEnggDesignHandler;

import reactor.core.publisher.Mono;

@RestController
public class ProjectController {
	
	@Autowired
	private StructEnggDesignHandler designHandler;
	
	@Autowired
	AnalysisService analysisService;
	
	 @RequestMapping(value= "/api/project/post", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
		Project create(@RequestBody Project project) {
		 Project analysedPproject = analysisService.analyse(project);
		 Mono<Project> monoProject = Mono.just(analysedPproject);
		 designHandler.postProject(monoProject);
		return analysedPproject;
		}
	 
	 
	 @RequestMapping(value= "/api/project", method = RequestMethod.GET)
		public List<Project> list() {
			
			 return designHandler.getAll();
		}
	 
	 @RequestMapping(value= "/api/project/{projectNumber}", method = RequestMethod.GET)
		public Project getProjectByProjectNumber(@PathVariable int projectNumber) {
			
			 return designHandler.getProjectByProjectNumber(projectNumber);
		}
	 
}
