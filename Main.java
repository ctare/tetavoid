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
    public void setup() {
        gene = new Gene(255, 100, 0.1);
    }
    ArrayList<Tree> trees;

    double avgMax;
    int maxMax;
    public void draw() {
        background(0);

        for (int i = 0; i < 10; i++);
        gene.nextGeneration();
        trees = Tree.getTrees(width/2, height, gene.gene[0]);

        for(Tree tree : trees){
            stroke(255);
            strokeWeight(tree.fat);
            line(tree.fromX, tree.fromY, tree.toX, tree.toY);

            if(tree.isLeaf){
                noStroke();
                fill(100, 255, 100, 70);
                rect(tree.toX - Tree.Area.FAT, tree.toY - Tree.Area.FAT, Tree.Area.FAT, Tree.Area.FAT);
            }
        }

        fill(255);
        double avg = gene.avg();
        int max = gene.max();
        avgMax = Math.max(avgMax, avg);
        maxMax = Math.max(maxMax, max);
        text(String.format("%d, avg: %f, max %d", gene.t, avg, max), 20, 20);
        text(String.format("    avg: %f, max %d", avgMax, maxMax), 20, 40);

//        for (int i = 0; i < gene.gene.length; i++) {
//            text(Arrays.toString(gene.gene[i]), 20, 60 + i * 20);
//        }
    }

    @Override
    public void keyPressed() {
        if(key == 'r'){
            gene = new Gene(255, 50, 0.0001);
        }
    }

    public static void main(String args[]) {
        PApplet.main("Main");
    }
}
