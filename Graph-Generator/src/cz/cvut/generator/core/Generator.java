/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.core;

import cz.cvut.generator.graph.Graph;

/**
 *
 * @author ZDENEK
 */
public class Generator implements GeneratorOutputI {
    Graph g;
    
    Generator(){
        
    }

    @Override
    public Graph getGraph() {
        return g;
    }
    
    
    
}
