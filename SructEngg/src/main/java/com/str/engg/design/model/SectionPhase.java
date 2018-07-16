package com.str.engg.design.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class SectionPhase {
	@Id
	int phaseId;
	int AddedStageID;
	ShapeTypeEnum shapeType;
	List<Double> shapeParameters;
	int materialId;
	List<Double> fcu_developementOrder;

	public int getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(int phaseId) {
		this.phaseId = phaseId;
	}

	public int getAddedStageID() {
		return AddedStageID;
	}

	public void setAddedStageID(int addedStageID) {
		AddedStageID = addedStageID;
	}

	public ShapeTypeEnum getShapeType() {
		return shapeType;
	}

	public void setShapeType(ShapeTypeEnum shapeType) {
		this.shapeType = shapeType;
	}

	public List<Double> getShapeParameters() {
		return shapeParameters;
	}

	public void setShapeParameters(List<Double> shapeParameters) {
		this.shapeParameters = shapeParameters;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public List<Double> getFcu_developementOrder() {
		return fcu_developementOrder;
	}

	public void setFcu_developementOrder(List<Double> fcu_developementOrder) {
		this.fcu_developementOrder = fcu_developementOrder;
	}

}
