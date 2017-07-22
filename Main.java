import processing.core.PApplet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends PApplet {
    public static Main p;

    public void settings() {
        size(500, 500);
    }

    Gene gene;
    Maze maze;
    QLearning mazeQ;
    final int SIZE = 20;
    public void setup() {
        gene = new Gene(Tree.treeSize(), 100, 0.1);

        int G = 1;
        int[][] mazeData = new int[][]{
                {1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1},
                {1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 1, 0, 1, G, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1},
                {1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
        };

        maze = new Maze(mazeData);
        mazeQ = new QLearning(maze);
    }
    ArrayList<Tree> trees;

    double avgMax;
    int maxMax;
    public void draw() {
        background(0);

//        === Q学習 ===
        fill(100, 255, 100, 100);
        for (Node[] row : maze.nodes) {
            for (Node node : row) {
                if(node instanceof GoalNode) {
                    fill(255, 10, 10, 20 + (float)node.Qvalue*2);
                }else if(node instanceof StartNode){
                    fill(10, 10, 255, 20 + (float)node.Qvalue*2);
                }else if(node instanceof WallNode){
                    fill(255, 150, 100, 20 + (float)node.Qvalue*2);
                }else{
                    fill(100, 255, 100, 20 + (float)node.Qvalue*2);
                }
                rect(node.x * SIZE, node.y * SIZE, SIZE, SIZE);
            }
        }

        if(!footprint.isEmpty()){
            Node node = footprint.remove(0);
            fill(255, 255, 70, 100);
            rect(node.x * SIZE, node.y * SIZE, SIZE, SIZE);
        }
        fill(255, 0, 0, 70);
        rect((mouseX / SIZE) * SIZE, (mouseY / SIZE) * SIZE, SIZE, SIZE);

        mazeQ.nextTry();

//        === 遺伝的アルゴリズム ===
//        for (int i = 0; i < 10; i++);
//        gene.nextGeneration();
//        trees = Tree.getTrees(width/2, height, gene.gene[0]);
//
//        for(Tree tree : trees){
//            stroke(255);
//            strokeWeight(tree.fat);
//            line(tree.fromX, tree.fromY, tree.toX, tree.toY);
//
//            if(tree.isLeaf){
//                noStroke();
//                fill(100, 255, 100, 70);
//                rect(tree.toX - Tree.Area.FAT, tree.toY - Tree.Area.FAT, Tree.Area.FAT, Tree.Area.FAT);
//            }
//        }
//
//        fill(255);
//        double avg = gene.avg();
//        int max = gene.max();
//        avgMax = Math.max(avgMax, avg);
//        maxMax = Math.max(maxMax, max);
//        text(String.format("%d, avg: %f, max %d", gene.t, avg, max), 20, 20);
//        text(String.format("    avg: %f, max %d", avgMax, maxMax), 20, 40);
    }

    ArrayList<Node> footprint = new ArrayList<>();
    @Override
    public void keyPressed() {
        switch (key){
            case 'r':
                setup();
                break;
            case 't':
                try{
                    Node target = maze.nodes[mouseY / SIZE][mouseX / SIZE];
                    if(!(target instanceof WallNode)){
                        footprint = mazeQ.walk(target.n);
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println(e);
                }
                break;
            default: break;
        }
    }

    public static void main(String args[]) {
        PApplet.main("Main");
    }
}
