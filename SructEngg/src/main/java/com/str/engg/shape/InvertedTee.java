package com.str.engg.shape;

//TODO

//to update as in rectangle

public class InvertedTee extends Shape {

    private double breadth_flange; //flange is at the Top
    private double breadth_web;
    private double height_web;
    private double height_flange;

    public void setBreadth_flange(double breadth_flange) {
        this.breadth_flange = breadth_flange;
    }

    public void setBreadth_web(double breadth_web) {
        this.breadth_web = breadth_web;
    }

    public void setHeight_web(double height_web) {
        this.height_web = height_web;
    }

    public void setHeight_flange(double hf) {
        this.height_flange = hf;
    }

    public double getBreadth_flange() {
        return breadth_flange;
    }

    public double getBreadth_web() {
        return breadth_web;
    }

    public double getHeight_web() {
        return height_web;
    }

    public double getHeight_flange() {
        return height_flange;
    }

    @Override
    public double getCompressionArea(double neutralaxisDepth_x) {
        double compressionArea;
        if (0.9 * neutralaxisDepth_x <= height_flange) {

            compressionArea = 0.9 * neutralaxisDepth_x * breadth_flange;
            return compressionArea;
        } else {

            compressionArea = 0.9 * neutralaxisDepth_x * breadth_web + height_flange * (breadth_flange - breadth_web);
            return compressionArea;

        }

    }

    @Override
    public void calculateSectionProperties() {
        area = breadth_flange * height_flange + breadth_web * height_web;
        totalHeight = height_flange + height_web;

        double areaFlange = breadth_flange * height_flange;
        double areaWeb = breadth_web * height_web;
        double Ix_Flange=breadth_flange*Math.pow(height_flange, 3)/12;
        double Ix_Web=breadth_web*Math.pow(height_web, 3)/12;

        yb = ((areaFlange * (height_web + height_flange / 2)) + areaWeb * height_web / 2) / (areaFlange + areaWeb);
        yt = totalHeight-yb;

        Ix = (Ix_Flange+areaFlange*Math.pow((yt-height_flange*.5),2))+(Ix_Web+areaWeb*Math.pow((yb-height_web*.5),2)); // Ix=I+Ay2
        Zb = Ix / yb;
        Zt = Ix / yt;

    }

	public InvertedTee(double breadth_flange, double breadth_web, double height_web, double height_flange) {
		super();
		this.breadth_flange = breadth_flange;
		this.breadth_web = breadth_web;
		this.height_web = height_web;
		this.height_flange = height_flange;
	}

}

