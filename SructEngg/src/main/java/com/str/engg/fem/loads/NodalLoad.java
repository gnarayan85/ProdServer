package com.str.engg.fem.loads;


// nodal loads in global axis


public class NodalLoad {
    private double NodalLoadId;
    private double xLoad;//load intensity in (kN),+ve value denoted in the direction of +ve  global x axis
    private double yLoad;//load intensity in (kN),+ve value denoted in the direction of +ve  global y axis
    private double zMoment;//load intensity in (kNm),+ve value for anti clockwise moment
    private LoadGroup loadCase;

    public double get_xLoad() {
        return xLoad;
    }

    public void set_xLoad(double xLoad) {
        this.xLoad = xLoad;
    }

    public double get_yLoad() {
        return yLoad;
    }

    public void set_yLoad(double yLoad) {
        this.yLoad = yLoad;
    }

    public double get_zMoment() {
        return zMoment;
    }

    public void set_zMoment(double zMoment) {
        this.zMoment = zMoment;
    }

    public LoadGroup getLoadCase() {
        return loadCase;
    }

    public void setLoadCase(LoadGroup loadCase) {
        this.loadCase = loadCase;
    }

    public double getNodalLoadId() {
        return NodalLoadId;
    }

    public void setNodalLoadId(double NodalLoadId) {
        this.NodalLoadId = NodalLoadId;
    }
    
    public NodalLoad(double xLoad, double yLoad, double zMoment, LoadGroup loadCase) {
        this.xLoad = xLoad;
        this.yLoad = yLoad;
        this.zMoment = zMoment;
        this.loadCase = loadCase;
    }
    
    

}
