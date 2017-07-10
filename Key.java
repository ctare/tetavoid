import java.util.HashMap;

/**
 * Created by ctare on 2017/07/10.
 */
public class Key {
    private static HashMap<Character, Boolean> keyPressed = new HashMap<>();

    public static void press(char key){
        keyPressed.put(key, true);
    }

    public static void release(char key){
        keyPressed.put(key, false);
    }

    public static boolean isPressed(char key){
        return keyPressed.getOrDefault(key, false);
    }
}
