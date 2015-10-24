package ca.ubc.ece.cpen221.mp3.graph;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;
import java.util.*;

public class AdjacencyMatrixGraph implements Graph {
    private int numVertices;
    private boolean[][] matrix;
    private List<Vertex> vertices;
    
    /**
     * Creates a graph with no vertices or edges in it
     */
    
    public AdjacencyMatrixGraph(){
        numVertices = 0;
        matrix = new boolean[0][0];
        vertices = new ArrayList<Vertex>();
    }
    
    /**
     * Creates an AdjacencyMatrixGraph with the given vertices and no edges
     * @param inputVertices: the vertices to be put in the graph
     */
    public AdjacencyMatrixGraph(List<Vertex> inputVertices){
        vertices = new ArrayList<Vertex>();
        for(int i = 0; i < inputVertices.size(); i++){
            Vertex toAdd = new Vertex(inputVertices.get(i).getLabel());
            vertices.add(toAdd);
        }
        numVertices = vertices.size();
        matrix = new boolean[numVertices][numVertices];
    }
        
    /**
     * @param v: the vertex to be added to the graph 
     *      Precondition: v must not be already in the graph
     *      
     * Postcondition: Adds v to the end of the graph (last row & column)
     */
    
    public void addVertex(Vertex v){
        boolean[][] newMatrix = new boolean[numVertices + 1][numVertices + 1];
        for(int i = 0; i < numVertices; i++){
            for(int j = 0; j < numVertices; j++){
                newMatrix[i][j] = matrix[i][j];
            }
        }
        numVertices++;
        for(int i = 0; i < numVertices; i++){
            newMatrix[i][numVertices - 1] = false;
        }
        for(int j = 0; j < numVertices; j++){
            newMatrix[numVertices - 1][j] = false;
        }
        Vertex toAdd = new Vertex(v.getLabel());
        vertices.add(toAdd);
        matrix = newMatrix;
    }
    
    /**
     * Precondition: v1 and v2 are vertices that are already in the graph
     * 
     * Postcondition: Adds an edge from v1 to v2
     */
    
    public void addEdge(Vertex v1, Vertex v2){
        int fromIndex = vertices.indexOf(v1);
        int toIndex = vertices.indexOf(v2);
        matrix[fromIndex][toIndex] = true;
    }
    
    /**
     * Check if there is an edge from v1 to v2.
     *
     * Precondition: v1 and v2 are vertices in the graph 
     * Postcondition: return true iff an edge from v1 connects to v2
     */
    public boolean edgeExists(Vertex v1, Vertex v2){
        int fromIndex = vertices.indexOf(v1);
        int toIndex = vertices.indexOf(v2);
        return matrix[fromIndex][toIndex];
    }
    
    /**
     * Get an array containing all downstream vertices adjacent to v.
     *
     * Precondition: v is a vertex in the graph
     * 
     * Postcondition: returns a list containing each vertex w such that there is
     * an edge from v to w. The size of the list must be as small as possible
     * (No trailing null elements). This method should return a list of size 0
     * iff v has no downstream neighbors.
     */
    public List<Vertex> getDownstreamNeighbors(Vertex v){
        List<Vertex> downstream = new ArrayList<Vertex>();
        int fromIndex = vertices.indexOf(v);
        for(int i = 0; i < numVertices; i++){
            if(matrix[fromIndex][i]){
                Vertex toAdd = new Vertex(vertices.get(i).getLabel());
                downstream.add(toAdd);
            }
        }
        return downstream;
    }
    
    /**
     * Get an array containing all upstream vertices adjacent to v.
     *
     * Precondition: v is a vertex in the graph
     * 
     * Postcondition: returns a list containing each vertex u such that there is
     * an edge from u to v. The size of the list must be as small as possible
     * (No trailing null elements). This method should return a list of size 0
     * iff v has no upstream neighbors.
     */
    public List<Vertex> getUpstreamNeighbors(Vertex v){
        List<Vertex> upstream = new ArrayList<Vertex>();
        int toIndex = vertices.indexOf(v);
        for(int i = 0; i < numVertices; i++){
            if(matrix[i][toIndex]){
                Vertex toAdd = new Vertex(vertices.get(i).getLabel());
                upstream.add(toAdd);
            }
        }
        return upstream;        
    }
    
    /**
     * Get all vertices in the graph.
     *
     * Postcondition: returns a list containing all vertices in the graph. This
     * method should return a list of size 0 iff the graph has no vertices.
     */
    public List<Vertex> getVertices(){
        List<Vertex> allVertices = new ArrayList<Vertex>();
        for(int i = 0; i < numVertices; i++){
            Vertex toAdd = new Vertex(vertices.get(i).getLabel());
            allVertices.add(toAdd);
        }
        return allVertices;
    }
    
}
