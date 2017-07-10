public class Protagonist extends PRect{
    private float ySpeed, yAcceleration = 0.2f;

    public Protagonist(float x, float y, int width, int height) {
        super(x, y, width, height);
    }


    public void move(){

    }

    public void jump(){
        ySpeed = -5;
    }

    @Override
    public void update() {
        Main.p.rect(super.x, super.y, super.width, super.height);
        y += ySpeed;
        ySpeed += yAcceleration;
    }
}
