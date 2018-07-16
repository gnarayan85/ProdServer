package com.str.engg.fem.structure;

import java.util.ArrayList;

import com.str.engg.fem.loads.Fem_UDL;
import com.str.engg.fem.loads.LoadGroup;
import com.str.engg.fem.loads.PointLoadOnBeam;

import Jama.Matrix;

public class Element {

    private final Node ni, nk;//i start node ,k end node
    private double length; //length of the element in meter
    private double lambda_x;
    private double lambda_y;
    private final Matrix elementK; // element stiffness matrix
    private final Matrix T; // transformation matrix d(local)=T x D (global)

    private final double A = 0.09; //cross sectional area in (m^2)
    private final double E = 21718500; // Elastic modulus (kN/m^2)
    private final double I = .000675; // moment of inertia (m^4)
    private final ArrayList<Fem_UDL> udlList;
    private ArrayList<PointLoadOnBeam> pointLoadList;

    private final ArrayList<ElementResult> elementResultList;

    public ArrayList<ElementResult> getElementResultList() {
		return elementResultList;
	}

	public void addElementResult(ElementResult elementResult) {
        elementResultList.add(elementResult);

    }

    public void addPointload(PointLoadOnBeam p) {
        pointLoadList.add(p);
    }

    public void addUdl(Fem_UDL udl) {
        udlList.add(udl);
    }

    private void hasChanged() {

        this.length = Math.sqrt((nk.get_x() - ni.get_x()) * (nk.get_x() - ni.get_x()) + (nk.get_y() - ni.get_y()) * (nk.get_y() - ni.get_y()));
        this.lambda_x = (nk.get_x() - ni.get_x()) / length;
        this.lambda_y = (nk.get_y() - ni.get_y()) / length;

        double a = A * E / length;
        double b = 12 * E * I / (length * length * length);
        double c = 6 * E * I / (length * length);
        double d = 4 * E * I / length;
        double e = 2 * E * I / length;

        elementK.set(0, 0, a);
        elementK.set(0, 3, -a);

        elementK.set(1, 1, b);
        elementK.set(1, 2, c);
        elementK.set(1, 4, -b);
        elementK.set(1, 5, c);

        elementK.set(2, 1, c);
        elementK.set(2, 2, d);
        elementK.set(2, 4, -c);
        elementK.set(2, 5, e);

        elementK.set(3, 0, -a);
        elementK.set(3, 3, a);

        elementK.set(4, 1, -b);
        elementK.set(4, 2, -c);
        elementK.set(4, 4, b);
        elementK.set(4, 5, -c);

        elementK.set(5, 1, c);
        elementK.set(5, 2, e);
        elementK.set(5, 4, -c);
        elementK.set(5, 5, d);

        T.set(0, 0, lambda_x);
        T.set(1, 1, lambda_x);
        T.set(2, 2, 1);
        T.set(3, 3, lambda_x);
        T.set(4, 4, lambda_x);
        T.set(5, 5, 1);
        T.set(0, 1, lambda_y);
        T.set(1, 0, -1 * lambda_y);
        T.set(3, 4, lambda_y);
        T.set(4, 3, -1 * lambda_y);

    }

    public double[][] getFixedEndForces(LoadGroup lg) {

        double[][] fixedEndForces = new double[6][2];
        //globalFixedEndForces[0] =Qix 
        //globalFixedEndForces[1] =Qiy 
        //globalFixedEndForces[2] =Qiz
        //globalFixedEndForces[3] =Qkx 
        //globalFixedEndForces[4] =Qky
        //globalFixedEndForces[5] =Qkz

        
     
        
        udlList.stream() // need to do this for point load list

                .filter((Fem_UDL e) -> e.getLoadCase().equals(lg))
                .forEach((Fem_UDL e) -> {
                    double w = e.getLoad();
                    //global fixed end forces
                    fixedEndForces[0][0] = fixedEndForces[0][0] - w * length * lambda_y / 2;        //Qix   changed to -ve
                    fixedEndForces[1][0] = fixedEndForces[1][0] + w * length * lambda_x / 2;     //Qiy   changed to +ve
                    fixedEndForces[2][0] = fixedEndForces[2][0] + w * length * length / 12;    //Qiz   
                    fixedEndForces[3][0] = fixedEndForces[3][0] - w * length * lambda_y / 2;        //Qkx   -1*-1=1
                    fixedEndForces[4][0] = fixedEndForces[4][0] + w * length * lambda_x / 2;     //Qky   
                    fixedEndForces[5][0] = fixedEndForces[5][0] - w * length * length / 12;       //Qkz   

                    //local fixed end forces
                    fixedEndForces[1][1] = fixedEndForces[1][1] + w * length / 2;     //Qiy   changed to +ve
                    fixedEndForces[2][1] = fixedEndForces[2][1] + w * length * length / 12;    //Qiz   
                    fixedEndForces[4][1] = fixedEndForces[4][1] + w * length / 2;     //Qky   
                    fixedEndForces[5][1] = fixedEndForces[5][1] - w * length * length / 12;

                });

        return fixedEndForces;
    }

    public double getLength() {

        return length;
    }

    public double get_lambda_x() {

        return lambda_x;
    }

    public double get_lambda_y() {
        return lambda_y;
    }

    public Node getNi() {
        return ni;
    }

    public Node getNk() {
        return nk;
    }

    public double getA() {
        return A;
    }

    public double getE() {
        return E;
    }

    public double getI() {
        return I;
    }

    public Matrix getElementK() {
        return elementK;
    }

    public Matrix getT() {
        return T;
    }

    //constructor
    public Element(Node ni, Node nk) {
        this.ni = ni;
        this.nk = nk;
        udlList = new ArrayList<Fem_UDL>();
        elementResultList = new ArrayList<ElementResult>();
        elementK = new Matrix(6, 6);
        T = new Matrix(6, 6);
        hasChanged();

    }

}
