/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.graph;

import cz.cvut.generator.core.GeneratorOutputI;
import java.util.List;

/**
 *
 * @author ZDENEK
 */
public class Graph implements GraphConfigI {

    private boolean directed;
    private boolean weighted;
    private List<Edge> edges;
    private List<Node> nodes;

    public boolean isDirected() {
        return directed;
    }
    
    public boolean isWeighted(){
        return weighted;
    }

    @Override
    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    @Override
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Node> getNodes() {
        return this.nodes;
    }

    public List<Edge> getEdges() {
        return this.edges;
    }

    @Override
    public void setWeighted(boolean weighted) {
        this.weighted = weighted;
    }


}
