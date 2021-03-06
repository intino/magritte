package io.intino.magritte.framework;

import io.intino.magritte.framework.utils.StashHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static io.intino.magritte.framework.utils.StashHelper.canonicalPath;
import static io.intino.magritte.framework.utils.StashHelper.stashWithExtension;
import static java.util.Arrays.asList;
import static java.util.logging.Logger.getGlobal;

class GraphHelper {
	static <T extends GraphWrapper> T create(Class<T> aClass, Graph graph) {
		try {
			T instance = aClass.getConstructor(Graph.class).newInstance(graph);
			instance.update();
			return instance;
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	static <T extends GraphWrapper> T create(Class<T> aClass, Graph graph, GraphWrapper wrapper) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		return aClass.getConstructor(Graph.class, aClass).newInstance(graph, wrapper);
	}

	static void saveStashes(Graph graph, String[] paths) {
		Set<String> set = new HashSet(asList(paths));
		Map<String, List<Node>> pathNodes = new HashMap<>();
		set.forEach(p -> pathNodes.put(p, new ArrayList<>()));
		for (Node node : graph.model.graph.rootList()) {
			if (node == null) continue;
			if (set.contains(node.stash())) pathNodes.get(node.stash()).add(node);
		}
		save(graph, pathNodes);
	}

	static void saveAll(Graph graph, String[] excludedPaths) {
		Set<String> set = new HashSet(asList(excludedPaths));
		Map<String, List<Node>> pathNodes = new HashMap<>();
		for (Node node : graph.model.graph.rootList()) {
			if (node == null) continue;
			if (set.contains(node.stash())) continue;
			if (!pathNodes.containsKey(node.stash())) pathNodes.put(node.stash(), new ArrayList<>());
			pathNodes.get(node.stash()).add(node);
		}
		save(graph, pathNodes);
	}

	private static void save(Graph graph, Map<String, List<Node>> pathNodes) {
		for (Map.Entry<String, List<Node>> entry : pathNodes.entrySet())
			StashWriter.write(graph, stashWithExtension(entry.getKey()), entry.getValue());
	}

	static Node createNode(Graph graph, Concept concept, String path, String name) {
		if (!concept.isMain()) {
			getGlobal().severe("Concept " + concept.id() + " is not main. The node could not be created.");
			return null;
		}
		if (concept.isAbstract()) {
			getGlobal().severe("Concept " + concept.id() + " is abstract. The node could not be created.");
			return null;
		}
		path = path == null || path.isEmpty() ? "Misc" : path;
		graph.doLoadStashes(graph.stashOf(StashHelper.stashWithExtension(path), false));
		if (name != null && graph.nodes.containsKey(path + "#" + name)) {
			getGlobal().warning("Node with id " + path + "#" + name + " already exists");
			return null;
		}
		return concept.createRoot(canonicalPath(path), name == null ? graph.createNodeName() : name, graph.model);
	}
}
