package io.intino.tara.magritte;

import io.intino.tara.magritte.loaders.ClassFinder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;
import static java.util.logging.Logger.getGlobal;

class LayerFactory {

    private final LayerMap layerMap;

    LayerFactory() {
        this.layerMap = new LayerMap();
    }

    LayerFactory(LayerFactory layerFactory) {
        this.layerMap = new LayerMap(layerFactory.layerMap);
    }

    private static Constructor<? extends Layer> getDeclaredConstructor(Class<? extends Layer> layerClass) {
        try {
            return layerClass.getDeclaredConstructor(Node.class);
        } catch (NoSuchMethodException e) {
            getGlobal().severe(e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            return null;
        }
    }

    Layer create(String name, Node node) {
        Class<? extends Layer> layerClass = layerMap.get(name);
        if (layerClass != null) return create(layerClass, node);
        getGlobal().severe("Concept " + name + " hasn't layer registered. Node " + node.id + " won't have it");
        return null;
    }

    @SuppressWarnings("ConstantConditions")
    Layer create(Class<? extends Layer> layerClass, Node node) {
        if (isAbstract(layerClass)) return null;
        try {
            Constructor<? extends Layer> constructor = layerMap.constructorOf(layerClass);
            constructor = constructor != null ? constructor : getDeclaredConstructor(layerClass);
            return constructor.newInstance(node);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            getGlobal().severe(e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return null;
    }

    private boolean isAbstract(Class<? extends Layer> layerClass) {
        return Modifier.isAbstract(layerClass.getModifiers());
    }

    List<String> names(Class<? extends Layer> layerClass) {
        final List<String> list = layerMap.get(layerClass);
        return list == null ? emptyList() : unmodifiableList(list);
    }

    void register(String name, String layerClass) {
        try {
            register(name, (Class<? extends Layer>) ClassFinder.find(layerClass));
        } catch (ClassNotFoundException e) {
            getGlobal().severe(e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
    }

    private void register(String name, Class<? extends Layer> layerClass) {
        layerMap.put(name, layerClass);
    }

    Class<? extends Layer> layerClass(String name) {
        return layerMap.get(name);
    }

    void clear() {
        layerMap.clear();
    }

    static class LayerMap {
        private final Map<String, Class<? extends Layer>> map;
        private final Map<Class<? extends Layer>, Constructor<? extends Layer>> methods;
        private final Map<Class<? extends Layer>, List<String>> names;

        LayerMap() {
            this.map = new HashMap<>();
            this.methods = new HashMap<>();
            this.names = new HashMap<>();
        }

        LayerMap(LayerMap layerMap) {
            this.map = new HashMap<>(layerMap.map);
            this.methods = new HashMap<>(layerMap.methods);
            this.names = new HashMap<>(layerMap.names);
        }

        void put(String name, Class<? extends Layer> layerClass) {
            map.put(name, layerClass);
            methods.put(layerClass, getDeclaredConstructor(layerClass));
            if (!names.containsKey(layerClass))
                names.put(layerClass, new ArrayList<>());
            names.get(layerClass).add(name);
        }

        public Class<? extends Layer> get(String name) {
            return map.get(name);
        }

        Constructor<? extends Layer> constructorOf(Class<? extends Layer> layerClass) {
            return methods.get(layerClass);
        }

        public List<String> get(Class<? extends Layer> layerClass) {
            return names.get(layerClass);
        }

        void clear() {
            map.clear();
            names.clear();
        }
    }


}