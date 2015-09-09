package tara.magritte;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;

public class LayerFactory {
    private static MorphMap morphMap = new MorphMap();

    public static Layer create(String name, Declaration declaration) {
        return create(morphMap.get(name), declaration);
    }

    public static Layer create(Class<? extends Layer> layerClass, Declaration declaration) {
        if (isAbstract(layerClass)) return null;
        try {
            return layerClass.getDeclaredConstructor(Declaration.class).newInstance(declaration);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Logger.severe(e.getMessage());
        }
        return null;
    }

    private static boolean isAbstract(Class<? extends Layer> layerClass) {
        return Modifier.isAbstract(layerClass.getModifiers());
    }

    public static List<String> names(Class<? extends Layer> layerClass) {
        return Collections.unmodifiableList(morphMap.get(layerClass));
    }

    public static void register(String name, String layerClass) {
        try {
            register(name, (Class<? extends Layer>) Class.forName(layerClass));
        } catch (ClassNotFoundException e) {
            Logger.severe(e.getMessage());
        }
    }

    public static void register(String name, Class<? extends Layer> layerClass) {
        morphMap.put(name, layerClass);
    }


    public static Class<? extends Layer> layerClass(String name) {
        return morphMap.get(name);
    }

    static class MorphMap {
        private Map<String, Class<? extends Layer>> map = new HashMap<>();
        private Map<Class<? extends Layer>, List<String>> names = new HashMap<>();

        public void put(String name, Class<? extends Layer> layerClass) {
            map.put(name, layerClass);
            if(!names.containsKey(layerClass))
                names.put(layerClass, new ArrayList<>());
            names.get(layerClass).add(name);
        }

        public Class<? extends Layer> get(String name) {
            return map.get(name);
        }

        public List<String> get(Class<? extends Layer> layerClass) {
            return names.get(layerClass);
        }

    }


}