/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator;

import cz.cvut.generator.core.Generator;
import cz.cvut.generator.graph.Edge;
import cz.cvut.generator.graph.Graph;
import cz.cvut.generator.graph.GraphType;
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
        Generator g = new Generator();
        g.setNodesCount(10);
        g.setMaxEdgeWeight(600);
        g.setMinEdgeWeight(6);
        ArrayList<GraphType> prop = new ArrayList<GraphType>();
        prop.add(GraphType.WEIGHTED);
        g.setProperties(prop);
        g.initialize();
        g.generateSimple();
        Graph graf = g.getGraph();
        OutputParser op = new OutputParser(g, OutputType.TRIVIAL);
        op.generateOutput();
    }
}
