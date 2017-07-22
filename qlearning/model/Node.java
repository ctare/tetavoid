package qlearning.model;

/**
 * Created by ctare on 2017/07/22.
 */
public class Node {
    public int x, y, n;
    public double Qvalue;
    public double reward = 0;

    public Node(int x, int y, int n) {
        this.x = x;
        this.y = y;
        this.n = n;
    }
}
