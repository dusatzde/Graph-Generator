/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.core;

import cz.cvut.generator.graph.*;
import java.util.*;

/**
 *
 * @author Jan Mikolasek, (Function Tree - Jakub Levy)
 */
public class Generator implements GeneratorOutputI, GeneratorConfigI {

    Graph g;
    private int nodeCount;                     //number of nodes
    private int bipartiteNodeCountP1;
    private int bipartiteNodeCountP2;
    private double minWeight;                   //minimal weight of edge
    private double maxWeight;                   //maximal weight of edge
    private ArrayList<GraphType> properties;    //list of graph properties
    private Node[] nodes;
    private ArrayList<Node> nodeList;
    private ArrayList<Edge> edgeList;
    private boolean directed;
    private boolean weighted;
    private Random rand;
    private GraphType graphType;

    public Generator() {
        g = new Graph();
        directed = false;
        weighted = false;
        rand = new Random();
    }

    public void initialize() {
        edgeList = new ArrayList<Edge>();
        if (properties.contains(GraphType.DIRECTED)) {
            directed = true;
        }
        if (properties.contains(GraphType.WEIGHTED)) {
            weighted = true;
        }
        if (properties.isEmpty()) {
            graphType = GraphType.SIMPLE;
        } else {
            graphType = properties.get(0);
        }
        if(graphType == GraphType.BIPARTITE) nodeCount = bipartiteNodeCountP1 + bipartiteNodeCountP2; 
        generateNodes();
    }

    //TODO
    public void generate() {
        initialize();
        switch (graphType) {
            case COMPLETE:
                generateComplete();
                break;
            case SIMPLE:
                generateSimple();
                break;
            case BIPARTITE:
                generateBipartite();
                break;
            case CYCLIC:
                generateCyclic();
                break;
            case ACYCLIC:
                generateAcyclic();
                break;
            case TREE:
                generateTree();
                break;
            case DISCRETE:
                break;
            default:
                break;
        }
    }

    private void generateNodes() {
        nodes = new Node[nodeCount];
        //creation array of nodes with id from 0 to nodeCount
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new Node(i, "Node: " + Integer.toString(i));         //or should id's be random???
        }
        //shuffle array
        for (int i = 0; i < nodeCount; i++) {
            int rna = rand.nextInt(nodeCount);
            int rnb = rand.nextInt(nodeCount);
            Node tmp = nodes[rna];
            nodes[rna] = nodes[rnb];
            nodes[rnb] = tmp;
        }
        nodeList = new ArrayList<Node>(Arrays.asList(nodes));
    }

    public void generateComplete() {
        if (directed) {
            for (int i = 0; i < nodeCount; i++) {
                for (int j = 0; j < nodeCount; j++) {
                    if (nodes[i].getId() != nodes[j].getId()) {
                        edgeList.add(new Edge(nodes[i], nodes[j]));
                    }
                }
            }
        } else {
            for (int i = 0; i < nodeCount; i++) {
                for (int j = i + 1; j < nodeCount; j++) {
                    edgeList.add(new Edge(nodes[i], nodes[j]));
                }
            }
        }
    }

    public void generateSimple(){
        int maxDensity = 3*(int)Math.sqrt(nodeCount); //maximal number of edges from one node
        int noEdge = 0;
        Edge e;
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
            if(rand.nextInt(2) == 0) e = new Edge(a, b);
            else e = new Edge(b, a);
            //multiplicity check
            if(!edgeList.contains(e) && a != b){
                edgeList.add(e);
                connectedPart.add(a);
                //add random number of edges coming from a
                noEdge = rand.nextInt(maxDensity);
                for(int i = 0; i < noEdge; i++){
                    b = nodes[rand.nextInt(nodeCount)];
                    if(rand.nextInt(2) == 0) e = new Edge(a, b);
                    else e = new Edge(b, a);
                    if(!edgeList.contains(e) && a != b)edgeList.add(e);
                    connectedPart.add(b);
                }    
            }
            else notUsed.add(a);
        }
        //multiplicity check for undirected graph
        if(!directed){
            ArrayList<Edge> multEdges = new ArrayList<Edge>();
            for(Edge ed: edgeList){
                if(edgeList.contains(new Edge(ed.getNodeTo(), ed.getNodeFrom())))multEdges.add(ed);
            }
            for(Edge ed: multEdges){
                edgeList.remove(ed);
            }
        }
    }


    public void generateBipartite() {
        int edgeCount;
        Node a;
        Edge e;
        boolean[] usedNodes = new boolean[nodeCount];
        ArrayList<Node> part1 = new ArrayList(Arrays.asList(nodes));
        ArrayList<Node> part2 = new ArrayList<Node>();
        int part2size = bipartiteNodeCountP2;
        //choose nodes for part2
        for (int i = 0; i < part2size; i++) {
            part2.add(part1.remove(rand.nextInt(part1.size())));
        }
        //generate edges coming from part1 nodes
        for (Node n : part1) {
            edgeCount = rand.nextInt(nodeCount - 1) + 1;
            //erase used flags
            for (int i = 0; i < nodeCount; i++) {
                usedNodes[i] = false;
            }
            for (int i = 0; i < edgeCount; i++) {
                a = part2.get(rand.nextInt(part2.size()));
                e = new Edge(n, a);
                if (!usedNodes[(int) a.getId()]) {
                    edgeList.add(e);
                }
                usedNodes[(int) a.getId()] = true;
            }
        }
        //generate edges coming from part2 nodes
        for (Node n : part2) {
            edgeCount = rand.nextInt(bipartiteNodeCountP2 - 1) + 1;
            //erase used flags
            for (int i = 0; i < nodeCount; i++) {
                usedNodes[i] = false;
            }
            for (int i = 0; i < edgeCount; i++) {
                a = part1.get(rand.nextInt(part1.size()));
                e = new Edge(n, a);
                if (!usedNodes[(int) a.getId()]) {
                    edgeList.add(e);
                }
                usedNodes[(int) a.getId()] = true;
            }
        }
        //multiplicity check for undirected graph
        if(!directed){
            ArrayList<Edge> multEdges = new ArrayList<Edge>();
            for(Edge ed: edgeList){
                if(edgeList.contains(new Edge(ed.getNodeTo(), ed.getNodeFrom())))multEdges.add(ed);
            }
            for(Edge ed: multEdges){
                edgeList.remove(ed);
            }
        }
    }
    

    public void generateCyclic(){
        int cycleSize;
        int cycleCount;
        int index;
        Node startNode, tmp1, tmp2;
        Edge e;
        int minCycleCount = (int) Math.sqrt(nodeCount)/2 + 1;
        cycleCount = rand.nextInt(2*(int) Math.sqrt(nodeCount) - minCycleCount) + minCycleCount;
        ArrayList[] cycles = new ArrayList[cycleCount];
        ArrayList<Node> notUsedNodes = new ArrayList<Node>(nodeList);
        
        //cycles generation
        for(int i = 0; i < cycleCount; i++){
            cycles[i] = new ArrayList<Node>();
            cycleSize = rand.nextInt(nodeCount - (int) Math.sqrt(nodeCount)) + (int) Math.sqrt(nodeCount);
            startNode = nodes[rand.nextInt(nodeCount)];
            tmp1 = nodes[rand.nextInt(nodeCount)];
            //we need two different nodes
            while(tmp1 == startNode) tmp1 = nodes[rand.nextInt(nodeCount)];
            //first edge of cycle
            e = new Edge(startNode, tmp1);
            edgeList.add(e);
            cycles[i].add(startNode);
            cycles[i].add(tmp1);
            notUsedNodes.remove(startNode);
            notUsedNodes.remove(tmp1);
            //rest edges od cycle
            for(int j = 0; j < cycleSize; j++){
                tmp2 = nodes[rand.nextInt(nodeCount)];
                e = new Edge(tmp1, tmp2);
                edgeList.add(e);
                cycles[i].add(tmp2);
                notUsedNodes.remove(tmp2);
                tmp1 = tmp2;
            }
            //close the cycle
            e = new Edge(tmp1, startNode);
            edgeList.add(e);
        }
        //assurment of graph connection
        for(int i = 0; i < cycleCount - 1; i = i+2){
            tmp1 = (Node) cycles[i].get(rand.nextInt(cycles[i].size()));
            tmp2 = (Node) cycles[i+1].get(rand.nextInt(cycles[i+1].size()));
            e = new Edge(tmp1, tmp2);
            if(!edgeList.contains(e)) edgeList.add(e);
        }
        //connect not yet used nodes
        for(Node n: notUsedNodes){
            index = rand.nextInt(cycleCount);
            tmp1 = (Node) cycles[index].get(rand.nextInt(cycles[index].size()));
            if(rand.nextInt(2) == 0)edgeList.add(new Edge(n, tmp1));
            else edgeList.add(new Edge(tmp1, n));
        }        
    }

    public void generateAcyclic(){
        Edge e;

        ArrayList<Node> notUsed = new ArrayList<Node>(Arrays.asList(nodes));
        ArrayList<Node> connectedPart = new ArrayList<Node>();
        HashMap<Node, ArrayList<Node>> cycleDetect = new HashMap<Node, ArrayList<Node>>(); //list of predecessors for every used node
        
        Node a = notUsed.remove(rand.nextInt(notUsed.size()));
        Node b = notUsed.remove(rand.nextInt(notUsed.size()));
        edgeList.add(new Edge(a, b));
        connectedPart.add(a);
        connectedPart.add(b);
        cycleDetect.put(a, new ArrayList<Node>());
        cycleDetect.put(b, new ArrayList<Node>());
        cycleDetect.get(b).add(a);
        
        //generating spanning tree
        while(!notUsed.isEmpty()){
            a = notUsed.remove(rand.nextInt(notUsed.size()));
            cycleDetect.put(a, new ArrayList<Node>());
            //connect a to rest of yet connected graph
            b = connectedPart.get(rand.nextInt(connectedPart.size()));
            //switching orientation
            if(rand.nextInt(2) == 0){
                e = new Edge(a, b);
            }
            else{
                e = new Edge(b, a);
            }
            //multiplicity check
            if(!edgeList.contains(e) && a != b){
                edgeList.add(e);
                cycleDetect.get(e.getNodeTo()).add(e.getNodeFrom());
                cycleDetect.get(e.getNodeTo()).addAll(cycleDetect.get(e.getNodeFrom()));
                connectedPart.add(a);
            }
            else notUsed.add(a);
        }
        
        //filling more edges for directed graph
//        if(directed){
//            int outEdgesCount;
//            int maxDensity = 3*(int)Math.sqrt(nodeCount); //maximal number of edges from one node
//            int fillingConstant = rand.nextInt(nodeCount);
//            for(int i = 0; i < fillingConstant; i++){
//                a = nodes[rand.nextInt(nodeCount)];
//                outEdgesCount = rand.nextInt(maxDensity);
//                for(int j = 0; j < outEdgesCount; j++){
//                    b = nodes[rand.nextInt(nodeCount)];
//                    //check cycle originating
//                    if(!cycleDetect.get(a).contains(b)){
//                        edgeList.add(new Edge(a, b));
//                        cycleDetect.get(b).addAll(cycleDetect.get(a));
//                    }
//                }
//            }
//            //break originated cycles
//        }
        
    }

    /*
     * Generating random Tree
     */

    public void generateTree() {
        Random r = new Random();
        int cnt = 1;

        Map<Integer, Boolean> used = new HashMap<Integer, Boolean>();
        for (int i = 0; i < nodeCount; i++) {
            used.put(i, false);
        }
        Node n1 = new Node(r.nextInt(nodeCount), null);
        Node n2 = new Node(r.nextInt(nodeCount), null);
        Edge e = new Edge(n1, n2);
        edgeList.add(e);
        used.put((int) n1.getId(), true);
        used.put((int) n2.getId(), true);

        while (cnt < nodeCount - 1) {
            n1 = new Node(r.nextInt(nodeCount), null);
            n2 = new Node(r.nextInt(nodeCount), null);
            if (!used.get((int) n2.getId()) && used.get((int) n1.getId())) {
                e = new Edge(n1, n2);
                edgeList.add(e);
                used.put((int) n1.getId(), true);
                used.put((int) n2.getId(), true);
                cnt++;
            }
        }
    }

    

    private void prepareOutputGraph() {
        if (weighted) {
            for (Edge e : edgeList) {
                e.setWeight(rand.nextDouble() * (maxWeight - minWeight) + minWeight);
            }
        }
        g.setEdges(edgeList);
        g.setNodes(nodeList);
        g.setDirected(directed);
        g.setWeighted(weighted);
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

    public List<GraphType> getProperties() {
        return this.properties;
    }

    @Override
    public void setBipartiteNodesCount(int part1, int part2) {
        bipartiteNodeCountP1 = part1;
        bipartiteNodeCountP2 = part2;
    }
}
