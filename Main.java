import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    public static Main p;
    public ArrayList<Block> blocks = new ArrayList<>();
    private Protagonist me = new Protagonist(200, 300, 25, 25);

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
        }

        me.update();

        if(Key.isPressed('j')) me.move(Protagonist.MoveType.RIGHT);
        if(Key.isPressed('f')) me.move(Protagonist.MoveType.LEFT);
        if(Key.isPressed('k')) me.jump();
    }

    public static void main(String args[]) {
        PApplet.main("Main");
    }

    @Override
    public void keyPressed() {
        Key.press(key);
    }

    @Override
    public void keyReleased() {
        Key.release(key);
    }

    @Override
    public void mousePressed() {
        blocks.add(new Block(mouseX, mouseY, 20, 20));
    }
}
