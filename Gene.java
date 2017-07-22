import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalInt;

/**
 * Created by ctare on 2017/07/19.
 */
public class Gene {
    private int length;
    private int popN;
    private double rate;

    public GeneCell[][] gene;
    private int[] fitness;
    public int t;

    public static final int FAT = 9;

    public Gene(int length, int popN, double rate) {
        this.length = length * FAT;
        this.popN = popN;
        this.rate = rate;
        init_gen();
    }

    public void init_gen(){
        gene = new GeneCell[popN][length];
        for(GeneCell[] g : gene){
            for (int i = 0; i < g.length; i++) {
                g[i] = new GeneCell().init();
            }
        }
        fitness = new int[popN];
        t = 0;

        calcFitness();
    }

    private void calcFitness(){
        for (int i = 0; i < gene.length; i++) {
            ArrayList<Tree> trees = Tree.getTrees(0, 0, gene[i]);

//            === area size ===
            Tree.Area area = new Tree.Area(trees);
            area.fill(trees);
//            fitness[i] = area.sum();

//            === sum high ===
            int sum = 0;
            int edgeSum = 0;
            for (Tree tree : trees) {
                if(tree.isLeaf) sum += tree.toY;
                else edgeSum += tree.toY;
            }
//            fitness[i] = -sum;

//            === sum and area ===
            fitness[i] = area.sum() * -(sum) / 1000;

//            == highest ==
//            float minv = Float.POSITIVE_INFINITY;
//            for (Tree tree : trees) {
//                minv = Math.min(minv, tree.toY);
//            }

//            === 000111 ===
//            int n = 0;
//            for (int j = 0; j < gene[i].length; j++) {
//                if(i < length / 2){
//                    if(gene[i][j] == 0) n++;
//                }else{
//                    if(gene[i][j] == 1) n++;
//                }
//            }
//            fitness[i] = n;
        }
    }

    public static int calcValue(GeneCell[] gene, int position){
        int p5 = position * FAT;
        int angle = 0;
        for (int i = p5; i < p5 + FAT; i++) {
            angle = (angle << 1) + gene[i].val();
        }
        return angle;
    }

    // p1 -> p2
    private void copyGene(int p1, int p2){
        if(p1 == p2) return;
        gene[p2] = new GeneCell[gene[p1].length];
        for (int i = 0; i < gene[p1].length; i++) {
            gene[p2][i] = gene[p1][i].clone();
        }
        fitness[p2] = fitness[p1];
    }

    // p2 -> p1
    private void swapGene(int p1, int p2){
        GeneCell[] tmpG = gene[p1];
        gene[p1] = gene[p2];
        gene[p2] = tmpG;

        int tmpF = fitness[p1];
        fitness[p1] = fitness[p2];
        fitness[p2] = tmpF;
    }

    private void elite(){
        int maxP = Integer.MIN_VALUE;
        int maxV = Integer.MIN_VALUE;
        for (int i = 0; i < fitness.length; i++) {
            if(maxV < fitness[i]){
                maxV = fitness[i];
                maxP = i;
            }
        }
        int minP = Integer.MAX_VALUE;
        int minV = Integer.MAX_VALUE;
        for (int i = 0; i < fitness.length; i++) {
            if(minV > fitness[i]){
                minV = fitness[i];
                minP = i;
            }
        }

        copyGene(maxP, minP);
        swapGene(0, maxP);
    }

    private void reproduction(){
        int sumOfFitness = Arrays.stream(fitness).sum();
        GeneCell[][] newGene = new GeneCell[popN][length];
        for (int p = 1; p < popN; p++) {
            double r = sumOfFitness * Math.random();
            long border = fitness[0];
            int num = 0;
            while(border < r){
                border += fitness[++num];
            }

            newGene[p] = new GeneCell[gene[p].length];
            for (int i = 0; i < gene[p].length; i++) {
                newGene[p][i] = gene[p][i].clone();
            }
        }

        for (int i = 1; i < popN; i++) {
            gene[i] = newGene[i];
        }
    }

    private void crossover(){
        int cPos = FAT * (int)(Math.random() * (length/FAT));
        for (int i = 1; i < popN - 1; i += 2) {
            for (int j = cPos; j < length; j++) {
                GeneCell tmp = gene[i][j];
                gene[i][j] = gene[i + 1][j];
                gene[i + 1][j] = tmp;
            }
        }
    }

    private void mutation(){
        for (int i = 1, geneLength = gene.length; i < geneLength; i++) {
            GeneCell[] g = gene[i];
            for (GeneCell cell : g) {
                if (Math.random() < (i < popN / 2 ? 0.0001 : rate)) {
                    cell.mutation();
                }
            }
        }
    }

    public double avg(){
        return Arrays.stream(fitness).sum() / popN;
    }

    public int max(){
        int max = Integer.MIN_VALUE;
        for(int i : fitness) max = Math.max(max, i);
        return max;
    }

    public void showGene(){
        double avg = avg();
        int max = max();
        System.out.println("--- " + t + " ---");
        System.out.println("avg : " + avg);
        System.out.println("max : " + max);
        ArrayList<Integer> angles = new ArrayList<>();
        for (GeneCell[] geneCells : gene) {
            for (int i = 0; i < geneCells.length/FAT; i++) {
                angles.add(Gene.calcValue(geneCells, i));
            }
            System.out.println(angles);
            angles = new ArrayList<>();
        }
        System.out.println();
    }

    public void nextGeneration(){
        t++;
        elite();
        reproduction();
        crossover();
        mutation();
        calcFitness();
//        showGene();
    }
}
