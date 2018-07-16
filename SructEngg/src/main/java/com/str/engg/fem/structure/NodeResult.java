/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.str.engg.fem.structure;

import com.str.engg.fem.loads.LoadGroup;

public class NodeResult {

    private LoadGroup loadGroup;
    private double fx;//x force kN
    private double fy;//y force kN
    private double mz;//z moment kNm
    private double ux; //x displacement m
    private double uy; //y displacement m
    private double uz; //z displacement rad

    public LoadGroup getLoadGroup() {
        return loadGroup;
    }

    public void setLoadGroup(LoadGroup loadGroup) {
        this.loadGroup = loadGroup;
    }

    public double getFx() {
        return fx;
    }

    public void setFx(double fx) {
        this.fx = fx;
    }

    public double getFy() {
        return fy;
    }

    public void setFy(double fy) {
        this.fy = fy;
    }

    public double getMz() {
        return mz;
    }

    public void setMz(double mz) {
        this.mz = mz;
    }

    public double getUx() {
        return ux;
    }

    public void setUx(double ux) {
        this.ux = ux;
    }

    public double getUy() {
        return uy;
    }

    public void setUy(double uy) {
        this.uy = uy;
    }

    public double getUz() {
        return uz;
    }

    public void setUz(double uz) {
        this.uz = uz;
    }

    public NodeResult(LoadGroup loadGroup, double fx, double fy, double mz, double ux, double uy, double uz) {
        this.loadGroup = loadGroup;
        this.fx = fx;
        this.fy = fy;
        this.mz = mz;
        this.ux = ux;
        this.uy = uy;
        this.uz = uz;
    }
    
    
    
    

}
