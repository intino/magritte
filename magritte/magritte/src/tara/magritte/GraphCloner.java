package tara.magritte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class GraphCloner {

	public static Graph doClone(Graph graph, Graph clone) {
		clone.loaders = new ArrayList<>(graph.loaders);
		clone.languages = new LinkedHashSet<>(graph.languages);
		clone.concepts = new HashMap<>(graph.concepts);
		clone.nodes = new HashMap<>(graph.nodes);
		clone.layerFactory = new LayerFactory(graph.layerFactory);
		clone.openedStashes = new HashSet<>(graph.openedStashes);
		graph.model.componentList().forEach(clone.model::add);
		if (graph.platform != null) clone.modelLoad().wrap(graph.application.getClass(), graph.platform.getClass());
		else clone.modelLoad().wrap(graph.application.getClass());
		return clone;
	}

}
