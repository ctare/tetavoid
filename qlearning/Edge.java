package qlearning;

import qlearning.model.Node;

/**
 * Created by ctare on 2017/07/22.
 */
public class Edge {
    public Node from, to;
    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
    }
}
