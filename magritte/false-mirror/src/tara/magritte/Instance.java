package tara.magritte;

import java.util.*;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;
import static tara.magritte.utils.StashHelper.stashName;

public class Instance extends Predicate {

	private static final Logger LOG = Logger.getLogger(Instance.class.getName());
	final List<Layer> layers = new ArrayList<>();
	private Instance owner;

	public Instance() {
		this("");
	}

	public Instance(String name) {
		super(name);
	}

	@Override
	public List<Concept> types() {
		return reverseListOf(new ArrayList<>(typeNames)).stream().map(t -> model().concept(t)).collect(toList());
	}

	public Soil root() {
		Instance instance = this;
		while (instance.owner != null)
			instance = instance.owner;
		return (Soil) instance;
	}

	public Instance main() {
		Instance instance = this;
		while (!(instance.owner instanceof Soil))
			instance = instance.owner;
		return instance;
	}

	public void add(Instance component) {
		for (Layer layer : layers) layer.addInstance(component);
	}

	@Override
	public Map<String, List<?>> variables() {
		Map<String, List<?>> variables = new HashMap<>();
		layers.forEach(m -> variables.putAll(m.variables()));
		return variables;
	}

	@Override
	public <T extends Layer> List<T> findInstance(Class<T> aClass) {
		List<T> tList = new ArrayList<>();
		if (is(aClass))
			tList.add(as(aClass));
		instances().forEach(c -> tList.addAll(c.findInstance(aClass)));
		return tList;
	}

	protected void removeInstance(Instance instance) {
		layers.forEach(l -> l.deleteInstance(instance));
	}

	public void addLayers(List<Concept> concepts) {
		concepts.forEach(this::addLayer);
	}

	public Instance addLayer(Concept concept) {
		if (is(concept.id())) return this;
		putType(concept);
		createLayer(concept);
		removeParentLayer(concept);
		return this;
	}

	public Instance addLayer(Class<? extends Layer> layerClass) {
		createLayer(layerClass);
		return this;
	}

	@SuppressWarnings("unused")
	public Instance removeLayer(Concept concept) {
		if (!is(concept.id())) return this;
		deleteType(concept);
		deleteLayer(concept);
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
	public List<Instance> components() {
		Set<Instance> instances = new LinkedHashSet<>();
		reverseListOf(layers).forEach(l -> instances.addAll(l.components()));
		return new ArrayList<>(instances);
	}

	@SuppressWarnings("unused")
	public <T extends Layer> List<T> components(Class<T> layerClass) {
		List<String> types = model().layerFactory.names(layerClass);
		return components().stream()
				.filter(c -> c.isAnyOf(types))
				.map(c -> c.as(layerClass))
				.collect(toList());
	}

	public List<Instance> instances() {
		Set<Instance> instances = new LinkedHashSet<>();
		reverseListOf(layers).forEach(l -> instances.addAll(l.instances()));
		return new ArrayList<>(instances);
	}

	@SuppressWarnings("unused")
	public <T extends Layer> List<T> instances(Class<T> layerClass) {
		List<String> types = model().layerFactory.names(layerClass);
		return instances().stream()
				.filter(c -> c.isAnyOf(types))
				.map(c -> c.as(layerClass))
				.collect(toList());
	}

	@SuppressWarnings("unused")
	public List<Instance> features() {
		Set<Instance> instances = new LinkedHashSet<>();
		reverseListOf(layers).forEach(l -> instances.addAll(l.features()));
		return new ArrayList<>(instances);
	}

	@SuppressWarnings("unused")
	public <T extends Layer> List<T> features(Class<T> layerClass) {
		List<String> types = model().layerFactory.names(layerClass);
		return instances().stream()
				.filter(c -> c.isAnyOf(types))
				.map(c -> c.as(layerClass))
				.collect(toList());
	}

	public void owner(Instance owner) {
		this.owner = owner;
	}

	public Instance owner() {
		return owner;
	}

	public <T extends Layer> T ownerWith(Class<T> $Class) {
		if (owner == null) return null;
		if (owner.is($Class)) return owner.as($Class);
		return owner.ownerWith($Class);
	}

	public void load(Layer layer, String name, List<?> objects) {
		if (layer.instance() == this)
			layer._load(name, objects);
		else
			LOG.severe("Layer does not belong to instance " + name);
	}

	public void set(Layer layer, String name, List<?> objects) {
		if (layer.instance() == this)
			layer._set(name, objects);
		else
			LOG.severe("Layer does not belong to instance " + name);
	}

	public void save(){
		model().save(this);
	}

	private void createLayer(Concept concept) {
		Layer layer = model().layerFactory.create(concept.id, this);
		if (layer != null) this.layers.add(0, layer);
	}

	private void deleteLayer(Concept concept) {
		layers.remove(as(concept.layerClass()));
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
		return root().model();
	}

	public String stash() {
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

	public boolean is(String type) {
		return typeNames.contains(type);
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