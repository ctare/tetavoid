public class Protagonist extends PRect{
    private float ySpeed = 5, xSpeed = 0, yAcceleration = 0.2f, xAcceleration = 0.2f;

    public Protagonist(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    enum MoveType{ RIGHT, LEFT }

    public void move(MoveType moveType){
        switch (moveType){
            case RIGHT:
                xSpeed = 5;
                break;
            case LEFT:
                xSpeed = -5;
                break;
            default: break;
        }
    }

    public void jump(){
        ySpeed = -5;
    }

    @Override
    public void update() {
        Main.p.rect(super.x, super.y, super.width, super.height);
        y += ySpeed;
        x += xSpeed;
        ySpeed += yAcceleration;
        if(xSpeed < 0) xSpeed += xAcceleration;
        else if(xSpeed > 0) xSpeed -= xAcceleration;
        if(-0.5 < xSpeed && xSpeed < 0.5) xSpeed = 0;
    }
}
