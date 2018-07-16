package com.str.engg.fem.structure;
import java.util.ArrayList;

import com.str.engg.fem.loads.LoadGroup;

import Jama.Matrix;

public class Structure {

    private final ArrayList<Element> elementList;
    private final ArrayList<Node> nodeList;
    private int dof; //total no of degree of freedome = no of nodes x 3
    private Matrix globalK; //global stiffness matrix
    private Matrix globalK_reduced; // global stiffness matrix reduced at supports
    private Matrix globalF; //global force matrix for given load case
    private Matrix globalU; //global displacement matrix for given load case
    private Matrix reactionR; //global reaction matrix for given load case

    private final ArrayList<LoadGroup> loadGroupList;

    public Structure() {

        elementList = new ArrayList<>();
        nodeList = new ArrayList<>();
        loadGroupList = new ArrayList<>();

    }

    public ArrayList<Element> getElementList() {
		return elementList;
	}

	public void addElement(Element element) {
        elementList.add(element);

    }

    public void addLoadGroup(LoadGroup loadGroup) {
        loadGroupList.add(loadGroup);

    }

    private void printMatrix(Matrix k) {

        for (int i = 0; i < k.getRowDimension(); i++) {
            for (int j = 0; j < k.getColumnDimension(); j++) {
                System.out.format("%.2f ", k.get(i, j));
            }
            System.out.print("\n");
        }
    }

    private void createGlobalStiffnessK() {

        elementList.stream().forEach((element) -> {
            if (!nodeList.contains(element.getNi())) {
                nodeList.add(element.getNi());
            }
            if (!nodeList.contains(element.getNk())) {
                nodeList.add(element.getNk());
            }
        });
        dof = nodeList.size() * 3;
        globalK = new Matrix(dof, dof);

        elementList.stream().forEach((element) -> {

            int i = nodeList.indexOf(element.getNi()) * 3;  //denotes index of first node of the element
            int k = nodeList.indexOf(element.getNk()) * 3;  //denotes index of second node of the element

            double L = element.getLength();
            double lambdaX = element.get_lambda_x();
            double lambdaY = element.get_lambda_y();
            double lambdaX_squared = element.get_lambda_x() * element.get_lambda_x();
            double lambdaX_times_lambdaY = element.get_lambda_x() * element.get_lambda_y();
            double lambdaY_squared = element.get_lambda_y() * element.get_lambda_y();
            double A = element.getA();
            double E = element.getE();
            double I = element.getI();

            double a = A * E / L;
            double b = 12 * E * I / (L * L * L);
            double c = 6 * E * I / (L * L);
            double d = 4 * E * I / L;
            double e = 2 * E * I / L;

            double _0_0 = a * lambdaX_squared + b * lambdaY_squared;
            double _0_1 = (a - b) * lambdaX_times_lambdaY;
            double _0_2 = -c * lambdaY;
            double _0_3 = -(a * lambdaX_squared + b * lambdaY_squared);
            double _0_4 = -(a - b) * lambdaX_times_lambdaY;
            double _0_5 = -c * lambdaY;
            double _1_1 = a * lambdaY_squared + b * lambdaX_squared;
            double _1_2 = c * lambdaX;
            double _1_3 = -(a - b) * lambdaX_times_lambdaY;
            double _1_4 = -(a * lambdaY_squared + b * lambdaX_squared);
            double _1_5 = c * lambdaX;
            double _2_2 = d;
            double _2_3 = c * lambdaY;
            double _2_4 = -c * lambdaX;
            double _2_5 = e;
            double _3_3 = a * lambdaX_squared + (b * lambdaY_squared);
            double _3_4 = (a - b) * lambdaX_times_lambdaY;
            double _3_5 = c * lambdaY;
            double _4_4 = a * lambdaY_squared + (b * lambdaX_squared);
            double _4_5 = -c * lambdaX;
            double _5_5 = d;

            double _1_0 = _0_1;
            double _2_0 = _0_2;
            double _2_1 = _1_2;
            double _3_0 = _0_3;
            double _3_1 = _1_3;
            double _3_2 = _2_3;
            double _4_0 = _0_4;
            double _4_1 = _1_4;
            double _4_2 = _2_4;
            double _4_3 = _3_4;
            double _5_0 = _0_5;
            double _5_1 = _1_5;
            double _5_2 = _2_5;
            double _5_3 = _3_5;
            double _5_4 = _4_5;

            globalK.set((i), (i), (globalK.get((i), (i)) + _0_0));// ok 0,0
            globalK.set((i), (i + 1), (globalK.get((i), (i + 1)) + _0_1));// ok 0,1
            globalK.set((i), (i + 2), (globalK.get((i), (i + 2)) + _0_2));// ok 0,2
            globalK.set((i), (k), (globalK.get((i), (k)) + _0_3));// ok 0,3
            globalK.set((i), (k + 1), (globalK.get((i), (k + 1)) + _0_4));// ok 0,4
            globalK.set((i), (k + 2), (globalK.get((i), (k + 2)) + _0_5));// ok 0,5

            globalK.set((i + 1), (i), (globalK.get((i + 1), (i)) + _1_0));// ok 1,0
            globalK.set((i + 1), (i + 1), (globalK.get((i + 1), (i + 1)) + _1_1));// ok 1,1
            globalK.set((i + 1), (i + 2), (globalK.get((i + 1), (i + 2)) + _1_2));// ok 1,2
            globalK.set((i + 1), (k), (globalK.get((i + 1), (k)) + _1_3));// ok 1,3
            globalK.set((i + 1), (k + 1), (globalK.get((i + 1), (k + 1)) + _1_4));// ok 1,4
            globalK.set((i + 1), (k + 2), (globalK.get((i + 1), (k + 2)) + _1_5));// ok 1,5

            globalK.set((i + 2), (i), (globalK.get((i + 2), (i)) + _2_0));// ok 2,0
            globalK.set((i + 2), (i + 1), (globalK.get((i + 2), (i + 1)) + _2_1));// ok 2,1
            globalK.set((i + 2), (i + 2), (globalK.get((i + 2), (i + 2)) + _2_2));// ok 2,2
            globalK.set((i + 2), (k), (globalK.get((i + 2), (k)) + _2_3));// ok 2,3
            globalK.set((i + 2), (k + 1), (globalK.get((i + 2), (k + 1)) + _2_4));// ok 2,4
            globalK.set((i + 2), (k + 2), (globalK.get((i + 2), (k + 2)) + _2_5));// ok 2,5

            globalK.set((k), (i), (globalK.get((k), (i)) + _3_0));// ok 3,0
            globalK.set((k), (i + 1), (globalK.get((k), (i + 1)) + _3_1));// ok 3,1
            globalK.set((k), (i + 2), (globalK.get((k), (i + 2)) + _3_2));// ok 3,2
            globalK.set((k), (k), (globalK.get((k), (k)) + _3_3));// ok 3,3
            globalK.set((k), (k + 1), (globalK.get((k), (k + 1)) + _3_4));// ok 3,4
            globalK.set((k), (k + 2), (globalK.get((k), (k + 2)) + _3_5));// ok 3,5

            globalK.set((k + 1), (i), (globalK.get((k + 1), (i)) + _4_0));// ok 4,0
            globalK.set((k + 1), (i + 1), (globalK.get((k + 1), (i + 1)) + _4_1));// ok 4,1
            globalK.set((k + 1), (i + 2), (globalK.get((k + 1), (i + 2)) + _4_2));// ok 4,2
            globalK.set((k + 1), (k), (globalK.get((k + 1), (k)) + _4_3));// ok 4,3
            globalK.set((k + 1), (k + 1), (globalK.get((k + 1), (k + 1)) + _4_4));// ok 4,4
            globalK.set((k + 1), (k + 2), (globalK.get((k + 1), (k + 2)) + _4_5));// ok 4,5

            globalK.set((k + 2), (i), (globalK.get((k + 2), (i)) + _5_0));// ok 5,0
            globalK.set((k + 2), (i + 1), (globalK.get((k + 2), (i + 1)) + _5_1));// ok 5,1
            globalK.set((k + 2), (i + 2), (globalK.get((k + 2), (i + 2)) + _5_2));// ok 5,2
            globalK.set((k + 2), (k), (globalK.get((k + 2), (k)) + _5_3));// ok 5,3
            globalK.set((k + 2), (k + 1), (globalK.get((k + 2), (k + 1)) + _5_4));// ok 5,4
            globalK.set((k + 2), (k + 2), (globalK.get((k + 2), (k + 2)) + _5_5));// ok 5,5

        });

    }

    private void createGlobalF(LoadGroup lg) {

        globalF = new Matrix(dof, 1); //global force matrix

        elementList.stream()
                .forEach(e -> {
                    double[][] globalFixedEndForces = e.getFixedEndForces(lg);

                    int i = nodeList.indexOf(e.getNi()) * 3;
                    int k = nodeList.indexOf(e.getNk()) * 3;

                    globalF.set(i, 0, globalF.get(i, 0) + -1 * globalFixedEndForces[0][0]);
                    globalF.set(i + 1, 0, globalF.get(i + 1, 0) + -1 * globalFixedEndForces[1][0]);
                    globalF.set(i + 2, 0, globalF.get(i + 2, 0) + -1 * globalFixedEndForces[2][0]);
                    globalF.set(k, 0, globalF.get(k, 0) + -1 * globalFixedEndForces[3][0]);
                    globalF.set(k + 1, 0, globalF.get(k + 1, 0) + -1 * globalFixedEndForces[4][0]);
                    globalF.set(k + 2, 0, globalF.get(k + 2, 0) + -1 * globalFixedEndForces[5][0]);

                });

        reactionR = new Matrix(dof, 1);

        reactionR = globalF.copy().times(-1);// gets a copy of negative fixed end reactions

    }

    public void getElementEndForces(LoadGroup lg) {

        elementList.stream()
                .forEach((Element e) -> {

                    int i = nodeList.indexOf(e.getNi()) * 3;
                    int k = nodeList.indexOf(e.getNk()) * 3;

                    double[][] fixedEndForces = e.getFixedEndForces(lg);
                    Matrix elementK = e.getElementK();
                    Matrix elementT = e.getT();
                    Matrix elementD = new Matrix(6, 1);
                    Matrix element_q;

                    elementD.set(0, 0, globalU.get(i, 0));
                    elementD.set(1, 0, globalU.get(i + 1, 0));
                    elementD.set(2, 0, globalU.get(i + 2, 0));
                    elementD.set(3, 0, globalU.get(k, 0));
                    elementD.set(4, 0, globalU.get(k + 1, 0));
                    elementD.set(5, 0, globalU.get(k + 2, 0));

                    element_q = elementK.times(elementT).times(elementD);

                    element_q.set(1, 0, (element_q.get(1, 0) + fixedEndForces[1][1]));
                    element_q.set(2, 0, (element_q.get(2, 0) + fixedEndForces[2][1]));
                    element_q.set(4, 0, (element_q.get(4, 0) + fixedEndForces[4][1]));
                    element_q.set(5, 0, (element_q.get(5, 0) + fixedEndForces[5][1]));

                    e.addElementResult(new ElementResult(lg, element_q.get(0, 0), element_q.get(1, 0), element_q.get(2, 0), element_q.get(3, 0), element_q.get(4, 0), element_q.get(5, 0)));

                    //System.out.println("element force matrix is ");
                   // printMatrix(element_q);
                    //System.out.println("\n\n");

                });

    }

    public void solve() {

        createGlobalStiffnessK();

        loadGroupList.stream()
                .forEach((LoadGroup lg) -> {

                    createGlobalF(lg);

                    globalU = new Matrix(dof, 1); //initialising global displacement matrix
                    globalK_reduced = new Matrix(dof, dof); //initialising reduced stiffness matrix
                    globalK_reduced = globalK_reduced.plus(globalK); //getting a copy of K into reduced stiffness matrix

                    //changing global force matrix for nodal loads and supports
                    nodeList.stream().forEach(node -> {

                        int i = nodeList.indexOf(node) * 3;
                        double[] nodalLoads = node.getNodalLoad(lg); // getting total Fx Fy Fz from the node for the given load case

                        if (node.xIsFixed()) {                                  //if the x is restrained for node corresponding force for the 'dof' is made into zero
                            globalF.set(i, 0, 0);
                            for (int j = 0; j < dof; j++) {
                                if (i == j) {
                                    globalK_reduced.set(i, j, 1);              // reducing stiffness matrix
                                } else {
                                    globalK_reduced.set(i, j, 0);
                                    globalK_reduced.set(j, i, 0);
                                }
                            }
                        } else {
                            globalF.set(i, 0, (globalF.get(i, 0) + nodalLoads[0]));

                        }
                        if (node.yIsFixed()) {
                            globalF.set(i + 1, 0, 0);
                            for (int j = 0; j < dof; j++) {
                                if (i + 1 == j) {
                                    globalK_reduced.set(i + 1, j, 1);
                                } else {

                                    globalK_reduced.set(i + 1, j, 0);
                                    globalK_reduced.set(j, i + 1, 0);
                                }

                            }
                        } else {
                            globalF.set(i + 1, 0, (globalF.get(i + 1, 0) + nodalLoads[1]));

                        }

                        if (node.zIsFixed()) {
                            globalF.set(i + 2, 0, 0);
                            for (int j = 0; j < dof; j++) {
                                if (i + 2 == j) {
                                    globalK_reduced.set(i + 2, j, 1);
                                } else {

                                    globalK_reduced.set(i + 2, j, 0);
                                    globalK_reduced.set(j, i + 2, 0);
                                }

                            }
                        } else {

                            globalF.set(i + 2, 0, (globalF.get(i + 2, 0) + nodalLoads[2]));
                        }

                    });

                    globalU = globalK_reduced.solve(globalF);  //solving the displacements 

                    globalF = globalK.times(globalU);

                    nodeList.stream().forEach(node -> {

                        int i = nodeList.indexOf(node) * 3;

                        node.addNodeResults(new NodeResult(lg, globalF.get(i, 0), globalF.get(i + 1, 0), globalF.get(i + 2, 0), globalU.get(i, 0), globalU.get(i + 1, 0), globalU.get(i + 2, 0)));

                    });

                   // System.out.println("\n\n");

                    //System.out.println("Global F matrix");
                    //printMatrix(globalF);

                    //System.out.println("\n\n");

                    reactionR = reactionR.plus(globalF);
                    //System.out.println("Global reactions");
                    //printMatrix(reactionR);

                    getElementEndForces(lg);

                });

    }

}
