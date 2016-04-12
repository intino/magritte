package tara.magritte;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.logging.Logger;

class LayerFactory {

    private static final Logger LOG = Logger.getLogger(LayerFactory.class.getName());

    private final MorphMap morphMap;

	LayerFactory(){
		this.morphMap = new MorphMap();
	}

	LayerFactory(LayerFactory layerFactory){
		this.morphMap = new MorphMap(layerFactory.morphMap);
	}

    public Layer create(String name, Node node) {
        Class<? extends Layer> layerClass = morphMap.get(name);
        if (layerClass != null) return create(layerClass, node);
		LOG.severe("Concept " + name + " hasn't layer registered. Node " + node.id + " won't have it");
		return null;
    }

    public Layer create(Class<? extends Layer> layerClass, Node node) {
        if (isAbstract(layerClass)) return null;
        try {
            return layerClass.getDeclaredConstructor(Node.class).newInstance(node);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LOG.severe(e.getCause().getMessage());
        }
        return null;
    }

    private boolean isAbstract(Class<? extends Layer> layerClass) {
        return Modifier.isAbstract(layerClass.getModifiers());
    }

    public List<String> names(Class<? extends Layer> layerClass) {
        return Collections.unmodifiableList(morphMap.get(layerClass));
    }

    public void register(String name, String layerClass) {
        try {
            register(name, (Class<? extends Layer>) Class.forName(layerClass));
        } catch (ClassNotFoundException e) {
            LOG.severe(e.getCause().getMessage());
        }
    }

    public void register(String name, Class<? extends Layer> layerClass) {
        morphMap.put(name, layerClass);
    }


    public Class<? extends Layer> layerClass(String name) {
        return morphMap.get(name);
    }

	public void clear() {
		morphMap.clear();
	}

	static class MorphMap {
        private final Map<String, Class<? extends Layer>> map;
        private final Map<Class<? extends Layer>, List<String>> names;

		MorphMap() {
			this.map = new HashMap<>();
			this.names = new HashMap<>();
		}

		MorphMap(MorphMap morphMap) {
			this.map = new HashMap<>(morphMap.map);
			this.names = new HashMap<>(morphMap.names);
		}

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

		public void clear() {
			map.clear();
			names.clear();
		}
	}


}