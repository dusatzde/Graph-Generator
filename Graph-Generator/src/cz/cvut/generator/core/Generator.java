/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.core;

import cz.cvut.generator.graph.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ZDENEK
 */
public class Generator implements GeneratorOutputI, GeneratorConfigI {
    Graph g;
    private int nodeCount;                     //number of nodes
    private double minWeight;                   //minimal weight of edge
    private double maxWeight;                   //maximal weight of edge
    private int componentCount;                 //number of graph components
    private ArrayList<GraphType> properties;    //list of graph properties
    private Node[] nodes;
    private ArrayList<Node> nodeList;
    private ArrayList<Edge> edgeList;
    private boolean directed;
    private boolean weighted;
    private Random rand;
    
    
    public Generator(){
        g = new Graph();
        directed = false;
        weighted = false;
        rand = new Random();
    }
    
    public void initialize(){
        generateNodes();
        edgeList = new ArrayList<Edge>();
        if(properties.contains(GraphType.DIRECTED))directed = true;
        if(properties.contains(GraphType.WEIGHTED))weighted = true;
    }
    
    //TODO
    public void generate(){
        
    }
    
    private void generateNodes(){
        nodes = new Node[nodeCount];
        //creation array of nodes with id from 0 to nodeCount
        for(int i = 0; i < nodeCount; i++){
            nodes[i] = new Node(i, "Node: " + Integer.toString(i));         //or should id's be random???
        }
        //shuffle array
        for(int i = 0; i < nodeCount; i++){
            int rna = rand.nextInt(nodeCount);
            int rnb = rand.nextInt(nodeCount);
            Node tmp = nodes[rna];
            nodes[rna] = nodes[rnb];
            nodes[rnb] = tmp;            
        }
        nodeList = new ArrayList<Node>(Arrays.asList(nodes));
    }
    
    public void generateComplete(){
        for(int i = 0; i < nodeCount; i++){
            for(int j = i+1; j < nodeCount; j++){
                edgeList.add(new Edge(nodes[i], nodes[j]));
            }
        }
    }
    
    public void generateSimple(){
        int maxDensity = nodeCount/20; //maximal number of edges from one node
        int noEdge = 0;
        ArrayList<Node> notUsed = new ArrayList<Node>(Arrays.asList(nodes));
        ArrayList<Node> connectedPart = new ArrayList<Node>();
        Node a = notUsed.remove(rand.nextInt(notUsed.size()));
        Node b = notUsed.remove(rand.nextInt(notUsed.size()));
        edgeList.add(new Edge(a, b));
        connectedPart.add(a);
        connectedPart.add(b);
        while(!notUsed.isEmpty()){
            a = notUsed.remove(rand.nextInt(notUsed.size()));
            //connect a to rest of yet connected graph
            b = connectedPart.get(rand.nextInt(connectedPart.size()));
            //switching orientation
            if(rand.nextInt(2) == 0) edgeList.add(new Edge(a, b));
            else edgeList.add(new Edge(b, a));
            connectedPart.add(a);
            //add random number of edges coming from a
            noEdge = rand.nextInt(maxDensity);
            for(int i = 0; i < noEdge; i++){
                b = nodes[rand.nextInt(nodeCount)];
                edgeList.add(new Edge(a, b));
                connectedPart.add(b);
            }          
        }
    }
    
    
    //TODO
    public void generateBipartite(){
        
    }
    
    //TODO
    public void generateCyclic(){
        
    }
    
    //TODO
    public void generateAcyclic(){
        
    }
    
    //TODO
    public void generateTree(){
        
    }
    
    //TO ERASE
    //dummy method for parser testing
    public void generateFullDummy(){
        int nodeCount = 1000;   //set number of nodes as you wish
        
        ArrayList<Edge> edges = new ArrayList<Edge>();
        ArrayList<Node> nodesList = new ArrayList<Node>();
        Node[] nodes = new Node[nodeCount];
        for(int i = 0; i < nodeCount; i++){
            nodes[i] = new Node(i+1, "N:"+(i+1));
            nodesList.add(nodes[i]);
        }
        for(int i = 0; i < nodeCount; i++){
            for(int j = i+1; j < nodeCount; j++){
                edges.add(new Edge(nodes[i], nodes[j]));
            }
        }
        
        g.setDirected(false);
        g.setEdges(edges);
        g.setNodes(nodesList);       
    }
    
    private void prepareOutputGraph(){
        if(weighted){
            for(Edge e: edgeList){
            e.setWeight(rand.nextDouble() * (maxWeight - minWeight) + minWeight);
            }
        }        
        g.setEdges(edgeList);
        g.setNodes(nodeList);
        g.setDirected(directed);
    }

    @Override
    public Graph getGraph() {
        prepareOutputGraph();
        return g;
    }

    @Override
    public void setNodesCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }

    @Override
    public void setProperties(List<GraphType> property) {
        this.properties = (ArrayList<GraphType>) property;
    }

    @Override
    public void setMinEdgeWeight(double weight) {
        this.minWeight = weight;
    }

    @Override
    public void setMaxEdgeWeight(double weight) {
        this.maxWeight = weight;
    }

    @Override
    public void setComponentCount(int componentCount) {
        this.componentCount = componentCount;
    }
    
    public List<GraphType> getProperties(){
        return this.properties;
    }
    
    
    
}
