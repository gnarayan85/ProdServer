package com.str.engg.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.str.engg.design.model.BeamSection;
import com.str.engg.design.model.Project;
import com.str.engg.design.model.SubFrame;
import com.str.engg.design.service.AnalysisService;
import com.str.engg.functional.handler.StructEnggDesignHandler;
import com.str.engg.model.User;
import com.str.engg.service.UserService;

@RestController
public class ProjectController {
	
	@Autowired
	private StructEnggDesignHandler designHandler;
	
	@Autowired
	AnalysisService analysisService;
	
	@Autowired
	UserService userService;
	
	 @RequestMapping(value= "/api/project/post", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
		Project create(@RequestBody Project project) {
		 Project analysedPproject = analysisService.analyse(project);
		 designHandler.postProject(analysedPproject);
		return analysedPproject;
		}
	 
	 @RequestMapping(value= "/api/project/add", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
		Project createNew(@RequestBody Project project) {
		 project.setProjectNumber(new Random().nextInt(99999));
		 project.setCreatedDate(new Date());
		 project.setCreatedUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		 project.setProjectUsers(new ArrayList<User>());
		 project.getProjectUsers().add(userService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()));
		 designHandler.postProject(project);
		 return project;
		}
	 
	 @RequestMapping(value= "/api/project/adduser/{projectNumber}/{userName}", method = RequestMethod.GET)
		Project addUserToProject(@PathVariable int projectNumber, @PathVariable String userName) {
		 Project project =  designHandler.getProjectByProjectNumber(projectNumber);
		 project.getProjectUsers().add(userService.findOne(userName));
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
		 int frameId = 1;
		 if(analysedPproject.getSubframeList() == null) {
			 analysedPproject.setSubframeList(new ArrayList<SubFrame>());
		 } else {
			 for(SubFrame subFrame1 : analysedPproject.getSubframeList()) {
				 if(subFrame1.getSubFrameId() >= frameId) {
					 frameId = subFrame1.getSubFrameId() + 1;
				 }
			 }
		 }
		 subFrame.setSubframeName("Frame" + frameId);
		 subFrame.setSubFrameId(frameId);
		 subFrame.setCreatedDate(new Date());
		 subFrame.setCreatedUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		 analysedPproject.getSubframeList().add(subFrame);
		 designHandler.postProject(analysedPproject);
		return analysedPproject;
		}
	 
	 @RequestMapping(value= "/api/project/subframe/delete/{projectNumber}/{frameId}", method = RequestMethod.GET)
		Project deleteSubFrame(@PathVariable int projectNumber, @PathVariable int frameId) {
		 Project analysedPproject =  designHandler.getProjectByProjectNumber(projectNumber);
		
		 for(SubFrame subFrame : analysedPproject.getSubframeList()) {
			 if(subFrame.getSubFrameId() == frameId) {
				 analysedPproject.getSubframeList().remove(subFrame);
			 }
		 }
		 designHandler.postProject(analysedPproject);
		return analysedPproject;
		}
	 
	 
	 
	 @RequestMapping(value= "/api/adduser/beamsection/{projectNumber}/{beamSectionId}", method = RequestMethod.GET)
     boolean addUserBeamSection(@PathVariable int projectNumber, @PathVariable int beamSectionId) {
		 Project analysedPproject =  designHandler.getProjectByProjectNumber(projectNumber);
		 for(BeamSection beamSection1 : analysedPproject.getBeamSectionList()) {
			 if(beamSectionId == beamSection1.getBeamSectionId()) {
				 if(beamSection1.getLockedUsername() == null) {
					 beamSection1.setLockedTime(new Date());
					 beamSection1.setLockedUsername(SecurityContextHolder.getContext().getAuthentication().getName());
					 return true;
				 } else {
					 if(beamSection1.getLockedUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
						 return true;
					 } else {
						 return false;
					 }
					 
				 }
			 }
		 } 
		 return false;
	 }
	 
	 
	 @RequestMapping(value= "/api/project/beamsection/{projectNumber}", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
		Project createBeamSection(@RequestBody BeamSection beamSection, @PathVariable int projectNumber) {
		 Project analysedPproject =  designHandler.getProjectByProjectNumber(projectNumber);
		 
		 int beamsectionId = 1;
		 if(beamSection.getBeamSectionId() > 0) {
			 for(BeamSection beamSection1 : analysedPproject.getBeamSectionList()) {
				 if(beamSection.getBeamSectionId() == beamSection1.getBeamSectionId()) {
					 beamSection.setCreatedUserName(beamSection1.getCreatedUserName());
					 beamSection.setCreatedDate(beamSection1.getCreatedDate());
					 analysedPproject.getBeamSectionList().remove(beamSection1);
					 break;
				 }
			 } 
			 beamsectionId = beamSection.getBeamSectionId();
		 } else {
			 if(analysedPproject.getBeamSectionList() == null) {
				 analysedPproject.setBeamSectionList(new ArrayList<BeamSection>());
			 } else {
				 for(BeamSection beamSection1 : analysedPproject.getBeamSectionList()) {
					 if(beamSection1.getBeamSectionId() >= beamsectionId) {
						 beamsectionId = beamSection1.getBeamSectionId() + 1;
					 }
				 }
			 }
			 beamSection.setCreatedDate(new Date());
			 beamSection.setCreatedUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		 }
		
		 beamSection.setBeamName("BeamSection" + beamsectionId);
		 beamSection.setBeamSectionId(beamsectionId);
		 beamSection.setLockedUsername(null);
		 beamSection.setLockedTime(null);
		 analysedPproject.getBeamSectionList().add(beamSection);
		 designHandler.postProject(analysedPproject);
		return analysedPproject;
		}
	 
	 @RequestMapping(value= "/api/project/beamsection/delete/{projectNumber}/{beamSectionId}", method = RequestMethod.GET)
		Project deleteBeamSection(@PathVariable int projectNumber, @PathVariable int beamSectionId) {
		 Project analysedPproject =  designHandler.getProjectByProjectNumber(projectNumber);
		
		 for(BeamSection beamSection : analysedPproject.getBeamSectionList()) {
			 if(beamSection.getBeamSectionId() == beamSectionId) {
				 analysedPproject.getBeamSectionList().remove(beamSection);
				 break;
			 }
		 }
		 designHandler.postProject(analysedPproject);
		return analysedPproject;
		}
	 
}
