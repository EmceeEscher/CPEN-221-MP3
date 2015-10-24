package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.staff.*;
import java.io.*;

public class TwitterAnalysis {
    public static void main(String[] args){

        Graph twitterData = createGraph();
        
        FileInputStream queryStream;
        FileOutputStream outputStream;
        try{
            queryStream = new FileInputStream(args[0]);
            outputStream = new FileOutputStream(args[1]);
        }catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }

        try{
            BufferedReader queryReader = new BufferedReader(new 
                    InputStreamReader(queryStream));
            BufferedWriter outputWriter = new BufferedWriter(new
                    OutputStreamWriter(outputStream));
            String line;
            while((line = queryReader.readLine()) != null){
                String[] columns = line.split("\\s+");
                if((columns.length == 4) && columns[3].equals("?")){
                    outputWriter.write("query: " + columns[0] + " " + columns[1]
                            + " " + columns[2]);
                    outputWriter.newLine();
                    outputWriter.write("<result>");
                    outputWriter.newLine();
                    //TODO: write code for finding/printing query results
                    outputWriter.write("<\\result>");
                    outputWriter.newLine();
                    
                }
            }
            queryReader.close();
            queryStream.close();
            outputWriter.close();
            outputStream.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    private static Graph createGraph(){
        FileInputStream twitterStream;
        Graph twitterData = new AdjacencyMatrixGraph();
        try{
            twitterStream = new FileInputStream("datasets/twitterShort.txt");
        }catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }
        try{
            BufferedReader twitterReader = new BufferedReader(new 
                    InputStreamReader(twitterStream));
            String line;
            while((line = twitterReader.readLine()) != null){
                String[] columns = line.split(" -> ");
                Vertex follower = new Vertex(columns[0]);
                Vertex following = new Vertex(columns[1]);
                if(!twitterData.getVertices().contains(follower)){
                    twitterData.addVertex(follower);
                }if(!twitterData.getVertices().contains(following)){
                    twitterData.addVertex(following);
                }
                twitterData.addEdge(follower, following);
            }
            twitterReader.close();
            twitterStream.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return twitterData;
    }
}
