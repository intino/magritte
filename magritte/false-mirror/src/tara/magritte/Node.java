package tara.magritte;

import java.util.*;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;
import static tara.magritte.utils.StashHelper.stashName;

public class Node extends Predicate {

	private static final Logger LOG = Logger.getLogger(Node.class.getName());
	final List<Layer> layers = new ArrayList<>();
	private Node owner;

	public Node() {
		this("");
	}

	public Node(String name) {
		super(name);
	}

	@Override
	public List<Concept> concepts() {
		return reverseListOf(new ArrayList<>(typeNames)).stream().map(t -> model().concept(t)).collect(toList());
	}

	public Graph graph() {
		Node node = this;
		while (node.owner != null)
			node = node.owner;
		return (Graph) node;
	}

	public Node root() {
		Node node = this;
		while (!(node.owner instanceof Graph))
			node = node.owner;
		return node;
	}

	public void add(Node node) {
		for (Layer layer : layers) layer.addInstance(node);
	}

	@Override
	public Map<String, List<?>> variables() {
		Map<String, List<?>> variables = new HashMap<>();
		layers.forEach(m -> variables.putAll(m.variables()));
		return variables;
	}

	@Override
	public <T extends Layer> List<T> findNode(Class<T> layerClass) {
		List<T> tList = new ArrayList<>();
		if (is(layerClass))
			tList.add(as(layerClass));
		content().forEach(c -> tList.addAll(c.findNode(layerClass)));
		return tList;
	}

	protected void remove(Node node) {
		layers.forEach(l -> l.deleteInstance(node));
	}

	public void addLayers(List<Concept> concepts) {
		concepts.forEach(this::addLayer);
	}

	public Node addLayer(Concept concept) {
		if (is(concept.id())) return this;
		putType(concept);
		createLayer(concept);
		removeParentLayer(concept);
		return this;
	}

	public Node addLayer(Class<? extends Layer> layerClass) {
		createLayer(layerClass);
		return this;
	}

	public void removeLayers(List<Concept> concepts) {
		concepts.forEach(this::removeLayer);
	}

	@SuppressWarnings("unused")
	public Node removeLayer(Concept concept) {
		if (!is(concept.id())) return this;
		deleteType(concept);
		deleteLayer(concept.layerClass());
		return this;
	}

	public Node removeLayer(Class<? extends Layer> layerClass) {
		deleteLayer(layerClass);
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T extends Layer> T as(Class<T> layerClass) {
		for (Layer layer : layers)
			if (layerClass.isAssignableFrom(layer.getClass())) return (T) layer;
		return null;
	}

	public Layer as(String conceptName) {
		return as(model().layerFactory.layerClass(conceptName));
	}

	@Override
	public List<Node> components() {
		Set<Node> nodes = new LinkedHashSet<>();
		reverseListOf(layers).forEach(l -> nodes.addAll(l.components()));
		return new ArrayList<>(nodes);
	}

	@SuppressWarnings("unused")
	public <T extends Layer> List<T> components(Class<T> layerClass) {
		List<String> types = model().layerFactory.names(layerClass);
		return components().stream()
				.filter(c -> c.isAnyOf(types))
				.map(c -> c.as(layerClass))
				.collect(toList());
	}

	public List<Node> content() {
		Set<Node> nodes = new LinkedHashSet<>();
		reverseListOf(layers).forEach(l -> nodes.addAll(l.instances()));
		return new ArrayList<>(nodes);
	}

	@SuppressWarnings("unused")
	public <T extends Layer> List<T> nodesAs(Class<T> layerClass) {
		List<String> types = model().layerFactory.names(layerClass);
		return content().stream()
				.filter(c -> c.isAnyOf(types))
				.map(c -> c.as(layerClass))
				.collect(toList());
	}

	@SuppressWarnings("unused")
	public List<Node> features() {
		Set<Node> nodes = new LinkedHashSet<>();
		reverseListOf(layers).forEach(l -> nodes.addAll(l.features()));
		return new ArrayList<>(nodes);
	}

	@SuppressWarnings("unused")
	public <T extends Layer> List<T> features(Class<T> layerClass) {
		List<String> types = model().layerFactory.names(layerClass);
		return content().stream()
				.filter(c -> c.isAnyOf(types))
				.map(c -> c.as(layerClass))
				.collect(toList());
	}

	public void owner(Node owner) {
		this.owner = owner;
	}

	public Node owner() {
		return owner;
	}

	public <T extends Layer> T ownerAs(Class<T> layerClass) {
		if (owner == null) return null;
		if (owner.is(layerClass)) return owner.as(layerClass);
		return owner.ownerAs(layerClass);
	}

	public void load(Layer layer, String name, List<?> values) {
		if (layer.instance() == this)
			layer._load(name, values);
		else
			LOG.severe("Layer does not belong to node " + name);
	}

	public void set(Layer layer, String name, List<?> values) {
		if (layer.instance() == this)
			layer._set(name, values);
		else
			LOG.severe("Layer does not belong to node " + name);
	}

	public void save(){
		model().save(this);
	}

	private void createLayer(Concept concept) {
		Layer layer = model().layerFactory.create(concept.id, this);
		if (layer != null) this.layers.add(0, layer);
	}

	private void deleteLayer(Class<? extends Layer> layerClass) {
		layers.remove(as(layerClass));
	}

	private void createLayer(Class<? extends Layer> layerClass) {
		Layer layer = model().layerFactory.create(layerClass, this);
		if (layer != null) this.layers.add(0, layer);
	}

	private void removeParentLayer(Concept concept) {
		if (concept.parent() == null || concept.parent().isAbstract()) return;
		layers.remove(layers.stream()
				.filter(l -> l.getClass() == concept.parent().layerClass()).findFirst().orElse(null));
	}

	public Model model() {
		return graph().model();
	}

	public String namespace() {
		return stashName(id);
	}

	private <T> List<T> reverseListOf(List<T> list) {
		List<T> result = new ArrayList<>(list);
		Collections.reverse(result);
		return result;
	}

	public void delete() {
		model().remove(this);
	}

	public boolean is(String concept) {
		return typeNames.contains(concept);
	}

	public boolean is(Concept concept) {
		return typeNames.contains(concept.name());
	}

	public boolean is(Class<? extends Layer> layer) {
		return isAnyOf(concepts(layer));
	}

	private List<String> concepts(Class<? extends Layer> layerClass) {
		return model().layerFactory.names(layerClass);
	}

	boolean isAnyOf(List<String> concepts) {
		return concepts.stream().filter(this::is).findFirst().isPresent();
	}

	public Layer as(Concept concept) {
		return as(concept.id);
	}

	void syncLayers() {
		layers.forEach(l -> layers.forEach(l::_facet));
	}
}