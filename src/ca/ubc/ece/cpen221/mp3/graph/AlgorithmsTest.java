package ca.ubc.ece.cpen221.mp3.graph;

import static org.junit.Assert.*;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;
import java.util.*;

import org.junit.Test;

public class AlgorithmsTest {

    /*
     * This test covers a tree with branches that have multiple sub-branches,
     * which themselves all have sub-branches. It also contains a loop (2 is 
     * downstream from 1, which is downstream from 2).
     */
    @Test
    public void breadthSearchTest() {
        List<Vertex> testVertices = new ArrayList<Vertex>();
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        testVertices.add(v1);
        testVertices.add(v2);
        testVertices.add(v3);
        testVertices.add(v4);
        testVertices.add(v5);
        Graph testGraph = new AdjacencyMatrixGraph(testVertices);
        
        testGraph.addEdge(v1, v2);
        testGraph.addEdge(v1, v3);
        testGraph.addEdge(v2, v1);
        testGraph.addEdge(v2, v4);
        testGraph.addEdge(v3, v5);

        Set<List<Vertex>> expected = new HashSet<List<Vertex>>();
        
        List<Vertex> v1Path = new ArrayList<Vertex>();
        v1Path.add(v1);
        v1Path.add(v2);
        v1Path.add(v3);
        v1Path.add(v4);
        v1Path.add(v5);
        
        List<Vertex> v2Path = new ArrayList<Vertex>();
        v2Path.add(v2);
        v2Path.add(v1);
        v2Path.add(v4);
        v2Path.add(v3);
        v2Path.add(v5);
        
        List<Vertex> v3Path = new ArrayList<Vertex>();
        v3Path.add(v3);
        v3Path.add(v5);
        
        List<Vertex> v4Path = new ArrayList<Vertex>();
        v4Path.add(v4);
        
        List<Vertex> v5Path = new ArrayList<Vertex>();
        v5Path.add(v5);
        
        expected.add(v1Path);
        expected.add(v2Path);
        expected.add(v3Path);
        expected.add(v4Path);
        expected.add(v5Path);
        
        assertTrue(Algorithms.breadthSearch(testGraph).equals(expected));
    }
    
    @Test
    public void emptyBreadthSearchTest(){
        Graph test = new AdjacencyMatrixGraph();
        Set<List<Vertex>> expected = new HashSet<List<Vertex>>();
        assertTrue(Algorithms.breadthSearch(test).equals(expected));
    }
    
    //WRITE MORE TESTS
    
    /*
     * the "ugly tree" in this test includes a vertex that points to
     * itself, as well as circles of vertexes, and a vertex that is not 
     * connected to any others
     
    @Test
    public void uglyTreeBreadthSearchTest(){
        List<Vertex> testVertices = new ArrayList<Vertex>();
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");
        Vertex v8 = new Vertex("8");
        testVertices.add(v1);
        testVertices.add(v2);
        testVertices.add(v3);
        testVertices.add(v4);
        testVertices.add(v5);
        testVertices.add(v6);
        testVertices.add(v7);
        testVertices.add(v8);
        Graph testGraph = new AdjacencyMatrixGraph(testVertices);
        
        testGraph.addEdge(v1, v2);
        testGraph.addEdge(v1, v3);
        testGraph.addEdge(v1, v4);
        testGraph.addEdge(v2, v4);
        testGraph.addEdge(v2, v2);
        testGraph.addEdge(v2, v5);
        testGraph.addEdge(v3, v6);
        testGraph.addEdge(v4, v6);
        testGraph.addEdge(v5, v7);
        
        assertTrue(Algorithms.breadthSearch(testGraph, v7));
        assertTrue(Algorithms.breadthSearch(testGraph, v8));
        assertTrue(!Algorithms.breadthSearch(testGraph, new Vertex("9")));
    }
    
    @Test
    public void emptyBreadthSearchTest(){
        Graph test = new AdjacencyMatrixGraph();
        assertTrue(!Algorithms.breadthSearch(test, new Vertex("")));
    }*/
    
    @Test
    public void commonUpstreamDownstreamTest(){
        List<Vertex> testVertices = new ArrayList<Vertex>();
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");
        Vertex v8 = new Vertex("8");
        testVertices.add(v1);
        testVertices.add(v2);
        testVertices.add(v3);
        testVertices.add(v4);
        testVertices.add(v5);
        testVertices.add(v6);
        testVertices.add(v7);
        testVertices.add(v8);
        Graph testGraph = new AdjacencyMatrixGraph(testVertices);
        
        testGraph.addEdge(v1, v2);
        testGraph.addEdge(v1, v3);
        testGraph.addEdge(v1, v4);
        testGraph.addEdge(v2, v5);
        testGraph.addEdge(v2, v6);
        testGraph.addEdge(v3, v6);
        testGraph.addEdge(v3, v7);
        testGraph.addEdge(v4, v7);
        testGraph.addEdge(v4, v8);
        testGraph.addEdge(v4, v6);
        
        List<Vertex> upstream67Test = new ArrayList<Vertex>();
        upstream67Test.add(v3);
        upstream67Test.add(v4);
        
        List<Vertex> upstream67Result = Algorithms.commonUpstream(testGraph,
                v6, v7);
        assertTrue(upstream67Test.equals(upstream67Result));
        
        List<Vertex> noCommonUpstream = Algorithms.commonUpstream(testGraph,
                v1, v8);
        assertTrue(noCommonUpstream.isEmpty());
        
        List<Vertex> downstream34Test = new ArrayList<Vertex>();
        downstream34Test.add(v6);
        downstream34Test.add(v7);
        
        List<Vertex> downstream34Result = Algorithms.commonDownstream(testGraph,
                v3, v4);
        assertTrue(downstream34Test.equals(downstream34Result));
        
        List<Vertex> noCommonDownstream = Algorithms.commonDownstream(testGraph,
                v5, v4);
        assertTrue(noCommonDownstream.isEmpty()); 
    }

}
