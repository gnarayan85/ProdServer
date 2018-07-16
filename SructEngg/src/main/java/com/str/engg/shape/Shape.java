package com.str.engg.shape;

public abstract class Shape {

	// common section properties here..............
	protected double area; // area of the shape in mm2
	protected double totalHeight; // total height of the shape in mm
	protected double yb; // centroid distance from bottom fiber in mm
	protected double yt; // centroid distance from top fiber in mm
	protected double Ix; // Moment of inertia about x axis of the shape in mm4
	protected double Zb; // bottom fibre section modulus in mm3
	protected double Zt; // top fibre section modulus in mm3
	protected double E; // Elastic modulus (N/mm^2)
	protected double density;//density of the material in kN/m3
	protected double weightPerMeter; //self weight in kN/m
	
	
	

	public abstract void calculateSectionProperties();

	public abstract double getCompressionArea(double neutralaxisDepth_x);

	public double getArea() {
		return area;
	}

	public double getTotalHeight() {
		return totalHeight;
	}

	public double getYb() {
		return yb;
	}

	public double getYt() {
		return yt;
	}

	public double getIx() {
		return Ix;
	}

	public double getZb() {
		return Zb;
	}

	public double getZt() {
		return Zt;
	}

	public double getE() {
		return E;
	}

	public double getDensity() {
		return density;
	}

	public double getWeightPerMeter() {
		return weightPerMeter;
	}
	
	
	
	
	

	
}
