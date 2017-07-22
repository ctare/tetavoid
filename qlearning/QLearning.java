package qlearning;

import qlearning.model.GoalNode;
import qlearning.model.Node;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ctare on 2017/07/22.
 */
public class QLearning {
    private int START = 0;

    private UndirectedGraph data;
    private int t = 0;

    private final int MAX_T = 100;
    private double Alpha = 0.1;
    private double Gamma = 0.9;
    public QLearning(UndirectedGraph data){
        this.data = data;
    }

    private Node selectAction(int state){
        int T = Math.max(1, MAX_T - t);

        double[] exp = new double[data.adjacencyList[state].size()];
        for (int i = 0; i < exp.length; i++) {
            exp[i] = Math.exp(data.adjacencyList[state].get(i).Qvalue / T);
        }

        double r = Math.random() * Arrays.stream(exp).sum();
        double border = 0;
        for (int i = 0; i < exp.length; i++) {
            border += exp[i];

            if(r <= border) return data.adjacencyList[state].get(i);
        }
        throw new RuntimeException("手が選ばれませんでした");
    }

    private void updateQ(int pState, int state, Node action, double r){
        double maxQ = data.adjacencyList[state].stream().mapToDouble(n -> n.Qvalue).max().orElse(0);
        action.Qvalue += Alpha * (r + Gamma * maxQ - action.Qvalue);
    }

    public void nextTry(){
        int state = START;
        Node nextAction = null;
        while(!(nextAction instanceof GoalNode)){
            nextAction = selectAction(state);
            int pState = state;
            state = nextAction.n;
            updateQ(pState, state, nextAction, nextAction.reward);
        }
        t++;
    }

    public ArrayList<Node> walk(int start){
        int state = start;
        Node nextAction = null;
        ArrayList<Node> footprint = new ArrayList<>();
        while(!(nextAction instanceof GoalNode)){
            nextAction = selectAction(state);
            footprint.add(nextAction);
            state = nextAction.n;
        }
        return footprint;
    }
}
