import processing.core.PApplet;

public class Utils {
    public static boolean collisionRectX(PRect r1, PRect r2) {
        if (PApplet.abs((r1.x + r1.width/2) - (r2.x + r2.width/2)) <= r1.width/2 + r2.width/2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean collisionRectY(PRect r1, PRect r2) {
        if (PApplet.abs((r1.y + r1.height/2) - (r2.y + r2.height/2)) <= r1.height/2 + r2.height/2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean collisionRect(PRect r1, PRect r2){
        return collisionRectX(r1, r2) && collisionRectY(r1, r2);
    }

    public static boolean collisionRectToWall(PRect r) {
        if (r.x <= 0 || r.x + r.width >= Main.p.width) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean collisionRectToBottom(PRect r) {
        if (r.y + r.height >= Main.p.height) {
            return true;
        } else {
            return false;
        }
    }

}
