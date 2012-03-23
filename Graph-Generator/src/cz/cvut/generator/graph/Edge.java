/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.graph;

/**
 *
 * @author ZDENEK
 */
public class Edge {
    
    public Edge(Node from, Node to, double weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    
    private Node from;
    private Node to;
    private double weight;
    
    
    
    
}
