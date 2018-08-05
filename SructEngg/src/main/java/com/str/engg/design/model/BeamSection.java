package com.str.engg.design.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class BeamSection {
	@Id
	int beamSectionId;
	List<SectionPhase> phaseList;
	List<BeamSectionPropertyAtStage> beamSectionPropertyAtStageList;
	String beamName;
    String createdUserName;
    Date createdDate;
    String lockedUsername;
    Date lockedTime;
    
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
	public String getBeamName() {
		return beamName;
	}
	public void setBeamName(String beamName) {
		this.beamName = beamName;
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
	public String getLockedUsername() {
		return lockedUsername;
	}
	public void setLockedUsername(String lockedUsername) {
		this.lockedUsername = lockedUsername;
	}
	public Date getLockedTime() {
		return lockedTime;
	}
	public void setLockedTime(Date lockedTime) {
		this.lockedTime = lockedTime;
	}


}
