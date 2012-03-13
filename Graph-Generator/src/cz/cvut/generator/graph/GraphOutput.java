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
public class GraphOutput{
    Generator gen;
    OutputType ot;
    
    GraphOutput(Generator gen, OutputType ot){
        this.gen = gen;
        this.ot = ot;
    }

    public void generateOutput() throws FileNotFoundException{
        switch(ot){
            case TRIVIAL:
                generateTrivialOutput();
                break;
            case DOT:
                
                break;
                
            default:
                System.out.println("Incorrect output type");
        }
    }
    private void generateTrivialOutput() throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"));
        Graph g = this.gen.getGraph();
        List<Node> nodes = g.getNodes();
        List<Edge> edges = g.getEdges();
        
        Iterator<Node> nodeIt = nodes.iterator();
        while(nodeIt.hasNext()){
            out.println(nodeIt.next());
        }
        
        Iterator<Edge> edgeIt = edges.iterator();
        while(edgeIt.hasNext()){
            out.println(edgeIt.next());
        }
        out.close();
    }


    
}
