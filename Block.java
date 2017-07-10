public class Block extends PRect{

    public Block(float x, float y, int width, int height) {
        super(x,y,width,height);
    }

    @Override
    public void update(){
        if(!Utils.collisionRectToBottom(this)) {
            boolean flg = true;
            for(PRect rect: Main.p.blocks){
                if(rect != this && Utils.collisionRect(this, rect)){
                    flg = false;
                    break;
                }
            }
            if(flg) y++;
        }
        Main.p.rect(x, y, width, height);
    }
}
