package qlearning;

import qlearning.model.Node;

import java.util.ArrayList;

/**
 * Created by ctare on 2017/07/22.
 */
public class UndirectedGraph {
    public Links[] adjacencyList;
    UndirectedGraph(int nodeN){
        adjacencyList = new Links[nodeN];
        for (int i = 0; i < nodeN; i++) {
            adjacencyList[i] = new Links();
        }
    }

    public void add(Edge edge){
        adjacencyList[edge.from.n].add(edge.to);
        adjacencyList[edge.to.n].add(edge.from);
    }

    public class Links extends ArrayList<Node>{
    }
}
