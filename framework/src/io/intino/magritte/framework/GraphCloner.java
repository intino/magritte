package io.intino.magritte.framework;

import java.util.*;
import java.util.logging.Logger;

class GraphCloner {

	static Graph doClone(Graph graph, Graph clone) {
		clone.loaders = new ArrayList<>(graph.loaders);
		clone.languages = new LinkedHashSet<>(graph.languages);
		clone.concepts = new HashMap<>(graph.concepts);
		clone.nodes = cloneHashMap(graph);
		clone.layerFactory = new LayerFactory(graph.layerFactory);
		clone.openedStashes = new HashSet<>(graph.openedStashes);
		graph.model.componentList().forEach(clone.model::add);
		graph.wrappers.values().forEach(w -> cloneWrapper(clone, w));
		return clone;
	}

	private static HashMap<String, Map<String, Node>> cloneHashMap(Graph graph) {
		HashMap<String, Map<String, Node>> newNodes = new HashMap<>();
		for (Map.Entry<String, Map<String, Node>> entry : new HashMap<>(graph.nodes).entrySet())
			newNodes.put(entry.getKey(), new LinkedHashMap<>(entry.getValue()));
		return newNodes;
	}

	private static void cloneWrapper(Graph clone, GraphWrapper w) {
		Class<? extends GraphWrapper> aClass = w.getClass();
		try {
			clone.wrappers.put(aClass, GraphHelper.create(aClass, clone, w));
		} catch (Throwable e) {
			Logger.getGlobal().info("Copy constructor on " + aClass.getSimpleName() + " not found. To improve performance when cloning, add constructor \"" + aClass.getSimpleName() + "(Graph graph, " + aClass.getSimpleName() + " wrapper)\" calling super (Builder 2.0.8+)");
			clone.wrappers.put(aClass, GraphHelper.create(aClass, clone));
		}
	}

}
