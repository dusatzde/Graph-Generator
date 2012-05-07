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

    Graph graph;
    Generator gen;
    OutputType ot;

    public OutputParser(Generator gen, OutputType ot) {
        this.gen = gen;
        this.ot = ot;
        this.graph = gen.getGraph();
        System.out.println("Generator ");
    }

    public void generateOutput() throws FileNotFoundException {
        System.out.println(ot);
        switch (ot) {
            case TRIVIAL:
                generateTrivialOutput();
                break;
            case ADJACENCY:
                generateAdjacencyOutput();
                break;
            case INCIDENCE:
                generateIncidenceOutput();
                break;
            case DOT:
                generateDOT();
                break;
            case XML:
                generateXML();
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

        double[][] adjacencyMatrix = new double[nodes.size()][nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                adjacencyMatrix[i][j] = Double.MAX_VALUE;
            }
        }

        directed = graph.isDirected();
        weighted = graph.isWeighted();


        Iterator<Edge> edgeIt = edges.iterator();
        Edge e;
        int from, to;


        while (edgeIt.hasNext()) {
            e = edgeIt.next();
            from = nodes.lastIndexOf(e.getNodeFrom());
            to = nodes.lastIndexOf(e.getNodeTo());
            if (weighted) {
                if (adjacencyMatrix[from][to] > e.getWeight()) {
                    adjacencyMatrix[from][to] = e.getWeight();
                }
                if (!directed) {
                    if (adjacencyMatrix[to][from] > e.getWeight()) {
                        adjacencyMatrix[to][from] = e.getWeight();
                    }
                }
            } else {
                adjacencyMatrix[from][to] = 1;
                if (directed) {
                    adjacencyMatrix[to][from] = -1;
                } else {
                    adjacencyMatrix[to][from] = 1;
                }
            }
        }

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (adjacencyMatrix[i][j] != Double.MAX_VALUE) {
                    out.print(adjacencyMatrix[i][j] + " ");
                } else {
                    if (weighted) {
                        out.print("inf ");
                    } else {
                        out.print("0 ");
                    }
                }
            }
            out.println("");
        }


        out.close();
    }

    private void generateIncidenceOutput() throws FileNotFoundException {
        System.out.println("Start of generating output file - Incidence matrix Graph");
        PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"));

        Graph g = this.gen.getGraph();
        List<Node> nodes = g.getNodes();
        List<Edge> edges = g.getEdges();
        Iterator<Edge> it = edges.iterator();
        double[][] incidenceMatrix = new double[nodes.size()][edges.size()];

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                incidenceMatrix[i][j] = 0;
            }
        }


        int j = 0;
        Edge e;
        while (it.hasNext()) {
            e = it.next();
            incidenceMatrix[nodes.lastIndexOf(e.getNodeFrom())][j] = 1;
            incidenceMatrix[nodes.lastIndexOf(e.getNodeTo())][j] = 1;
            j++;
        }

        for (int i = 0; i < nodes.size(); i++) {
            for (j = 0; j < edges.size(); j++) {
                out.print((int) incidenceMatrix[i][j] + " ");
            }
            out.println();
        }
        out.close();

    }

    private void generateDOT() throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new FileOutputStream("output.dot"));
        List<Edge> edges = graph.getEdges();
        List<Node> nodes = graph.getNodes();
        Iterator<Edge> it = edges.iterator();
        Iterator<Node> itn = nodes.iterator();
        while (itn.hasNext()) {
            Node n = itn.next();
            out.println(n.getId());
        }

        if (graph.isDirected()) {
            out.println("digraph graphname{");
            if (graph.isWeighted()) {
                System.out.println("Start of generating output file - DOT (directed, weighted");
                while (it.hasNext()) {
                    Edge e = it.next();
                    out.println(e.getNodeFrom().getId() + " -> " + e.getNodeTo().getId() + "[label=" + e.getWeight() + "]");
                }
            } else {
                System.out.println("Start of generating output file - DOT (directed, not weighted");
                while (it.hasNext()) {
                    Edge e = it.next();
                    out.println(e.getNodeFrom().getId() + " -> " + e.getNodeTo().getId());
                }
            }
        } else {
            out.println("graph graphname{");
            if (graph.isWeighted()) {
                System.out.println("Start of generating output file - DOT (not directed, weighted");
                while (it.hasNext()) {
                    Edge e = it.next();
                    out.println(e.getNodeFrom().getId() + " -- " + e.getNodeTo().getId() + "[label=" + e.getWeight() + "]");
                }
            } else {
                System.out.println("Start of generating output file - DOT (not directed, not weighted");
                while (it.hasNext()) {
                    Edge e = it.next();
                    out.println(e.getNodeFrom().getId() + " -- " + e.getNodeTo().getId());
                }
            }

        }

        out.println(
                "}");
        out.close();
    }

    private void generateXML() throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new FileOutputStream("output.xml"));
        List<Edge> edges = graph.getEdges();
        List<Node> nodes = graph.getNodes();

        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        out.print("<graph weighted=\"");
        if (graph.isWeighted()) {
            out.print("true");
        } else {
            out.print("false");
        }
        out.print("\" directed=\"");

        if (graph.isDirected()) {
            out.println("true\">");
        } else {
            out.println("false\">");
        }

        Iterator<Node> itn = nodes.iterator();
        out.println("   <nodes>");

        while (itn.hasNext()) {
            Node n = itn.next();
            out.println("      <node>");
            out.println("         <name> " + n.getId() + " </name>");
            out.println("      </node>");
        }
        out.println("   </nodes>");

        Iterator<Edge> ite = edges.iterator();

        out.println("   <edges>");
        while (ite.hasNext()) {
            Edge e = ite.next();
            out.println("      <edge>");
            out.println("         <nodeFrom>" + e.getNodeFrom().getId() + "</nodeFrom>");
            out.println("         <nodeTo>" + e.getNodeTo().getId() + "</nodeTo>");
            if (graph.isWeighted()) {
                out.println("         <weight>" + e.getWeight() + "</weight>");
            }
            out.println("      </edge>");
        }

        out.println("   </edges>");
        out.println("</graph>");
        out.close();


    }
}
