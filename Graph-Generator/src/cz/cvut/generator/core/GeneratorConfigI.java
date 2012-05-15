/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.core;

import cz.cvut.generator.graph.GraphType;
import java.util.List;

/**
 *
 * @author ZDENEK
 */
public interface GeneratorConfigI {
    
    public void setNodesCount(int nodeCount);
    public void setProperties(List<GraphType> property);
    public void setMinEdgeWeight(double weight);
    public void setMaxEdgeWeight(double weight);
    public void setBipartiteNodesCount(int part1, int part2);
    

}
