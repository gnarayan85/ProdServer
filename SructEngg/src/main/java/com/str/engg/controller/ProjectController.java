package com.str.engg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.str.engg.design.model.Project;
import com.str.engg.design.model.SubFrame;
import com.str.engg.design.service.AnalysisService;
import com.str.engg.functional.handler.StructEnggDesignHandler;

@RestController
public class ProjectController {
	
	@Autowired
	private StructEnggDesignHandler designHandler;
	
	@Autowired
	AnalysisService analysisService;
	
	 @RequestMapping(value= "/api/project/post", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
		Project create(@RequestBody Project project) {
		 Project analysedPproject = analysisService.analyse(project);
		 designHandler.postProject(analysedPproject);
		return analysedPproject;
		}
	 
	 @RequestMapping(value= "/api/project/add", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
		Project createNew(@RequestBody Project project) {
		 project.setProjectNumber(new Random().nextInt(99999));
		 designHandler.postProject(project);
		return project;
		}
	 
	 
	 @RequestMapping(value= "/api/project", method = RequestMethod.GET)
		public List<Project> list() {
			
			 return designHandler.getAll();
		}
	 
	 @RequestMapping(value= "/api/project/{projectNumber}", method = RequestMethod.GET)
		public Project getProjectByProjectNumber(@PathVariable int projectNumber) {
			
			 return designHandler.getProjectByProjectNumber(projectNumber);
		}
	 
	 @RequestMapping(value= "/api/project/delete/{projectNumber}", method = RequestMethod.GET)
		public void deleteProject(@PathVariable int projectNumber) {
			
			  designHandler.deleteProject(projectNumber);
		}
	 
	 
	 @RequestMapping(value= "/api/project/subframe/{projectNumber}", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
		Project createSubFrame(@RequestBody SubFrame subFrame, @PathVariable int projectNumber) {
		 Project analysedPproject =  designHandler.getProjectByProjectNumber(projectNumber);
		 if(analysedPproject.getSubframeList() == null) {
			 analysedPproject.setSubframeList(new ArrayList<SubFrame>());
			 
		 }
		 subFrame.setSubframeName("Frame" + (analysedPproject.getSubframeList().size()+1));
		 subFrame.setSubFrameId((analysedPproject.getSubframeList().size()+1));
		 analysedPproject.getSubframeList().add(subFrame);
		 designHandler.postProject(analysedPproject);
		return analysedPproject;
		}
	 
}
