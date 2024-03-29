package io.intino.magritte.framework;

import java.util.*;

import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;
import static java.util.logging.Logger.getGlobal;
import static java.util.stream.Collectors.toList;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Concept extends Predicate {

	final Set<Concept> concepts = new LinkedHashSet<>();
	private final Set<Concept> children = new LinkedHashSet<>();
	private final Set<Concept> instances = new LinkedHashSet<>();
	boolean isAbstract;
	boolean isMetaConcept;
	public boolean isAspect;
	boolean isMain;
	Class<? extends Layer> layerClass;
	Concept metatype = null;
	Set<Content> contentRules = new LinkedHashSet<>();
	List<Node> nodes = new ArrayList<>();
	Map<String, List<?>> variables = new LinkedHashMap<>();
	Map<String, List<?>> parameters = new LinkedHashMap<>();
	private Concept parent;

	public Concept(String name) {
		super(name);
	}

	static List<Concept> metaTypesOf(Collection<Concept> metaConcepts) {
		List<Concept> concepts = new ArrayList<>();
		for (Concept metaConcept : metaConcepts) {
			concepts.addAll(metaTypesOf(metaConcept.concepts));
			concepts.add(metaConcept);
		}
		return concepts;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public boolean isMetaConcept() {
		return isMetaConcept;
	}

	@SuppressWarnings("unused")
	public boolean isMain() {
		return isMain;
	}

	public Class<? extends Layer> layerClass() {
		return layerClass;
	}

	public List<Concept> conceptList() {
		return unmodifiableList(new ArrayList<>(concepts));
	}

	public Concept parent() {
		return parent;
	}

	void parent(Concept parent) {
		if (parent == null) return;
		this.parent = parent;
		putType(parent);
		parent.children.add(this);
	}

	@SuppressWarnings("unused")
	public List<Concept> children() {
		return unmodifiableList(new ArrayList<>(children));
	}

	void concepts(List<Concept> concepts) {
		concepts.forEach(this::putType);
	}

	public List<Node> nodes() {
		return nodes;
	}

	@Override
	protected void putType(Concept concept) {
		if (is(concept.id())) return;
		super.putType(concept);
		concepts.add(concept);
		concept.instances.add(this);
	}

	public List<Concept> allInstances() {
		Set<Concept> instances = new LinkedHashSet<>();
		instances.addAll(this.instances);
		this.instances.forEach(s -> instances.addAll(s.allInstances()));
		return new ArrayList<>(instances);
	}

	public List<Concept> allChildren() {
		Set<Concept> children = new LinkedHashSet<>();
		children.addAll(this.children);
		this.children.forEach(s -> children.addAll(s.allChildren()));
		return new ArrayList<>(children);
	}

	public List<Concept> multipleAllowed() {
		return unmodifiableList(contentRules.stream().filter(c -> c.max > 1).map(c -> c.concept).collect(toList()));
	}

	public List<Concept> singleAllowed() {
		return unmodifiableList(contentRules.stream().filter(c -> c.max == 1).map(c -> c.concept).collect(toList()));
	}

	public List<Concept> multipleRequired() {
		return unmodifiableList(contentRules.stream().filter(c -> c.min == 1 && c.max > 1).map(c -> c.concept).collect(toList()));
	}

	public List<Concept> singleRequired() {
		return unmodifiableList(contentRules.stream().filter(c -> c.min == 1 && c.max == 1).map(c -> c.concept).collect(toList()));
	}

	@Override
	public Map<String, List<?>> variables() {
		return Collections.unmodifiableMap(variables);
	}

	@Override
	public <T extends Layer> List<T> findNode(Class<T> aClass) {
		// TODO
		return null;
	}

	public Map<String, List<?>> parameters() {
		return Collections.unmodifiableMap(parameters);
	}

	@Override
	public List<Node> componentList() {
		return unmodifiableList(nodes);
	}

	Node createRoot(String path, String name, Model model) {
		if (isMetaConcept) {
			getGlobal().severe("Node cannot be created. Concept " + this.id + " is a MetaConcept");
			return null;
		}
		return newNode(path + "#" + (name != null ? name : model.graph().createNodeName()), model);
	}

	private Node component(Node owner, String name) {
		return owner.graph().load(owner.id() + "$" + name, false);
	}

	public Node createNode(Node owner) {
		return createNode(owner.graph().createNodeName(), owner);
	}

	@SuppressWarnings("ConstantConditions")
	public Node createNode(String name, Node owner) {
		if (isMetaConcept) {
			getGlobal().severe("Node cannot be created. Concept " + this.id + " is a MetaConcept");
			return null;
		}
		if (owner != null && !(owner instanceof Model) && component(owner, name) != null) {
			getGlobal().severe("Owner " + owner.name() + " contains a component named " + name + ". Node " + component(owner, name).toString());
			return null;
		}
		if (name != null && (name.contains(".") || name.contains("$"))) {
			getGlobal().severe("Name " + name + " is invalid. $ or . cannot be used");
			return null;
		}
		return newNode(owner.id() + "$" + (name != null ? name : owner.graph().createNodeName()), owner);
	}

	private Node newNode(String name, Node owner) {
		Node node = owner.graph().node$(name);
		node.owner(owner);
		prepareNode(node, owner.graph());
		if (!owner.is("Model")) owner.add(node);
		return node;
	}

	void prepareNode(Node node, Graph graph) {
		List<Concept> metaTypes = metaTypesOf(singletonList(this));
		addConcepts(node, metaTypes);
		cloneNodes(node, graph);
		fillVariables(node, metaTypes);
	}

	private void addConcepts(Node node, List<Concept> metaTypes) {
		node.addLayers(metaTypes);
		node.syncLayers();
	}

	private void fillVariables(Node node, List<Concept> types) {
		types.forEach(c -> c.variables.forEach(node::load));
		types.forEach(c -> c.parameters.forEach(node::load));
	}

	private void cloneNodes(Node node, Graph graph) {
		NodeCloner.clone(nodesOf(node, graph), node, graph);
	}

	private List<Node> nodesOf(Node node, Graph graph) {
		List<Node> nodes = new ArrayList<>();
		for (String typeName : node.typeNames) nodes.addAll(graph.concepts.get(typeName).nodes);
		return nodes;
	}

	@Override
	public String toString() {
		return id + "{names=" + concepts.stream().map(m -> m.id).collect(toList()) + '}';
	}

	public boolean is(String concept) {
		return id.equals(concept) || typeNames.contains(concept);
	}

	public boolean isAspect() {
		return isAspect;
	}

	static class Content {

		Concept concept;
		int min, max;

		Content(Concept concept, int min, int max) {
			this.concept = concept;
			this.min = min;
			this.max = max;
		}
	}
}
