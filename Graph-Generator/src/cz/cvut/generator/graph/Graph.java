/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.graph;

import java.util.List;

/**
 *
 * @author ZDENEK
 */
public class Graph implements GraphConfigI {
    
    private boolean directed;
    private List<Edge> edges;
    private List<Node> nodes;

    public boolean isDirected() {
        return directed;
    }

    @Override
    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    @Override
    public void setNodes(List<Node> nodes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEdges(List<Edge> edges) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
