package io.intino.magritte.framework;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.logging.Logger;

public class GraphMerger {

	public static Graph doMerge(Graph mergedGraph, List<Graph> graphs, BinaryOperator<Node> operator) {
		graphs.forEach(g -> {
			mergedGraph.loaders.addAll(g.loaders);
			mergedGraph.languages.addAll(g.languages);
			g.concepts.forEach((k, v) -> mergedGraph.concepts.putIfAbsent(k, v));
			g.nodes.forEach((k, v) -> {
				Node node = v;
				if(mergedGraph.nodes.containsKey(k)) node = operator.apply(mergedGraph.nodes.get(k), v);
				mergedGraph.nodes.put(k, node);
			});
			if(mergedGraph.layerFactory == null) mergedGraph.layerFactory = g.layerFactory;
			mergedGraph.openedStashes.addAll(g.openedStashes);
		});
		mergedGraph.nodes.forEach((k, v) -> {
			if(v.owner() instanceof Model) v.owner(mergedGraph.model);
			mergedGraph.model.add(v);
		});
		return mergedGraph;
	}

}
