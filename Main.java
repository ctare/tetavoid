import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    public static PApplet p;
    public ArrayList<Block> blocks = new ArrayList<>();

    public void settings() {
        size(400, 600);
        p = this;
    }

    public void setup() {
        background(0);
        blocks.add(new Block(200,0,20,20));
    }

    public void draw() {
        background(0);
        for (Block block : blocks) {
            block.update();
//            block.x += cos(atan2(mouseY - block.y, mouseX - block.x)) * 2;
//            block.y += sin(atan2(mouseY - block.y, mouseX - block.x)) * 2;
        }
    }

    public static void main(String args[]) {
        PApplet.main("Main");
    }
}
