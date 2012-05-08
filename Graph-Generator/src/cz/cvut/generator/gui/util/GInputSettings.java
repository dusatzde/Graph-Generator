/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.gui.util;

import cz.cvut.generator.graph.GraphType;
import java.util.List;

/**
 *
 * @author ZDENEK
 */
public class GInputSettings {
    
    private int nodeCount;
    private GraphType type;
    private List<GraphType> properties;
    private int n;
    private int m;
    private double minWeight;
    private double maxWeight;

    public GInputSettings(GraphType type, List<GraphType> properties) {
        this.type = type;
        this.properties = properties;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }

    public List<GraphType> getProperties() {
        return properties;
    }

    public void setProperties(List<GraphType> properties) {
        this.properties = properties;
    }

    public GraphType getType() {
        return type;
    }

    public void setType(GraphType type) {
        this.type = type;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public double getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(double minWeight) {
        this.minWeight = minWeight;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
