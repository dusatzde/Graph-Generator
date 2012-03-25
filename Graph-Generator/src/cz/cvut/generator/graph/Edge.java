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
    
    public Edge(Node from, Node to){
        this.from = from;
        this.to = to;
        weight = 0;
    }
    
    private Node from;
    private Node to;
    private double weight;
    
    public void setWeight(double weight){
                this.weight = weight;

    }
    
    @Override
    public String toString(){
        return "from: " + from.getId() + " to: " + to.getId() + " weight: " + weight;
    }
    
}
