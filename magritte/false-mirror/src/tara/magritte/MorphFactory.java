package tara.magritte;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MorphFactory {

    private static Map<String, Class<? extends Morph>> morphMap = new HashMap<>();
    private static Set<String> abstractTypes = new LinkedHashSet<>();
    static{
        morphMap.put("Root", Root.class);
    }

    public static Morph newInstance(String type, Node node) {
        if(morphMap.containsKey(type))
            try {
                return morphMap.get(type).getDeclaredConstructor(Node.class).newInstance(node);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        throw new RuntimeException("type not found: " + type);
    }

    public static boolean isAbstract(String type){
        return abstractTypes.contains(type);
    }

    public static void register(String type, String aClass) {
        try {
            morphMap.put(type, (Class<? extends Morph>) Class.forName(aClass));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void registerAbstract(String type) {
        abstractTypes.add(type);
    }
}