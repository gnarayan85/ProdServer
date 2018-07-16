package com.str.engg.design.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

public class Beam {
	
	@Id
	int beamId;
	String name;
	double span;
	int beamSectionId;
	ArrayList<EndForces> endForceList;
	
	public int getBeamId() {
		return beamId;
	}
	public void setBeamId(int beamId) {
		this.beamId = beamId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSpan() {
		return span;
	}
	public void setSpan(double span) {
		this.span = span;
	}
	public int getBeamSectionId() {
		return beamSectionId;
	}
	public void setBeamSectionId(int beamSectionId) {
		this.beamSectionId = beamSectionId;
	}
	public ArrayList<EndForces> getEndForceList() {
		return endForceList;
	}
	public void setEndForceList(ArrayList<EndForces> endForceList) {
		this.endForceList = endForceList;
	}
	public void addEndForce(EndForces endForce) {
		this.endForceList.add(endForce);
	}
	public Beam() {
		this.endForceList = new ArrayList<EndForces>();
	}
	

}
