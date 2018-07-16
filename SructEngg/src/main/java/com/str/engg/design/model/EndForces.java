package com.str.engg.design.model;

public class EndForces {
	
	int stageId;
	int loadCaseId;
	private double Fx_near;
	private double Fy_near;
	private double Mz_near;
	private double Fx_far;
	private double Fy_far;
	private double Mz_far;

	
	public int getStageId() {
		return stageId;
	}
	public void setStageId(int stageId) {
		this.stageId = stageId;
	}
	public int getLoadCaseId() {
		return loadCaseId;
	}
	public void setLoadCaseId(int loadCaseId) {
		this.loadCaseId = loadCaseId;
	}
	public double getFx_near() {
		return Fx_near;
	}
	public void setFx_near(double fx_near) {
		Fx_near = fx_near;
	}
	public double getFy_near() {
		return Fy_near;
	}
	public void setFy_near(double fy_near) {
		Fy_near = fy_near;
	}
	public double getMz_near() {
		return Mz_near;
	}
	public void setMz_near(double mz_near) {
		Mz_near = mz_near;
	}
	public double getFx_far() {
		return Fx_far;
	}
	public void setFx_far(double fx_far) {
		Fx_far = fx_far;
	}
	public double getFy_far() {
		return Fy_far;
	}
	public void setFy_far(double fy_far) {
		Fy_far = fy_far;
	}
	public double getMz_far() {
		return Mz_far;
	}
	public void setMz_far(double mz_far) {
		Mz_far = mz_far;
	}
	
}
