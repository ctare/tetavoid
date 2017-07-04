public class Utils {
    public static boolean collisionRectX(PRect r1, PRect r2) {
        if (r1.x <= r2.x + r2.width || r1.x + r1.width >= r2.x) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean collisionRectY(PRect r1, PRect r2) {
        if (r1.y + r1.height >= r2.y) {
            return true;
        } else {
            return false;
        }
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
