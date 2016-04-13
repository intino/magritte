package tara.magritte;

import tara.io.Facet;
import tara.io.Prototype;
import tara.io.Stash;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

class StashReader {

	static List<String> proteoTypes = new ArrayList<>(asList(
			"Concept",
			"MetaConcept",
			"Facet",
			"MetaFacet",
			"FacetMetaConcept",
			"FacetConcept",
			"FacetFacet",
			"FacetMetaFacet",
			"MetaFacetMetaConcept",
			"MetaFacetConcept",
			"MetaFacetFacet",
			"MetaFacetMetaFacet"));
	private final ModelHandler model;

	public StashReader(ModelHandler model) {
		this.model = model;
	}

	public void read(Stash stash) {
		loadConcepts(stash.concepts);
		loadInstances(model.graph, stash.nodes);
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
		concept.nodes = loadVirtualInstances(rawConcept.nodes);
		concept.prototypes = rawConcept.prototypes.stream().map(p -> loadPrototype(model.graph, p)).collect(toList());
		concept.parameters = rawConcept.parameters.stream().collect(toMap(v -> v.name, v -> v.values, (oldK, newK) -> newK));
		concept.variables = rawConcept.variables.stream().collect(toMap(v -> v.name, v -> v.values, (oldK, newK) -> newK));
	}

	private Stream<String> typesWithoutConcept(tara.io.Concept taraConcept) {
		return taraConcept.types.stream().filter(t -> !proteoTypes.contains(t));
	}

	private List<Node> loadInstances(Node parent, List<tara.io.Node> rawNodes) {
		List<Node> result = new ArrayList<>();
		for (tara.io.Node rawNode : rawNodes) {
			Node node = model.$Node(rawNode.name);
			node.owner(parent);
			loadInstance(node, rawNode);
			parent.add(node);
			result.add(node);
		}
		return result;
	}

	private Node loadInstance(Node node, tara.io.Node rawNode) {
		addConcepts(node, rawNode.facets);
		loadInstances(node, rawNode.facets.stream().flatMap(f -> f.nodes.stream()).collect(toList()));
		clonePrototypes(node);
		cloneInstances(node);
		saveVariables(node, rawNode);
		return node;
	}

	private List<Node> loadVirtualInstances(List<tara.io.Node> nodes) {
		Node root = new Graph(){

			@Override
			public Model model() {
				return (Model) StashReader.this.model;
			}
		};
		return loadInstances(root, nodes);
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
				.collect(toMap(v -> v.name, v -> v.values, (oldK, newK) -> newK));
	}

	private void clonePrototypes(Node node) {
		NodeCloner.clone(prototypesOf(node), node, model);
	}

	private void cloneInstances(Node node) {
		NodeCloner.clone(instancesOf(node), node, model);
	}

	private List<Node> prototypesOf(Node node) {
		List<Node> prototypes = new ArrayList<>();
		node.conceptList().forEach(t -> t.prototypeList().forEach(prototypes::add));
		return prototypes;
	}

	private List<Node> instancesOf(Node node) {
		List<Node> nodes = new ArrayList<>();
		node.conceptList().forEach(t -> t.componentList().forEach(nodes::add));
		return nodes;
	}

	private Node loadPrototype(Node parent, tara.io.Prototype prototype) {
		Node node = createPrototype(prototype);
		node.owner(parent);
		addConcepts(node, prototype.facets);
		if (prototype.className != null) node.addLayer(model.$concept(prototype.name));
		loadVariables(prototype, node);
		addComponentPrototypes(node, prototype.facets.stream().flatMap(f -> f.nodes.stream()).collect(toList()));
		parent.add(node);
		return node;
	}

	private void loadVariables(Prototype prototype, Node node) {
		List<Concept> metatypes = metaTypesOf(prototype.facets.stream().map(f -> model.$concept(f.name))).collect(toList());
		metatypes.forEach(c -> c.variables().entrySet().forEach(v -> node.as(c)._load(v.getKey(), v.getValue())));
		metatypes.stream().filter(c -> c.metatype != null).forEach(c -> c.parameters.entrySet().forEach(p -> node.as(c.metatype)._load(p.getKey(), p.getValue())));
		prototype.facets.forEach(f -> {
			Layer layer = node.as(f.name);
			variablesOf(f).forEach(layer::_load);
		});
	}

	private Node createPrototype(Prototype prototype) {
		Node node = prototype.name == null ? new Node() : model.$Node(prototype.name);
		if (prototype.className != null) model.layerFactory.register(node.id, prototype.className);
		return node;
	}

	private void addComponentPrototypes(Node aNode, List<tara.io.Node> prototypes) {
		for (tara.io.Node prototype : prototypes) loadPrototype(aNode, (Prototype) prototype);
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
