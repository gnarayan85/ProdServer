package com.str.engg.design.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class PointLoad {
	@Id
	int pointLoadId;
	double value;
	double distanceFromLeft;
	List<Integer> beamIdList;
	
	public List<Integer> getBeamIdList() {
		return beamIdList;
	}
	public void setBeamIdList(List<Integer> beamIdList) {
		this.beamIdList = beamIdList;
	}
	public int getPointLoadId() {
		return pointLoadId;
	}
	public void setPointLoadId(int pointLoadId) {
		this.pointLoadId = pointLoadId;
	}

	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getDistanceFromLeft() {
		return distanceFromLeft;
	}
	public void setDistanceFromLeft(double distanceFromLeft) {
		this.distanceFromLeft = distanceFromLeft;
	}

	
	

}