package io.intino.tara.magritte;

import io.intino.tara.magritte.utils.PathHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static io.intino.tara.magritte.utils.PathHelper.canonicalPath;
import static io.intino.tara.magritte.utils.PathHelper.pathWithExtension;
import static java.util.Arrays.asList;
import static java.util.logging.Logger.getGlobal;

class GraphHelper {
    static <T extends GraphView> T create(Class<T> aClass, Graph graph) {
        try {
            T instance = aClass.getConstructor(Graph.class).newInstance(graph);
            instance.update();
            return instance;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    static void saveStashes(Graph graph, String[] paths) {
        Set<String> set = new HashSet(asList(paths));
        Map<String, List<Node>> pathNodes = new HashMap<>();
        set.forEach(p -> pathNodes.put(p, new ArrayList<>()));
        for (Node node : graph.model.graph.rootList())
            if (set.contains(node.path())) pathNodes.get(node.path()).add(node);
        for (Map.Entry<String, List<Node>> entry : pathNodes.entrySet())
            StashWriter.write(graph, pathWithExtension(entry.getKey()), entry.getValue());
    }

    static void saveAll(Graph graph, String[] excludedPaths) {
        Set<String> set = new HashSet(asList(excludedPaths));
        Map<String, List<Node>> pathNodes = new HashMap<>();
        for (Node node : graph.model.graph.rootList()) {
            if (set.contains(node.path())) continue;
            if (!pathNodes.containsKey(node.path())) pathNodes.put(node.path(), new ArrayList<>());
            pathNodes.get(node.path()).add(node);
        }
        for (Map.Entry<String, List<Node>> entry : pathNodes.entrySet())
            StashWriter.write(graph, pathWithExtension(entry.getKey()), entry.getValue());
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
        graph.doLoadStashes(graph.stashOf(PathHelper.pathWithExtension(path), false));
        if (name != null && graph.nodes.containsKey(path + "#" + name)) {
            getGlobal().warning("Node with id " + path + "#" + name + " already exists");
            return null;
        }
        return concept.createNode(canonicalPath(path), name == null ? graph.createNodeName() : name, graph.model);
    }
}
