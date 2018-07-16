package com.str.engg.design.model;

import org.springframework.data.annotation.Id;

public class Material {
	@Id
	int materialId;
	String name;
	double fcu;
	
	public int getMaterialId() {
		return materialId;
	}
	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getFcu() {
		return fcu;
	}
	public void setFcu(double fcu) {
		this.fcu = fcu;
	}
}
