package com.str.engg.design.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class BeamSection {
	@Id
	int beamSectionId;
	List<SectionPhase> phaseList;
	List<BeamSectionPropertyAtStage> beamSectionPropertyAtStageList;
	public int getBeamSectionId() {
		return beamSectionId;
	}
	public void setBeamSectionId(int beamSectionId) {
		this.beamSectionId = beamSectionId;
	}
	public List<SectionPhase> getPhaseList() {
		return phaseList;
	}
	public void setPhaseList(List<SectionPhase> phaseList) {
		this.phaseList = phaseList;
	}
	public List<BeamSectionPropertyAtStage> getBeamSectionPropertyAtStageList() {
		return beamSectionPropertyAtStageList;
	}
	public void setBeamSectionPropertyAtStageList(List<BeamSectionPropertyAtStage> beamSectionPropertyAtStageList) {
		this.beamSectionPropertyAtStageList = beamSectionPropertyAtStageList;
	}


}
