public class Block extends PRect{

    public Block(int x, int y, int width, int height) {
        super(x,y,width,height);
    }

    @Override
    public void update(){
        Main.p.rect(x, y, width, height);
    }
}
