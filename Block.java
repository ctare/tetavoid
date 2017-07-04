public class Block extends PRect{

    public Block(float x, float y, int width, int height) {
        super(x,y,width,height);
    }

    @Override
    public void update(){
        if(!Utils.collisionRectToBottom(this)) {
            y++;
        }
        Main.p.rect(x, y, width, height);
    }
}
