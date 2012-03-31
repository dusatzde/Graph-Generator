/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator;

import cz.cvut.generator.core.Generator;
import cz.cvut.generator.graph.Edge;
import cz.cvut.generator.graph.Graph;
import cz.cvut.generator.graph.GraphType;
import cz.cvut.generator.graph.Node;
import cz.cvut.generator.graph.OutputParser;
import cz.cvut.generator.graph.OutputType;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ZDENEK
 */
public class GraphGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        /*
         * TOTO MUZETE SAMOZREJME SMAZAT
         */
        
//        ArrayList<Edge> hrany  = new ArrayList<Edge>();
//        Node n1 = new Node(1, "ahoj");
//        Node n2 = new Node(2, "hi");
//        Edge h = new Edge(n1, n2);
//        hrany.add(h);
//        h = new Edge(n1, n2);
//        
//        System.out.println(hrany.contains(h));
        
        Generator g = new Generator();
        g.setNodesCount(10);
        g.setMaxEdgeWeight(600);
        g.setMinEdgeWeight(6);
        ArrayList<GraphType> prop = new ArrayList<GraphType>();
        prop.add(GraphType.WEIGHTED);
        prop.add(GraphType.DIRECTED);
        g.setProperties(prop);
        g.initialize();
        g.generateBipartite();
        Graph graf = g.getGraph();
        for(Edge e: graf.getEdges()) System.out.println(e);
        //OutputParser op = new OutputParser(g, OutputType.INCIDENCE);
        //op.generateOutput();
    }
}
