package tara.magritte;

import tara.io.Facet;
import tara.io.Variable;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.stream.Collectors.toList;
import static tara.io.Helper.*;

public class StashWriter {

	private static final Logger LOG = Logger.getLogger(StashWriter.class.getName());

	private final GraphHandler model;
	private final String stash;
	private final List<Node> nodes;

	public StashWriter(GraphHandler model, String stash, List<Node> nodes) {
		this.model = model;
		this.stash = stash;
		this.nodes = nodes;
	}

	public static void write(GraphHandler model, String stash, List<Node> nodes) {
		new StashWriter(model, stash, nodes).write();
	}

	private void write() {
		model.store.writeStash(newStash(language(), nodes(this.nodes)), stash);
	}

	private String language() {
		List<String> languages = new ArrayList(model.languages);
		return languages.size() > 1 ? languages.get(1) : languages.size() == 1 ? languages.get(0) : null;
	}

	private List<tara.io.Node> nodes(List<Node> nodes) {
		return nodes.stream().map(this::node).collect(toList());
	}

	private tara.io.Node node(Node node) {
		return newNode(node.id, facetsOf(node));
	}

	private List<Facet> facetsOf(Node node) {
		return node.layers.stream()
				.filter(l -> l instanceof tara.magritte.tags.Concept)
				.map(this::facetOf).collect(toList());
	}

	private Facet facetOf(Layer layer) {
		return newFacet(layerName(layer), variablesOf(layer.variables()), nodes(layer.componentList()));
	}

	private List<? extends Variable> variablesOf(Map<String, List<?>> variables) {
		return variables.entrySet().stream()
				.filter(e -> !e.getValue().isEmpty() && e.getValue().get(0) != null)
				.map(this::variableOf).collect(toList());
	}

	private Variable variableOf(Map.Entry<String, List<?>> variable) {
		Object value = variable.getValue().get(0);
		if (value instanceof Integer) return newInteger(variable.getKey(), (List<Integer>) variable.getValue());
		if (value instanceof Double) return newDouble(variable.getKey(), (List<Double>) variable.getValue());
		if (value instanceof Boolean) return newBoolean(variable.getKey(), (List<Boolean>) variable.getValue());
		if (value instanceof String) return newString(variable.getKey(), (List<String>) variable.getValue());
		if (value instanceof URL) return newResource(variable.getKey(), resourceOf(variable.getValue()));
		if (value instanceof Layer) return newReference(variable.getKey(), refsOfLayers(variable.getValue()));
		if (value instanceof Reference) return newReference(variable.getKey(), refs(variable.getValue()));
		if (value instanceof Enum) return newWord(variable.getKey(), words(variable.getValue()));
		if (value instanceof NativeCode) return newFunction(variable.getKey(), classesOf(variable.getValue()));
		if (value instanceof LocalDateTime) return newDate(variable.getKey(), dateOf(variable.getValue()));
		if (value instanceof LocalTime) return newTime(variable.getKey(), timeOf(variable.getValue()));
		LOG.severe("Type of variable " + variable.getKey() + " cannot be identified");
		return null;
	}

	private List<String> resourceOf(List<?> values) {
		return values.stream().map(v -> model.store.relativePathOf((URL)v)).collect(toList());
	}

	private List<String> timeOf(List<?> values) {
		return values.stream().map(v -> ((LocalTime)v).format(ofPattern("HH:mm:ss"))).collect(toList());
	}

	private List<String> dateOf(List<?> values) {
		return values.stream().map(v -> ((LocalDateTime)v).format(ofPattern("dd/MM/yyyy HH:mm:ss"))).collect(toList());
	}

	private List<String> classesOf(List<?> values) {
		return values.stream().map(v -> v.getClass().getCanonicalName()).collect(toList());
	}

	private List<String> refsOfLayers(List<?> layers) {
		return layers.stream().map(l -> ((Layer) l).id()).collect(toList());
	}

	private List<String> words(List<?> values) {
		return values.stream().map(Object::toString).collect(toList());
	}

	private List<String> refs(List<?> references) {
		return references.stream().map(r -> ((Reference)r).name).collect(toList());
	}

	private String layerName(Layer layer) {
		return model.layerFactory.names(layer.getClass()).get(0);
	}
}
