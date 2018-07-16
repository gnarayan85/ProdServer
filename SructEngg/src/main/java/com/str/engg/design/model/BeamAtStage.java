package com.str.engg.design.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;

public class BeamAtStage {
	@Id
	int stageId;
	int beamId;
	double[] supportOrdinates;
	double span;
	Map<Integer, List<BeamResultData>> beamResultMap;//key is load case id
		
	public int getStageId() {
		return stageId;
	}
	public void setStageId(int stageId) {
		this.stageId = stageId;
	}
	public int getBeamId() {
		return beamId;
	}
	public void setBeamId(int beamId) {
		this.beamId = beamId;
	}
	public double[] getSupportOrdinates() {
		return supportOrdinates;
	}
	public void setSupportOrdinates(double[] supportOrdinates) {
		this.supportOrdinates = supportOrdinates;
	}
	public double getSpan() {
		return span;
	}
	public void setSpan(double span) {
		this.span = span;
	}
	public Map<Integer, List<BeamResultData>> getBeamResultMap() {
		return beamResultMap;
	}
	public void setBeamResultMap(Map<Integer, List<BeamResultData>> beamResultMap) {
		this.beamResultMap = beamResultMap;
	}
		
	
		
	
	

}
