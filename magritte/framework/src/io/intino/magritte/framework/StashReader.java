package io.intino.magritte.framework;

import io.intino.magritte.io.Stash;
import io.intino.magritte.io.Variable;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

class StashReader {

	private static List<String> proteoTypes = new ArrayList<>(asList(
			"Concept",
			"MetaConcept",
			"Aspect",
			"Facet",
			"MetaAspect"));
	private final Graph graph;

	StashReader(Graph graph) {
		this.graph = graph;
	}

	void read(Stash stash) {
		loadConcepts(stash.concepts);
		loadNodes(graph.model, stash.nodes);
	}

	private void loadConcepts(List<io.intino.magritte.io.Concept> rawConcepts) {
		for (io.intino.magritte.io.Concept rawConcept : rawConcepts) {
			graph.layerFactory.register(rawConcept.name, rawConcept.className);
			loadConcept(graph.concept$(rawConcept.name), rawConcept);
		}
	}

	@SuppressWarnings("Convert2MethodRef")
	private void loadConcept(Concept concept, io.intino.magritte.io.Concept rawConcept) {
		concept.parent(graph.concept$(rawConcept.parent));
		List<Concept> concepts = typesWithoutConcept(rawConcept);
		concept.metatype = !concepts.isEmpty() ? concepts.get(0) : null;
		concept.concepts(metaTypesOf(concepts));
		concept.isAbstract = rawConcept.isAbstract;
		concept.isMetaConcept = rawConcept.isMetaConcept;
		concept.isAspect = rawConcept.isAspect;
		concept.isMain = rawConcept.isMain;
		concept.layerClass = graph.layerFactory.layerClass(concept.id);
		concept.contentRules = rawConcept.contentRules.stream().map(c -> new Concept.Content(graph.concept$(c.type), c.min, c.max)).collect(toSet());
		concept.nodes = loadVirtualNodes(rawConcept.nodes);
		concept.parameters = rawConcept.parameters.stream().collect(toMap(v -> v.name, v -> v.values, (oldK, newK) -> newK, LinkedHashMap::new));
		concept.variables = rawConcept.variables.stream().collect(toMap(v -> v.name, v -> v.values, (oldK, newK) -> newK, LinkedHashMap::new));
	}

	private List<Concept> typesWithoutConcept(io.intino.magritte.io.Concept taraConcept) {
		List<Concept> result = new ArrayList<>();
		for (String type : taraConcept.types)
			if (!proteoTypes.contains(type))
				result.add(graph.concept$(type));
		return result;
	}

	private List<Node> loadNodes(Node parent, List<io.intino.magritte.io.Node> rawNodes) {
		List<Node> result = new ArrayList<>();
		for (io.intino.magritte.io.Node rawNode : rawNodes) {
			Node node = graph.node$(rawNode.name);
			node.owner(parent);
			loadNode(node, rawNode);
			parent.add(node);
			result.add(node);
		}
		return result;
	}

	private Node loadNode(Node node, io.intino.magritte.io.Node rawNode) {
		List<Concept> metaTypes = metaTypesOf(conceptsOf(rawNode.layers));
		addConcepts(node, metaTypes);
		loadNodes(node, rawNode.nodes);
		cloneNodes(node);
		saveVariables(node, rawNode.variables, metaTypes);
		return node;
	}

	private List<Node> loadVirtualNodes(List<io.intino.magritte.io.Node> nodes) {
		Node root = new Model(graph, emptyMap());
		return loadNodes(root, nodes);
	}

	private void addConcepts(Node node, List<Concept> metaTypes) {
		node.addLayers(metaTypes);
		node.syncLayers();
	}

	private List<Concept> conceptsOf(List<String> layers) {
		List<Concept> result = new ArrayList<>();
		for (String layer : layers) {
			Concept concept = graph.concepts.get(layer);
			if (concept == null) throw new MagritteException("Concept " + layer + " not found");
			result.add(concept);
		}
		return result;
	}

	private void saveVariables(Node node, List<Variable> variables, List<Concept> types) {
		Map<String, List<?>> variableMap = new LinkedHashMap<>();
		types.forEach(c -> variableMap.putAll(c.variables));
		types.forEach(t -> variableMap.putAll(t.parameters));
		variables.forEach((e) -> variableMap.put(e.name, e.values));
		graph.addVariableIn(node, variableMap);
	}


	private void cloneNodes(Node node) {
		NodeCloner.clone(nodesOf(node), node, graph);
	}

	private List<Node> nodesOf(Node node) {
		List<Node> nodes = new ArrayList<>();
		for (String typeName : node.typeNames) nodes.addAll(graph.concepts.get(typeName).nodes);
		return nodes;
	}

	private List<Concept> metaTypesOf(Collection<Concept> metaConcepts) {
		List<Concept> concepts = new ArrayList<>();
		for (Concept metaConcept : metaConcepts) {
			concepts.addAll(metaTypesOf(metaConcept.concepts));
			concepts.add(metaConcept);
		}
		return concepts;
	}

}
