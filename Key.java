import java.util.HashMap;

/**
 * Created by ctare on 2017/07/10.
 */
public class Key {
    private static HashMap<Integer, Boolean> keyPressed = new HashMap<>();

    public static void press(int key){
        keyPressed.put(key, true);
    }

    public static void release(int key){
        keyPressed.put(key, false);
    }

    public static boolean isPressed(int key){
        return keyPressed.getOrDefault(key, false);
    }
}
