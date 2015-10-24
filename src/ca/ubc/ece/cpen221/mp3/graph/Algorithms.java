package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

import java.util.*;

public class Algorithms {

	/**
	 * *********************** Algorithms ****************************
	 * 
	 * Please see the README for the machine problem for a more detailed
	 * specification of the behavior of each method that one should implement.
	 */
    
    /**
     * Performs a breadth-first traversal of g
     * 
     * @param g: the graph to be traversed
     * 
     * @return a Set of Lists that represent the paths taken by the traversal if
     *      started from every different vertex in the graph
     */
    public static Set<List<Vertex>> breadthSearch(Graph g){
        Set<List<Vertex>> pathSet = new HashSet<List<Vertex>>();
        List<Vertex> vertices = g.getVertices();
        for(int i = 0; i < vertices.size(); i++){
            Vertex current = vertices.get(i);
            List<Vertex> path = new ArrayList<Vertex>();
            List<Vertex> toTraverse = new ArrayList<Vertex>();
            toTraverse.add(current);
            while(toTraverse.size() > 0){
                current = toTraverse.get(0);
                path.add(current);
                List<Vertex> downstream = g.getDownstreamNeighbors(current);
                for(int j = 0; j < downstream.size(); j++){
                    if(!path.contains(downstream.get(j)) && 
                            !toTraverse.contains(downstream.get(j)))
                        toTraverse.add(downstream.get(j));
                }
                toTraverse.remove(0);
            }
            pathSet.add(path);
        }        
        return pathSet;
    }
    
    
    /**
     * Returns a list of all vertices u in Graph g that are upstream to both
     * v1 and v2 (upstream means there is are edges from u to v1 and v2)
     * 
     * @param g: the graph to be analyzed
     * @param v1, v2: the two vertices to find common upstream neighbors of
     *      Precondition: v1, v2 must be present in g
     * 
     * @return a list containing all vertices upstream to both
     * v1 and v2. Returns an empty list if no such vertices exist
     * 
     */
    public static List<Vertex> commonUpstream(Graph g, Vertex v1, Vertex v2){
        List<Vertex> v1Upstream = g.getUpstreamNeighbors(v1);
        List<Vertex> v2Upstream = g.getUpstreamNeighbors(v2);
        v1Upstream.retainAll(v2Upstream);
        
        return v1Upstream;
    }
    
    /**
     * Returns a list of all vertices u in Graph g that are downstream to both
     * v1 and v2 (downstream means there are edges from v1 and v2 to u)
     * 
     * @param g: the graph to be analyzed
     * @param v1, v2: the two vertices to find common downstream neighbors of
     *      Precondition: v1, v2 must be present in g
     * 
     * @return a list containing all vertices downstream to both
     * v1 and v2. Returns an empty list if no such vertices exist
     * 
     */
    public static List<Vertex> commonDownstream(Graph g, Vertex v1, Vertex v2){
        List<Vertex> v1Downstream = g.getDownstreamNeighbors(v1);
        List<Vertex> v2Downstream = g.getDownstreamNeighbors(v2);
        v1Downstream.retainAll(v2Downstream);
        
        return v1Downstream;
    }
    
	/**
	 * This is provided as an example to indicate that this method and other
	 * methods should be implemented here.
	 * 
	 * You should write the specs for this and all other methods.
	 * 
	 * @param graph
	 * @param a
	 * @param b
	 * @return
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
		// TODO: Implement this method and others
		return 0;
	}

}
