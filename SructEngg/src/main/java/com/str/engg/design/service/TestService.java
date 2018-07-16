package com.str.engg.design.service;


import com.str.engg.fem.loads.Fem_UDL;
import com.str.engg.fem.loads.LoadGroup;
import com.str.engg.fem.loads.NodalLoad;
import com.str.engg.fem.structure.Element;
import com.str.engg.fem.structure.Node;
import com.str.engg.fem.structure.Structure;

public class TestService {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Node n1 = new Node(0, 0);
        Node n2 = new Node(1, 3);
        Node n3 = new Node(5, 4);
        Node n4 = new Node(5, 0);

        n1.setSupport(true, true, true);
        n4.setSupport(true, true, true);

        LoadGroup deadLc = new LoadGroup("Dead");
        LoadGroup liveLc = new LoadGroup("Live");

        NodalLoad nl1 = new NodalLoad(100, 0, 0, deadLc);
        Element e1 = new Element(n1, n2);
        Element e2 = new Element(n2, n3);
        Element e3 = new Element(n3, n4);
        n2.addNodalLoad(nl1);
        //UDL udl1 = new UDL(10, deadLc);
        Fem_UDL udl2 = new Fem_UDL(50, liveLc);

        //e2.addUdl(udl1);
        e2.addUdl(udl2);

        Structure s = new Structure();
        s.addLoadGroup(deadLc);
        s.addLoadGroup(liveLc);
        s.addElement(e1);
        s.addElement(e2);
        s.addElement(e3);

        s.solve();
        long finishTime = System.currentTimeMillis();
        System.out.println("That took: " + (finishTime - startTime) + " ms");
    }

}
