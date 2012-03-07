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
public interface GraphConfigI{

    public void setNodes(List<Node> nodes);
    public void setEdges(List<Edge> edges);
    public void setDirected(boolean directed);
    
}
