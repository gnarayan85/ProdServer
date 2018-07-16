package com.str.engg.fem.structure;

import java.util.ArrayList;

import com.str.engg.fem.loads.LoadGroup;
import com.str.engg.fem.loads.NodalLoad;

public class Node {

    private final double x, y; //position coordinates in meters
    private final boolean[] support; //array of boolean x,y,z fixed = true,free = false

    private final ArrayList<NodalLoad> nodalLoadList; //array of nodal loads along x and y axis moment along z

    private final ArrayList<NodeResult> nodeResultList;

    public Node(double x, double y) {
        this.x = x;
        this.y = y;
        nodalLoadList = new ArrayList<NodalLoad>();
        nodeResultList = new ArrayList<NodeResult>();
        this.support = new boolean[3];

    }

    public void addNodalLoad(NodalLoad nLoad) {
        nodalLoadList.add(nLoad);

    }

    public void addNodeResults(NodeResult nodeResults) {
        nodeResultList.add(nodeResults);

    }

    public double[] getNodalLoad(LoadGroup lg) {
        double[] nodalLoad = new double[3];//array of total nodal loads along x and y axis moment along z
        //nodalLoad [0] = Fx (kN)
        //nodalLoad [1] = Fy (kN)
        //nodalLoad [2] = Mz (kNm)
        nodalLoadList.stream()
                .filter((NodalLoad e) -> e.getLoadCase().equals(lg))
                .forEach((NodalLoad e) -> {
                    nodalLoad[0] = nodalLoad[0] + e.get_xLoad();
                    nodalLoad[1] = nodalLoad[1] + e.get_yLoad();
                    nodalLoad[2] = nodalLoad[2] + e.get_zMoment();

                });

        return nodalLoad;
    }

    public void setSupport(boolean isFixed_x, boolean isFixed_y, boolean isFixed_z) {

        support[0] = isFixed_x;
        support[1] = isFixed_y;
        support[2] = isFixed_z;

    }

    public boolean xIsFixed() {
        return support[0];
    }

    public boolean yIsFixed() {
        return support[1];
    }

    public boolean zIsFixed() {
        return support[2];
    }

    public double get_x() {
        return x;
    }

    public double get_y() {
        return y;
    }

}
