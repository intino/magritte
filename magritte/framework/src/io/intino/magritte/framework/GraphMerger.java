package io.intino.magritte.framework;

import java.util.List;
import java.util.function.BinaryOperator;

public class GraphMerger {

	public static Graph doMerge(Graph mergedGraph, List<Graph> graphs, BinaryOperator<Node> operator) {
		graphs.forEach(g -> {
			mergedGraph.loaders.addAll(g.loaders);
			mergedGraph.languages.addAll(g.languages);
			g.concepts.forEach((k, v) -> mergedGraph.concepts.putIfAbsent(k, v));
			g.nodes.forEach((k, v) -> v.forEach((k2, v2) -> {
				String id = k + "#" + k2;
				Node node = v2;
				if (mergedGraph.node(id) != null) node = operator.apply(mergedGraph.node(id), v2);
				mergedGraph.register(node);
			}));
			if (mergedGraph.layerFactory == null) mergedGraph.layerFactory = g.layerFactory;
			mergedGraph.openedStashes.addAll(g.openedStashes);
		});
		mergedGraph.nodes.forEach((k, v) -> v.forEach((k2, v2) -> {
			if (v2.owner() instanceof Model) v2.owner(mergedGraph.model);
			mergedGraph.model.add(v2);
		}));
		return mergedGraph;
	}

}
