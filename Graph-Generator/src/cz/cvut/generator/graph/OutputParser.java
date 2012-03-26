/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.graph;

import cz.cvut.generator.core.Generator;
import cz.cvut.generator.core.GeneratorOutputI;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class OutputParser {

    Generator gen;
    OutputType ot;

    public OutputParser(Generator gen, OutputType ot) {
        this.gen = gen;
        this.ot = ot;
    }

    public void generateOutput() throws FileNotFoundException {
        switch (ot) {
            case TRIVIAL:
                generateTrivialOutput();
                break;
            case ADJACENCY:
                generateAdjacencyOutput();
                break;
            case DOT:

                break;

            default:
                System.out.println("Incorrect output type");
        }
    }

    private void generateTrivialOutput() throws FileNotFoundException {
        System.out.println("Start of generating output file - Trivial Graph");
        PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"));
        Graph g = this.gen.getGraph();
        List<Node> nodes = g.getNodes();
        List<Edge> edges = g.getEdges();

        Iterator<Node> nodeIt = nodes.iterator();
        while (nodeIt.hasNext()) {
            out.println(nodeIt.next().getLabel());
        }

        Iterator<Edge> edgeIt = edges.iterator();
        while (edgeIt.hasNext()) {
            Edge e = edgeIt.next();
            out.println(e.getNodeFrom().getLabel() + " " + e.getNodeTo().getLabel());
        }
        out.close();
    }

    private void generateAdjacencyOutput() throws FileNotFoundException {
        System.out.println("Start of generating output file - Adjacency matrix");
        PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"));

        Graph g = this.gen.getGraph();
        List<GraphType> properties = gen.getProperties();
        List<Node> nodes = g.getNodes();
        List<Edge> edges = g.getEdges();
        boolean weighted = false;
        boolean directed = false;

        int[][] adjacencyMatrix = new int[nodes.size()][nodes.size()];

        Iterator<GraphType> it = properties.iterator();
        while (it.hasNext()) {
            GraphType gType = it.next();
            if (gType == GraphType.DIRECTED) {
                directed = true;
            }
            if (gType == GraphType.WEIGHTED) {
                weighted = true;
            }
        }


        Iterator<Edge> edgeIt = edges.iterator();
        Edge e;
        int from, to;

        if (weighted) {
            
        } else {
            while (edgeIt.hasNext()) {
                e = edgeIt.next();
                from = nodes.lastIndexOf(e.getNodeFrom());
                to = nodes.lastIndexOf(e.getNodeTo());
                adjacencyMatrix[from][to] = 1;
                if (directed) {
                    adjacencyMatrix[to][from] = -1;
                } else {
                    adjacencyMatrix[to][from] = 1;
                }
            }

            for (int i = 0; i < nodes.size(); i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    out.print(adjacencyMatrix[i][j] + " ");
                }
                out.println("");
            }
        }

        out.close();
    }
}