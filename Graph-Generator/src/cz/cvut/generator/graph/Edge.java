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
    
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Edge)) return false;
        Edge h = (Edge)o;
        if(this.from == h.from && this.to == h.to && this.weight == h.weight) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.from != null ? this.from.hashCode() : 0);
        hash = 53 * hash + (this.to != null ? this.to.hashCode() : 0);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        return hash;
    }
    
    public Node getNodeFrom(){
        return this.from;
    }
    
    public Node getNodeTo(){
        return this.to;
    }
    
    public double getWeight(){
        return this.weight;
    }
    
}
