package Rabbit.util;

import java.lang.reflect.Field;
import java.util.HashMap;

public class KeywordManager {
    public static HashMap<String, String> keywordMap = new HashMap<>();
    public static String BRACED;
    public static String EXPOSED;
    public static String STAGGER;
    public static String GUIDE;
    public static String CLUTCH;
    public static String FERVOR;
    public static String CRITICAL;
    public static String DEATHBLOW;
    public static String CARROT;
    public static String JUMP;
    public static String TRANSIENT;
    public static String CORPOREAL;
    public static String ECHO;
    public static String COUNTER;

    public static String getKeyword(String ID) {
        return keywordMap.getOrDefault(ID, "");
    }

    public static void setupKeyword(String ID, String key) {
        keywordMap.put(ID, key);
        Field[] fields = KeywordManager.class.getDeclaredFields();
        for (Field f : fields) {
            if (f.getName().toLowerCase().equals(ID)) {
                try {
                    f.set(null, key);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
