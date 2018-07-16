package com.str.engg.shape;

public class Rectangle extends Shape {

	private double breadth; // in mm
	private double height; // in mm

	public Rectangle(double breadth, double height, double E, double density) {
		this.breadth = breadth;
		this.height = height;
		this.E = E;
		this.density = density;
		calculateSectionProperties();
	}

	@Override
	public void calculateSectionProperties() {

		area = breadth * height;
		Ix = (breadth * Math.pow(height, 3)) / 12; // Ix=bd^3/12
		yb = height / 2;
		yt = height / 2;
		Zb = Ix / yb;
		Zt = Ix / yt;
		totalHeight = height;
		weightPerMeter = area * density * Math.pow(10, -6);

	}

	public double getBreadth() {
		return breadth;
	}

	public double getHeight() {
		return height;
	}

	public void setBreadth(double breadth) {
		this.breadth = breadth;
		calculateSectionProperties();
	}

	public void setHeight(double height) {
		this.height = height;
		calculateSectionProperties();
	}

	@Override

	////////// remember to remove code dependency from these equations and make it
	////////// generic
	public double getCompressionArea(double neutralaxisDepth_x) {

		return 0.9 * neutralaxisDepth_x * breadth;

	}

}
