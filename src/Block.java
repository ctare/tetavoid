import processing.core.*;

public class Block extends PRect{

    public Block(int x, int y, int width, int height) {
        super(x,y,width,height);
    }

    public void update(){
        p.rect(x, y, width, height);
    }
}
