package tara.magritte;

import tara.io.Facet;
import tara.io.Stash;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		concept.metatype = typesWithoutConcept(rawConcept).map(s -> model.$concept(s)).findFirst().orElse(null);
		concept.concepts(metaTypesOf(typesWithoutConcept(rawConcept).map(name -> model.$concept(name))).collect(toList()));
		concept.isAbstract = rawConcept.isAbstract;
		concept.isMetaConcept = rawConcept.isMetaConcept;
		concept.isMain = rawConcept.isMain;
		concept.layerClass = model.layerFactory.layerClass(concept.id);
		concept.contentRules = rawConcept.contentRules.stream().map(c -> new Concept.Content(model.$concept(c.type), c.min, c.max)).collect(toSet());
		concept.nodes = loadVirtualNodes(rawConcept.nodes);
		concept.parameters = rawConcept.parameters.stream().collect(toMap(v -> v.name, v -> v.values, (oldK, newK) -> newK, LinkedHashMap::new));
		concept.variables = rawConcept.variables.stream().collect(toMap(v -> v.name, v -> v.values, (oldK, newK) -> newK, LinkedHashMap::new));
	}

	private Stream<String> typesWithoutConcept(tara.io.Concept taraConcept) {
		return taraConcept.types.stream().filter(t -> !proteoTypes.contains(t));
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
		addConcepts(node, rawNode.facets);
		loadNodes(node, rawNode.facets.stream().flatMap(f -> f.nodes.stream()).collect(toList()));
		cloneNodes(node);
		saveVariables(node, rawNode);
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

	private void addConcepts(Node node, List<Facet> facets) {
		node.addLayers(metaTypesOf(facets.stream().map(f -> model.$concept(f.name))).collect(toList()));
		node.syncLayers();
	}

	private void saveVariables(Node node, tara.io.Node taraNode) {
		List<Concept> metatypes = metaTypesOf(taraNode.facets.stream().map(f -> model.$concept(f.name))).collect(toList());
		metatypes.forEach(c -> model.addVariableIn(node.as(c), c.variables()));
		metatypes.stream().filter(c -> c.metatype != null).forEach(c -> model.addVariableIn(node.as(c.metatype), c.parameters));
		taraNode.facets.forEach(f -> model.addVariableIn(node.as(f.name), variablesOf(f)));
	}

	private Map<String, List<?>> variablesOf(Facet facet) {
		return facet.variables.stream()
				.filter(v -> v != null)
				.collect(toMap(v -> v.name, v -> v.values, (oldK, newK) -> newK, LinkedHashMap::new));
	}


	private void cloneNodes(Node node) {
		NodeCloner.clone(nodesOf(node), node, model);
	}

	private List<Node> nodesOf(Node node) {
		List<Node> nodes = new ArrayList<>();
		node.conceptList().forEach(t -> t.componentList().forEach(nodes::add));
		return nodes;
	}

	private Stream<Concept> metaTypesOf(Stream<Concept> metaConcepts) {
		List<Concept> concepts = new ArrayList<>();
		metaConcepts.forEach(d -> {
			concepts.addAll(metaTypesOf(d.conceptList().stream()).collect(toList()));
			concepts.add(d);
		});
		return concepts.stream();
	}

}
