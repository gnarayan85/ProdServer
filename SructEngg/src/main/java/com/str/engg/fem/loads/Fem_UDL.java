package com.str.engg.fem.loads;

public class Fem_UDL {

    private double load;//load intensity in (kN/m),+ve value denoted towards element
    private LoadGroup loadCase;

    public void setLoad(double load) {
        this.load = load;
    }



    public void setLoadCase(LoadGroup loadCase) {
        this.loadCase = loadCase;
    }

    public double getLoad() {
        return load;
    }

    public LoadGroup getLoadCase() {
        return loadCase;
    }


    
    public Fem_UDL(double load, LoadGroup loadCase) {
        this.load = load;
        this.loadCase = loadCase;
    
    }

}
