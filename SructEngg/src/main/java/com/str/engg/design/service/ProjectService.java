package com.str.engg.design.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.str.engg.design.model.Project;
import com.str.engg.functional.handler.StructEnggDesignHandler;

@Service
public class ProjectService {

	@Autowired
	private StructEnggDesignHandler structEnggDesignHandler;
	
//	public List<Project> getProjects() {
//		return structEnggDesignHandler.getAll();
//	}

//	public Project getProject(int projectNumber) {
//		return projectRepository.findOne(projectNumber);
//		return null;
//	}

/*	public Project saveProject(Project project) {
		return structEnggDesignHandler.postGraph(project);
	}

	public Project updateProject(int projectNumber, Project project) {
		return structEnggDesignHandler.save(project);
	}*/

//	public String deleteProject(int projectNumber) {
//		structEnggDesignHandler.delete(projectNumber);
//		return "project "+projectNumber+" deleted";
//		 return null;
//	}

	

}
