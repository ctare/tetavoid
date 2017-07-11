public class Protagonist extends PRect{
    private float ySpeed = 5, xSpeed = 0, yAcceleration = 0.3f, xAcceleration = 0.2f;
    private float tx, ty;

    public Protagonist(float x, float y, int width, int height) {
        super(x, y, width, height);
        tx = x;
        ty = y;
    }

    enum MoveType{ RIGHT, LEFT }

    public void move(MoveType moveType){
        switch (moveType){
            case RIGHT:
//                xSpeed = 5;
                x += 5;
                collisionToBlocks();
                break;
            case LEFT:
//                xSpeed = -5;
                x -= 5;
                collisionToBlocks();
                break;
            default: break;
        }
    }

    public void jump(){
        if(ySpeed == 0) ySpeed = -5;
    }

    private void collisionToBlocks(){
        for(Block block: Main.p.blocks){
            if(Utils.collisionRect(this, block)){
                switch (Utils.hitDirection(this, block)){
                    case RIGHT:
                        block.color = Main.p.color(255, 0, 0);
                        x = block.x + block.width + 1;
                        xSpeed *= -0.3;
                        break;
                    case LEFT:
                        block.color = Main.p.color(155, 255, 0);
                        x = block.x - width - 1;
                        xSpeed *= -0.3;
                        break;
                    case TOP:
                        block.color = Main.p.color(155, 25, 255);
                        ySpeed = 0;
                        y = block.y - height;
                        break;
                    case BOTTOM:
                        block.color = Main.p.color(25, 255, 255);
                        ySpeed *= -1;
                        y = block.y + block.height + 1;
                        break;
                    default:
                        block.color = Main.p.color(100);
                        break;
                }
            }
        }
    }

    @Override
    public void update() {
        Main.p.rect(super.x, super.y, super.width, super.height);
        y += ySpeed;
        ySpeed += yAcceleration;
        if(Utils.collisionRectToBottom(this)){
            y = Main.p.height - height;
            ySpeed = 0;
        }
        x += xSpeed;
        collisionToBlocks();
        if(xSpeed < 0) xSpeed += xAcceleration;
        else if(xSpeed > 0) xSpeed -= xAcceleration;
        if(-0.5 < xSpeed && xSpeed < 0.5) xSpeed = 0;
        if(Utils.collisionRectToLeft(this)){
            x = 0;
            xSpeed *= -1;
        }
        if(Utils.collisionRectToRight(this)){
            x = Main.p.width - width;
            xSpeed *= -1;
        }

        tx = x;
        ty = y;
    }

    public Block xPrevious(){
        return new Block(tx, y, width, height);
//        return new Block(x - xSpeed, y, width, height);
    }

    public Block yPrevious(){
        return new Block(x, ty, width, height);
//        return new Block(x, y - ySpeed, width, height);
    }
}
