/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.graph;

/**
 *
 * @author ZDENEK
 */
public class Node {
    private int id;
    private String label;
    
    public Node(int id, String label){
        this.id = id;
        this.label = label;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
