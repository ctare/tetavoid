public class Block extends PRect{
    public boolean fixed = false;

    public Block(float x, float y, int width, int height) {
        super(x,y,width,height);
    }

    int color = 255;

    @Override
    public void update(){
        if(!Utils.collisionRectToBottom(this)) {
            boolean flg = true;
            for(Block rect: Main.p.blocks){
                if(rect != this && rect.fixed && Utils.collisionRect(this, rect)){
                    flg = false;
                    this.fixed = true;
                    break;
                }
            }
            if(flg) y++;
        }else{
            this.fixed = true;
        }
        Main.p.fill(color);
        Main.p.rect(x, y, width, height);
    }
}
