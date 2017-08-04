package io.intino.tara.magritte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.logging.Logger;

class GraphCloner {

	static Graph doClone(Graph graph, Graph clone) {
		clone.loaders = new ArrayList<>(graph.loaders);
		clone.languages = new LinkedHashSet<>(graph.languages);
		clone.concepts = new HashMap<>(graph.concepts);
		clone.nodes = new HashMap<>(graph.nodes);
		clone.layerFactory = new LayerFactory(graph.layerFactory);
		clone.openedStashes = new HashSet<>(graph.openedStashes);
		graph.model.componentList().forEach(clone.model::add);
		graph.wrappers.values().forEach(w -> cloneWrapper(clone, w));
		return clone;
	}

	private static void cloneWrapper(Graph clone, GraphWrapper w) {
		Class<? extends GraphWrapper> aClass = w.getClass();
		try {
			clone.wrappers.put(aClass, GraphHelper.create(aClass, clone, w));
		} catch (Throwable e){
			Logger.getGlobal().info("Copy constructor on " + aClass.getSimpleName() + " not found. To improve performance when cloning, add constructor \"" + aClass.getSimpleName() + "(Graph graph, " + aClass.getSimpleName() + " wrapper)\" calling super (Builder 2.0.8+)");
			clone.wrappers.put(aClass, GraphHelper.create(aClass, clone));
		}
	}

}
