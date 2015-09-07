package tara.magritte;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.logging.Logger;

public class MorphFactory {
    private static final Logger LOG = Logger.getLogger(MorphFactory.class.getName());

    private static MorphMap morphMap = new MorphMap();

    public static Morph create(String name, Declaration declaration) {
        return create(morphMap.get(name), declaration);
    }

    public static Morph create(Class<? extends Morph> morphClass, Declaration declaration) {
        if (isAbstract(morphClass)) return null;
        try {
            return morphClass.getDeclaredConstructor(Declaration.class).newInstance(declaration);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LOG.severe(e.getMessage());
        }
        return null;
    }

    private static boolean isAbstract(Class<? extends Morph> morphClass) {
        return Modifier.isAbstract(morphClass.getModifiers());
    }

    public static List<String> names(Class<? extends Morph> morphClass) {
        return Collections.unmodifiableList(morphMap.get(morphClass));
    }

    public static void register(String name, String morphClass) {
        try {
            register(name, (Class<? extends Morph>) Class.forName(morphClass));
        } catch (ClassNotFoundException e) {
            LOG.severe(e.getMessage());
        }
    }

    public static void register(String name, Class<? extends Morph> morphClass) {
        morphMap.put(name, morphClass);
    }


    public static Class<? extends Morph> morphClass(String name) {
        return morphMap.get(name);
    }

    static class MorphMap {
        private Map<String, Class<? extends Morph>> map = new HashMap<>();
        private Map<Class<? extends Morph>, List<String>> names = new HashMap<>();

        public void put(String name, Class<? extends Morph> morphClass) {
            map.put(name, morphClass);
            if(!names.containsKey(morphClass))
                names.put(morphClass, new ArrayList<>());
            names.get(morphClass).add(name);
        }

        public Class<? extends Morph> get(String name) {
            return map.get(name);
        }

        public List<String> get(Class<? extends Morph> morphClass) {
            return names.get(morphClass);
        }

    }


}