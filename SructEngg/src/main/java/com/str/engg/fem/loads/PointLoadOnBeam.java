package com.str.engg.fem.loads;
//direction of load in local axis,needs to convert into global

public class PointLoadOnBeam {

    private double load;//load intensity in (kN),+ve value denoted towards the element perpendicular to it
    private double position;//0 to 1 ,starting load position from start point of the beam (in % of span) 
    private LoadGroup loadCase;

    public void setLoad(double load) {
        this.load = load;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public void setLoadCase(LoadGroup loadCase) {
        this.loadCase = loadCase;
    }

    public double getLoad() {
        return load;
    }

    public double getPosition() {
        return position;
    }

    public LoadGroup getLoadCase() {
        return loadCase;
    }
    
    
    
    

}
