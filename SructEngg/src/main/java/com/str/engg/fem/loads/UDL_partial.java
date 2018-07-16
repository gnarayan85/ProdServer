package com.str.engg.fem.loads;

public class UDL_partial {

    private double load;//load intensity in (kN/m),+ve value denoted towards the element
    private double position_start;//0 to 1 ,starting load position from start point of the beam (in % of span) 
    private double length_load;//0 to 1 , length of the load (in % of span) 
    private LoadGroup loadCase;

    public void setLoad(double load) {
        this.load = load;
    }

    public void setPosition_start(double position_start) {
        this.position_start = position_start;
    }

    public void setLength_load(double length_load) {
        this.length_load = length_load;
    }

    public void setLoadCase(LoadGroup loadCase) {
        this.loadCase = loadCase;
    }

    public UDL_partial(double load, LoadGroup loadCase) {
        this.load = load;
        this.loadCase = loadCase;
        position_start = 0;
        length_load = 1;

    }

}
