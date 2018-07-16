package com.str.engg.design.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class AnalysisStage {
	@Id
	int stageId;
	String name;
	int order;
	double[] supportOrdinates;
	boolean isContinuous;
	List<LoadCase> loadCaseList;

	public int getStageId() {
		return stageId;
	}

	public void setStageId(int stageId) {
		this.stageId = stageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public List<LoadCase> getLoadCaseList() {
		return loadCaseList;
	}

	public void setLoadCaseList(List<LoadCase> loadCaseList) {
		this.loadCaseList = loadCaseList;
	}

	public double[] getSupportOrdinates() {
		return supportOrdinates;
	}

	public void setSupportOrdinates(double[] supportOrdinates) {
		this.supportOrdinates = supportOrdinates;
	}

	public boolean isContinuous() {
		return isContinuous;
	}

	public void setContinuous(boolean isContinuous) {
		this.isContinuous = isContinuous;
	}
	
	
	
}
