package com.str.engg.design.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.str.engg.model.User;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Document
@Data
@RequiredArgsConstructor
public class Project implements Serializable{
	
	
      @Id  ObjectId databaseId;
	  int projectNumber;
      String projectName;
      String projectDescrption;
      List<SubFrame>subframeList;
      List<AnalysisStage> analysisStagesList;
      List<BeamSection> beamSectionList;
      List<Material> materialList;
      List<ColumnSection> columnSectionList;
      String createdUserName;
      Date createdDate;
      List<User> projectUsers;
	public int getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(int projectNumber) {
		this.projectNumber = projectNumber;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDescrption() {
		return projectDescrption;
	}
	public void setProjectDescrption(String projectDescrption) {
		this.projectDescrption = projectDescrption;
	}
	
	public List<SubFrame> getSubframeList() {
		return subframeList;
	}
	public void setSubframeList(List<SubFrame> subframeList) {
		this.subframeList = subframeList;
	}
	public List<AnalysisStage> getAnalysisStagesList() {
		return analysisStagesList;
	}
	public void setAnalysisStagesList(List<AnalysisStage> analysisStagesList) {
		this.analysisStagesList = analysisStagesList;
	}

	public List<BeamSection> getBeamSectionList() {
		return beamSectionList;
	}
	public void setBeamSectionList(List<BeamSection> beamSectionList) {
		this.beamSectionList = beamSectionList;
	}
	public List<Material> getMaterialList() {
		return materialList;
	}
	public void setMaterialList(List<Material> materialList) {
		this.materialList = materialList;
	}
	public List<ColumnSection> getColumnSectionList() {
		return columnSectionList;
	}
	public void setColumnSectionList(List<ColumnSection> columnSectionList) {
		this.columnSectionList = columnSectionList;
	}
	public ObjectId getDatabaseId() {
		return databaseId;
	}
	public void setDatabaseId(ObjectId databaseId) {
		this.databaseId = databaseId;
	}
	public String getCreatedUserName() {
		return createdUserName;
	}
	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public List<User> getProjectUsers() {
		return projectUsers;
	}
	public void setProjectUsers(List<User> projectUsers) {
		this.projectUsers = projectUsers;
	}
      
      
      
	
}

