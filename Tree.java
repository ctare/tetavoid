import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by ctare on 2017/07/20.
 */
public class Tree {
    float fromX, fromY, toX, toY, angle, fat, length;
    int depth;
    boolean isLeaf = false;

    public Tree(float fromX, float fromY, float angle, float fat, float length, int depth) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = fromX + length * (float)Math.cos(Math.toRadians(angle));
        this.toY = fromY + length * (float)Math.sin(Math.toRadians(angle));
        this.angle = angle;
        this.fat = fat;
        this.length = length;
        this.depth = depth;
    }

    public static void tree(ArrayList<Tree> trees, float startX, float startY, GeneCell[] gene){
        ArrayDeque<Tree> deque = new ArrayDeque<>();
        deque.add(new Tree(startX, startY, -90, 5, 60, 1));
        int dp = 1;
        while(!deque.isEmpty()){
            Tree target = deque.removeFirst();
            trees.add(target);
            if(target.length > 5){
                float rAngle = target.angle - (Gene.calcValue(gene, target.depth * 2 - 1));
                float lAngle = target.angle + (Gene.calcValue(gene, target.depth * 2));
                float nextFat = target.fat * 0.8f;
                float nextLength = target.length * 0.7f;
                Tree rNext = new Tree(target.toX, target.toY, rAngle, nextFat, nextLength, target.depth * 2);
                Tree lNext = new Tree(target.toX, target.toY, lAngle, nextFat, nextLength, target.depth * 2 + 1);
                deque.add(rNext);
                deque.add(lNext);
            }else{
                target.isLeaf = true;
            }
        }
    }

    public static ArrayList<Tree> getTrees(float x, float y, GeneCell[] gene){
        ArrayList<Tree> trees = new ArrayList<>();
        tree(trees, x, y, gene);
        return trees;
    }

    public static class Area{
        public static final int FAT = 10;

        private boolean[][] area;
        private int diffX, diffY;

        Area(ArrayList<Tree> trees){
            int startX = Integer.MAX_VALUE;
            int startY = Integer.MAX_VALUE;
            int endX = Integer.MIN_VALUE;
            int endY = Integer.MIN_VALUE;
            for (Tree tree : trees) {
                if(!tree.isLeaf) continue;
                startX = Math.min(startX, (int)tree.toX);
                startY = Math.min(startY, (int)tree.toY);
                endX = Math.max(endX, (int)tree.toX);
                endY = Math.max(endY, (int)tree.toY);
            }
            startX -= FAT;
            startY -= FAT;
            endX += FAT;
            endY += FAT;
            area = new boolean[endY - startY][endX - startX];
            diffX = startX;
            diffY = startY;
        }

        public void fill(ArrayList<Tree> trees){
            for(Tree t: trees){
                if(!t.isLeaf) continue;
                for(int y = (int)t.toY - FAT; y < (int)t.toY + FAT; y++){
                    for(int x = (int)t.toX - FAT; x < (int)t.toX + FAT; x++){
                        area[y - diffY][x - diffX] = true;
                    }
                }
            }
        }

        public int sum(){
            int result = 0;
            for (boolean[] row : area) {
                for (boolean cell : row) {
                    result += cell ? 1 : 0;
                }
            }
            return result;
        }
    }
}
