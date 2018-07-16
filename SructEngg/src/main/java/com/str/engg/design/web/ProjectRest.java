package com.str.engg.design.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.str.engg.design.model.Project;
import com.str.engg.design.service.AnalysisService;
import com.str.engg.design.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectRest {
//	@Autowired
//	ProjectService projectService;
//	
//	@Autowired
//	AnalysisService analysisService;
//	
//	@RequestMapping( value = "/all" ,method = RequestMethod.GET)
//	public List<Project> retrieveProjects() {
//		try{
//		return projectService.getProjects();
//		}
//		catch(Exception e) {
//			System.out.println(e);
//			return null;
//		}
//	}
//	
//	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
//	public Project  retrieveProject(@PathVariable("id") int projectNumber) {
//		try {
//			return projectService.getProject(projectNumber);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	@RequestMapping( method = RequestMethod.POST)
//	public Project  postProject(@RequestBody Project project) {
//		try {
//			return analysisService.analyse(project);
//			//return null;
//		//return projectService.saveProject(project);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
//	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
//	public Project  putProject(@PathVariable("id") int projectNumber,@RequestBody Project project) {
//		try {
//			return projectService.updateProject(projectNumber,project);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
//	@RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
//	public String  removeProject(@PathVariable("id") int projectNumber) {
//		try {
//			return projectService.deleteProject(projectNumber);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	
}
