package com.str.engg.design.model;

import com.str.engg.shape.Shape;

public class BeamSectionPropertyAtStage {

	private int stageId;
	
	private Shape shape;

	public int getStageId() {
		return stageId;
	}

	public void setStageId(int stageId) {
		this.stageId = stageId;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public BeamSectionPropertyAtStage(int stageId, Shape shape) {
		super();
		this.stageId = stageId;
		this.shape = shape;
	}
	


	
	
}
