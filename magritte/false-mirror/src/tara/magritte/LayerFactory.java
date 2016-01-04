package tara.magritte;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.logging.Logger;

class LayerFactory {

    private static final Logger LOG = Logger.getLogger(LayerFactory.class.getName());

    private static final MorphMap morphMap = new MorphMap();

    public static Layer create(String name, Instance instance) {
        Class<? extends Layer> layerClass = morphMap.get(name);
        if (layerClass != null) return create(layerClass, instance);
        LOG.severe("Concept " + name + " hasn't layer registered. Instance " + instance.name + " won't have it");
        return null;
    }

    public static Layer create(Class<? extends Layer> layerClass, Instance instance) {
        if (isAbstract(layerClass)) return null;
        try {
            return layerClass.getDeclaredConstructor(Instance.class).newInstance(instance);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LOG.severe(e.getCause().getMessage());
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
            LOG.severe(e.getCause().getMessage());
        }
    }

    public static void register(String name, Class<? extends Layer> layerClass) {
        morphMap.put(name, layerClass);
    }


    public static Class<? extends Layer> layerClass(String name) {
        return morphMap.get(name);
    }

    static class MorphMap {
        private final Map<String, Class<? extends Layer>> map = new HashMap<>();
        private final Map<Class<? extends Layer>, List<String>> names = new HashMap<>();

        public void put(String name, Class<? extends Layer> layerClass) {
            map.put(name, layerClass);
            if (!names.containsKey(layerClass))
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