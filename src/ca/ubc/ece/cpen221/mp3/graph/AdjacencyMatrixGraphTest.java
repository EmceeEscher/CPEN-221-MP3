package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

import static org.junit.Assert.*;

import org.junit.Test;


import java.util.*;

public class AdjacencyMatrixGraphTest {

    @Test
    public void test() {
        List<Vertex> testVertices = new ArrayList<Vertex>();
        Vertex v1 = new Vertex("a");
        Vertex v2 = new Vertex("b");
        Vertex v3 = new Vertex("c");
        Vertex v4 = new Vertex("d");
        Vertex v5 = new Vertex("e");
        testVertices.add(v1);
        testVertices.add(v2);
        testVertices.add(v3);
        testVertices.add(v4);
        testVertices.add(v5);
        AdjacencyMatrixGraph testGraph = new AdjacencyMatrixGraph();
        assertTrue(testGraph.getVertices().isEmpty());
        
        testGraph.addVertex(v1);
        testGraph.addVertex(v2);
        testGraph.addVertex(v3);
        testGraph.addVertex(v4);
        testGraph.addVertex(v5);
        assertTrue(testVertices.equals(testGraph.getVertices()));
        
        AdjacencyMatrixGraph testGraph2 = new AdjacencyMatrixGraph(testVertices);
        assertTrue(testGraph2.getVertices().equals(testGraph.getVertices()));
        
        testGraph.addEdge(v1, v5);
        assertTrue(testGraph.edgeExists(v1, v5));
        assertTrue(!testGraph.edgeExists(v1, v3));
        assertTrue(!testGraph.edgeExists(v5, v1));
        assertTrue(!testGraph.edgeExists(v4, v3));
        assertTrue(!testGraph2.edgeExists(v1, v5));
        
        testGraph.addEdge(v5, v2);
        testGraph.addEdge(v1, v2);
        assertTrue(testGraph.getDownstreamNeighbors(v3).isEmpty());
        
        List<Vertex> testV1Downstream = new ArrayList<Vertex>();
        testV1Downstream.add(v2);
        testV1Downstream.add(v5);
        List<Vertex> realV1Downstream = testGraph.getDownstreamNeighbors(v1);
        assertTrue(testV1Downstream.equals(realV1Downstream));
        
        assertTrue(testGraph.getUpstreamNeighbors(v4).isEmpty());
        
        List<Vertex> testV2Upstream = new ArrayList<Vertex>();
        testV2Upstream.add(v1);
        testV2Upstream.add(v5);
        List<Vertex> realV2Upstream = testGraph.getUpstreamNeighbors(v2);
        assertTrue(testV2Upstream.equals(realV2Upstream));
        
    }

}
