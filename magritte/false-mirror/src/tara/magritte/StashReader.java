package tara.magritte;

import tara.io.Stash;
import tara.io.Variable;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

class StashReader {

	private static List<String> proteoTypes = new ArrayList<>(asList(
			"Concept",
			"MetaConcept",
			"Facet",
			"MetaFacet",
			"Facet#MetaConcept",
			"Facet#Concept",
			"Facet#Facet",
			"Facet#MetaFacet",
			"MetaFacet#MetaConcept",
			"MetaFacet#Concept",
			"MetaFacet#Facet",
			"MetaFacet#MetaFacet"));
	private final GraphHandler model;

	public StashReader(GraphHandler model) {
		this.model = model;
	}

	public void read(Stash stash) {
		loadConcepts(stash.concepts);
		loadNodes(model.model, stash.nodes);
	}

	private void loadConcepts(List<tara.io.Concept> rawConcepts) {
		for (tara.io.Concept rawConcept : rawConcepts) {
			model.layerFactory.register(rawConcept.name, rawConcept.className);
			loadConcept(model.$concept(rawConcept.name), rawConcept);
		}
	}

	@SuppressWarnings("Convert2MethodRef")
	private void loadConcept(Concept concept, tara.io.Concept rawConcept) {
		concept.parent(model.$concept(rawConcept.parent));
		List<Concept> concepts = typesWithoutConcept(rawConcept);
		concept.metatype = !concepts.isEmpty() ? concepts.get(0) : null;
		concept.concepts(metaTypesOf(concepts));
		concept.isAbstract = rawConcept.isAbstract;
		concept.isMetaConcept = rawConcept.isMetaConcept;
		concept.isMain = rawConcept.isMain;
		concept.layerClass = model.layerFactory.layerClass(concept.id);
		concept.contentRules = rawConcept.contentRules.stream().map(c -> new Concept.Content(model.$concept(c.type), c.min, c.max)).collect(toSet());
		concept.nodes = loadVirtualNodes(rawConcept.nodes);
		concept.parameters = rawConcept.parameters.stream().collect(toMap(v -> v.name, v -> v.values, (oldK, newK) -> newK, LinkedHashMap::new));
		concept.variables = rawConcept.variables.stream().collect(toMap(v -> v.name, v -> v.values, (oldK, newK) -> newK, LinkedHashMap::new));
	}

	private List<Concept> typesWithoutConcept(tara.io.Concept taraConcept) {
		List<Concept> result = new ArrayList<>();
		for (String type : taraConcept.types)
			if (!proteoTypes.contains(type))
				result.add(model.$concept(type));
		return result;
	}

	private List<Node> loadNodes(Node parent, List<tara.io.Node> rawNodes) {
		List<Node> result = new ArrayList<>();
		for (tara.io.Node rawNode : rawNodes) {
			Node node = model.$Node(rawNode.name);
			node.owner(parent);
			loadNode(node, rawNode);
			parent.add(node);
			result.add(node);
		}
		return result;
	}

	private Node loadNode(Node node, tara.io.Node rawNode) {
		List<Concept> metaTypes = metaTypesOf(conceptsOf(rawNode.facets));
		addConcepts(node, metaTypes);
		loadNodes(node, rawNode.nodes);
		cloneNodes(node);
		saveVariables(node, rawNode.variables, metaTypes);
		return node;
	}

	private List<Node> loadVirtualNodes(List<tara.io.Node> nodes) {
		Node root = new Model() {

			@Override
			public Graph graph() {
				return (Graph) StashReader.this.model;
			}
		};
		return loadNodes(root, nodes);
	}

	private void addConcepts(Node node, List<Concept> metaTypes) {
		node.addLayers(metaTypes);
		node.syncLayers();
	}

	private List<Concept> conceptsOf(List<String> facets) {
		List<Concept> result = new ArrayList<>();
		for (String facet : facets) result.add(model.concepts.get(facet));
		return result;
	}

	private void saveVariables(Node node, List<Variable> variables, List<Concept> types) {
		Map<String, List<?>> variableMap = new LinkedHashMap<>();
		types.forEach(c -> variableMap.putAll(c.variables));
		types.forEach(t -> variableMap.putAll(t.parameters));
		variables.forEach((e) -> variableMap.put(e.name, e.values));
		model.addVariableIn(node, variableMap);
	}


	private void cloneNodes(Node node) {
		NodeCloner.clone(nodesOf(node), node, model);
	}

	private List<Node> nodesOf(Node node) {
		List<Node> nodes = new ArrayList<>();
		for (String typeName : node.typeNames) nodes.addAll(model.concepts.get(typeName).nodes);
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
