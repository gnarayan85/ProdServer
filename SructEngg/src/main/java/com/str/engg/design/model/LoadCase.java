package com.str.engg.design.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class LoadCase {
	@Id
	int loadCaseId;
	LoadCaseTypeEnum type;
	String name;
    List<PointLoad>pointLoadList;
    List<UDL>udlList;   
    
	public int getLoadCaseId() {
		return loadCaseId;
	}
	public void setLoadCaseId(int loadCaseId) {
		this.loadCaseId = loadCaseId;
	}
	public LoadCaseTypeEnum getType() {
		return type;
	}
	public void setType(LoadCaseTypeEnum type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PointLoad> getPointLoadList() {
		return pointLoadList;
	}
	public void setPointLoadList(List<PointLoad> pointLoadList) {
		this.pointLoadList = pointLoadList;
	}
	public List<UDL> getUdlList() {
		return udlList;
	}
	public void setUdlList(List<UDL> udlList) {
		this.udlList = udlList;
	}
	
}
