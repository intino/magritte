package io.intino.tara.magritte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static io.intino.tara.magritte.GraphHandler.create;

class GraphCloner {

	static Graph doClone(Graph graph, Graph clone) {
		clone.loaders = new ArrayList<>(graph.loaders);
		clone.languages = new LinkedHashSet<>(graph.languages);
		clone.concepts = new HashMap<>(graph.concepts);
		clone.nodes = new HashMap<>(graph.nodes);
		clone.layerFactory = new LayerFactory(graph.layerFactory);
		clone.openedStashes = new HashSet<>(graph.openedStashes);
		graph.model.componentList().forEach(clone.model::add);
		if(graph.platform != null) clone.platform = create(graph.platform.getClass(), clone);
		if(graph.application != null) clone.application = create(graph.application.getClass(), clone);
		return clone;
	}

}
