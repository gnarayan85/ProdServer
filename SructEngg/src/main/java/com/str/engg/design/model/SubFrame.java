package com.str.engg.design.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class SubFrame {
	@Id
	int subFrameId;
	String subframeName;
	String description;
	double supportWidth;
	List<Beam>beamList;
    List<Column>bottomColumnList;
    List<Column>topColumnList;
    List<BeamAtStage>beamAtStageList;
     
    
    
	public List<BeamAtStage> getBeamAtStageList() {
		return beamAtStageList;
	}
	public void setBeamAtStageList(List<BeamAtStage> beamAtStageList) {
		this.beamAtStageList = beamAtStageList;
	}
	public List<Beam> getBeamList() {
		return beamList;
	}
	public void setBeamList(List<Beam> beamList) {
		this.beamList = beamList;
	}
	
	public int getSubFrameId() {
		return subFrameId;
	}
	public void setSubFrameId(int subFrameId) {
		this.subFrameId = subFrameId;
	}
	public String getSubframeName() {
		return subframeName;
	}
	public void setSubframeName(String subframeName) {
		this.subframeName = subframeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getSupportWidth() {
		return supportWidth;
	}
	public void setSupportWidth(double supportWidth) {
		this.supportWidth = supportWidth;
	}
	public List<Column> getBottomColumnList() {
		return bottomColumnList;
	}
	public void setBottomColumnList(List<Column> bottomColumnList) {
		this.bottomColumnList = bottomColumnList;
	}
	public List<Column> getTopColumnList() {
		return topColumnList;
	}
	public void setTopColumnList(List<Column> topColumnList) {
		this.topColumnList = topColumnList;
	}
	
	
	/*int subFrameId;
	String subframeName;
	String description;
	
	List<Beam> beamList;
	List<Column>columnList;
	List<UDL>udlList;
	List<PointLoad>pointLoadList;
	double supportWidth;
	
	
	public int getSubFrameId() {
		return subFrameId;
	}
	public void setSubFrameId(int subFrameId) {
		this.subFrameId = subFrameId;
	}
	public String getSubframeName() {
		return subframeName;
	}
	public void setSubframeName(String subframeName) {
		this.subframeName = subframeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Beam> getBeamList() {
		return beamList;
	}
	public void setBeamList(List<Beam> beamList) {
		this.beamList = beamList;
	}
	public List<Column> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}
	public double getSupportWidth() {
		return supportWidth;
	}
	public void setSupportWidth(double supportWidth) {
		this.supportWidth = supportWidth;
	}
	public List<UDL> getUdlList() {
		return udlList;
	}
	public void setUdlList(List<UDL> udlList) {
		this.udlList = udlList;
	}
	public List<PointLoad> getPointLoadList() {
		return pointLoadList;
	}
	public void setPointLoadList(List<PointLoad> pointLoadList) {
		this.pointLoadList = pointLoadList;
	}*/
}
