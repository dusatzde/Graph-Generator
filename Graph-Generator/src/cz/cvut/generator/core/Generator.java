/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.core;

import cz.cvut.generator.graph.*;
import java.util.ArrayList;

/**
 *
 * @author ZDENEK
 */
public class Generator implements GeneratorOutputI {
    Graph g;
    
    Generator(){
        g = new Graph();
    }
    
    //dummy method for parser testing
    public void generateFull(){
        int nodeCount = 1000;   //set number of nodes as you wish
        
        ArrayList<Edge> edges = new ArrayList<Edge>();
        ArrayList<Node> nodesList = new ArrayList<Node>();
        Node[] nodes = new Node[nodeCount];
        for(int i = 0; i < nodeCount; i++){
            nodes[i] = new Node(i+1, "N:"+(i+1));
            nodesList.add(nodes[i]);
        }
        for(int i = 0; i < nodeCount; i++){
            for(int j = i+1; j < nodeCount; j++){
                edges.add(new Edge(nodes[i], nodes[j], (double)(i+j)));
            }
        }
        
        g.setDirected(false);
        g.setEdges(edges);
        g.setNodes(nodesList);       
    }

    @Override
    public Graph getGraph() {
        return g;
    }
    
    
    
}
