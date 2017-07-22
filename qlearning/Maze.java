package qlearning;

import qlearning.model.*;

/**
 * Created by ctare on 2017/07/22.
 */
public class Maze extends UndirectedGraph {
    public Node[][] nodes;
    Maze(int[][] mazeData) {
        super(countNodes(mazeData));

        nodes = new Node[mazeData.length][];
        int count = 0;
        for (int i = 0; i < mazeData.length; i++) {
            nodes[i] = new Node[mazeData[i].length];
            for (int j = 0; j < mazeData[i].length; j++) {
                if (i == 5 && j == 10) {
                    nodes[i][j] = new GoalNode(j, i, count);
                    nodes[i][j].reward = 10;
                } else {
                    if (mazeData[i][j] == 1) {
                        nodes[i][j] = new Node(j, i, count);
                    } else {
                        nodes[i][j] = new WallNode(j, i, count);
                    }
                }
                count++;
            }
        }
        nodes[0][0] = new StartNode(0, 0, 0);

        for (int i = 0; i < mazeData.length; i++) {
            for (int j = 0; j < mazeData[i].length; j++) {
                if(mazeData[i][j] == 0) continue;
                if(i + 1 < mazeData.length && mazeData[i + 1][j] == 1) this.add(new Edge(nodes[i][j], nodes[i + 1][j]));
                if(j + 1 < mazeData[i].length && mazeData[i][j + 1] == 1) this.add(new Edge(nodes[i][j], nodes[i][j + 1]));
            }
        }
    }

    private static int countNodes(int[][] mazeData){
        int count = 0;
        for (int[] row : mazeData) {
            count += row.length;
        }
        return count;
    }
}
