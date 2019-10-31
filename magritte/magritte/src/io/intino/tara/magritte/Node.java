package io.intino.tara.magritte;

import io.intino.tara.magritte.utils.StashHelper;

import java.util.*;

import static io.intino.tara.magritte.Concept.metaTypesOf;
import static java.util.Collections.singletonList;
import static java.util.logging.Logger.getGlobal;
import static java.util.stream.Collectors.toList;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Node extends Predicate {

	final List<Layer> layers = new ArrayList<>();
	private Node owner;

	public Node() {
		this("");
	}

	public Node(String id) {
		super(id);
	}

	private static <T> List<T> reverseListOf(List<T> list) {
		List<T> result = new ArrayList<>(list);
		Collections.reverse(result);
		return result;
	}

	public Node owner() {
		return owner;
	}

	public <T extends Layer> T ownerAs(Class<T> layerClass) {
		if (owner == null) return null;
		if (owner.is(layerClass)) return owner.as(layerClass);
		return owner.ownerAs(layerClass);
	}

	public Node clone(Node parent) {
		NodeCloner.clone(singletonList(this), parent, graph());
		return graph().node(parent.id() + "." + this.name());
	}

	public Node clone(String stash) {
		NodeCloner.clone(singletonList(this), model(), stash, graph());
		return graph().node(stash + "#" + this.name());
	}

	@Override
	public List<Concept> conceptList() {
		List<Concept> result = new ArrayList<>();
		Graph graph = graph();
		for (String typeName : typeNames) result.add(graph.concept(typeName));
		Collections.reverse(result);
		return result;
	}

	public List<String> layerList() {
		List<String> aspectList = new ArrayList<>(typeNames);
		Collections.reverse(aspectList);
		return aspectList;
	}

	public Model model() {
		Node node = this;
		while (node.owner != null)
			node = node.owner;
		return (Model) node;
	}

	public Graph graph() {
		return model().graph();
	}

	public String stash() {
		return StashHelper.pathOf(id);
	}

	public Node root() {
		return graph().load(rootNodeId());
	}

	public void createNode(String name, Concept concept) {
		add(concept.createNode(name, this));
	}

	public void add(Node node) {
		for (Layer layer : layers) layer.addNode$(node);
	}

	@Override
	public List<Node> componentList() {
		Set<Node> nodes = new LinkedHashSet<>();
		reverseListOf(layers).forEach(l -> nodes.addAll(l.componentList$()));
		return new ArrayList<>(nodes);
	}

	@SuppressWarnings("unused")
	public <T extends Layer> List<T> componentList(Class<T> layerClass) {
		List<String> types = graph().layerFactory.names(layerClass);
		return componentList().stream()
				.filter(c -> c.isAnyOf(types))
				.map(c -> c.as(layerClass))
				.collect(toList());
	}

	@Override
	public <T extends Layer> List<T> findNode(Class<T> layerClass) {
		List<T> tList = new ArrayList<>();
		if (is(layerClass))
			tList.add(as(layerClass));
		componentList().forEach(c -> tList.addAll(c.findNode(layerClass)));
		return tList;
	}

	@SuppressWarnings("unchecked")
	public <T extends Layer> T as(Class<T> layerClass) {
		for (Layer layer : layers)
			if (layerClass.isAssignableFrom(layer.getClass())) return (T) layer;
		return null;
	}

	public Layer as(String conceptName) {
		return as(graph().layerFactory.layerClass(conceptName));
	}

	public void load(Layer layer, String name, List<?> values) {
		if (layer.core$() == this)
			layer.load$(name, values);
		else
			getGlobal().severe("Layer does not belong to node " + name);
	}

	public void set(Layer layer, String name, List<?> values) {
		if (layer.core$() == this)
			layer.set$(name, values);
		else
			getGlobal().severe("Layer does not belong to node " + name);
	}

	@Override
	public Map<String, List<?>> variables() {
		Map<String, List<?>> variables = new HashMap<>();
		layers.forEach(m -> variables.putAll(m.variables$()));
		return variables;
	}

	public void save() {
		graph().save(this);
	}

	public void delete() {
		graph().remove(this);
	}

	public boolean is(String concept) {
		return typeNames.contains(concept);
	}

	public boolean is(Concept concept) {
		return typeNames.contains(concept.id());
	}

	public boolean is(Class<? extends Layer> layer) {
		return isAnyOf(concepts(layer));
	}

	public Layer as(Concept concept) {
		return as(concept.id);
	}

	private void createLayer(Concept concept) {
		Layer layer = graph().layerFactory.create(concept.id, this);
		if (layer != null) this.layers.add(0, layer);
	}

	private void createLayer(Class<? extends Layer> layerClass) {
		Layer layer = graph().layerFactory.create(layerClass, this);
		if (layer != null) this.layers.add(0, layer);
	}

	private void removeParentLayer(Concept concept) {
		if (concept.parent() == null || concept.parent().isAbstract()) return;
		Layer toRemove = null;
		for (Layer layer : layers) {
			if (layer.getClass() != concept.parent().layerClass) continue;
			toRemove = layer;
			break;
		}
		if (toRemove != null) layers.remove(toRemove);
	}

	protected void remove(Node node) {
		layers.forEach(l -> l.removeNode$(node));
	}

	public <T extends Layer> T addAspect(Class<T> layerClass) {
		return (T) addAspect(graph().concept(layerClass));
	}

	public Layer addAspect(String concept) {
		return addAspect(graph().concept(concept));
	}

	public Layer addAspect(Concept concept) {
		model().remove(this);
		concept.prepareNode(this, this.graph());
		model().add(this);
		return as(concept);
	}

	void addLayers(List<Concept> concepts) {
		concepts.forEach(this::addLayer);
	}

	void addLayer(Class<? extends Layer> layerClass) {
		createLayer(layerClass);
	}

	Node addLayer(Concept concept) {
		if (is(concept.id)) return this;
		putType(concept);
		createLayer(concept);
		removeParentLayer(concept);
		return this;
	}

	void removeAspects(List<Concept> concepts) {
		concepts.forEach(this::removeAspect);
	}

	public void removeAspect(Class<? extends Layer> layerClass) {
		removeAspect(graph().layerFactory.names(layerClass).get(0));
	}

	public void removeAspect(String concept) {
		removeAspect(graph().concept(concept));
	}

	public void removeAspect(Concept concept) {
		if (!is(concept.id()) || !concept.isAspect()) return;
		model().remove(this);
		Set<Concept> toRemove = new HashSet<>(metaTypesOf(singletonList(concept)));
		toRemove.addAll(concept.allChildren());
		toRemove.addAll(concept.allInstances());
		toRemove.forEach(c -> typeNames.remove(c.id()));
		List<Concept> list = conceptList();
		typeNames.clear();
		list.forEach(c -> metaTypesOf(singletonList(c)).forEach(c1 -> typeNames.add(c1.id())));
		toRemove.stream().filter(r -> !typeNames.contains(r.id())).forEach(r -> layers.remove(as(r.layerClass)));
		model().add(this);
	}

	boolean isAnyOf(List<String> concepts) {
		return concepts.stream().anyMatch(this::is);
	}

	void owner(Node owner) {
		this.owner = owner;
	}

	void load(String varName, List<?> value) {
		layers.forEach(l -> l.load$(varName, value));
	}

	void set(String varName, List<?> value) {
		layers.forEach(l -> l.set$(varName, value));
	}

	private List<String> concepts(Class<? extends Layer> layerClass) {
		return graph().layerFactory.names(layerClass);
	}

	void syncLayers() {
		layers.forEach(l -> layers.forEach(l::sync$));
	}

}