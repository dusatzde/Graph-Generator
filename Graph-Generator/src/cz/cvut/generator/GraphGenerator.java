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
import cz.cvut.generator.gui.MainWindow;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

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
         * UI Thead
         */
//        SwingUtilities.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                MainWindow m = new MainWindow();
//                m.setVisible(true);
//            }
//        });



        /*
         * TOTO MUZETE SAMOZREJME SMAZAT
         */
        Generator g = new Generator();
        g.setNodesCount(10);
        g.setMaxEdgeWeight(600);
        g.setMinEdgeWeight(6);
        ArrayList<GraphType> prop = new ArrayList<GraphType>();
        //prop.add(GraphType.SIMPLE);
        //prop.add(GraphType.WEIGHTED);
        //prop.add(GraphType.DIRECTED);
        g.setProperties(prop);
        g.initialize();
        g.generate();
        Graph graf = g.getGraph();
        for (Edge e : graf.getEdges()) {
            System.out.println(e);
        }
        //OutputParser op = new OutputParser(g, OutputType.DOT);
        //op.generateOutput();
    }
}
