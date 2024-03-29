package io.intino.magritte.framework;

import io.intino.magritte.framework.tags.Terminal;
import io.intino.magritte.framework.types.DateX;
import io.intino.magritte.io.model.Variable;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.intino.magritte.io.Helper.*;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.stream.Collectors.toList;

class StashWriter {

	private final Graph model;
	private final String stash;
	private final List<Node> nodes;

	private StashWriter(Graph model, String stash, List<Node> nodes) {
		this.model = model;
		this.stash = stash;
		this.nodes = nodes;
	}

	static void write(Graph model, String stash, List<Node> nodes) {
		new StashWriter(model, stash, nodes).write();
	}

	private void write() {
		model.store.writeStash(newStash(language(), nodes(this.nodes)), stash);
	}

	private String language() {
		List<String> languages = new ArrayList<>(model.languages);
		return languages.isEmpty() ? null : languages.get(0);
	}

	private List<io.intino.magritte.io.model.Node> nodes(List<Node> nodes) {
		return nodes.stream().map(this::node).collect(toList());
	}

	private io.intino.magritte.io.model.Node node(Node node) {
		return newNode(node.id, layersOf(node), variablesOf(node.variables()), nodes(node.componentList()));
	}

	private List<String> layersOf(Node node) {
		return node.layers.stream()
				.filter(l -> l instanceof Terminal)
				.map(this::conceptIdOf).collect(toList());
	}

	private List<? extends Variable> variablesOf(Map<String, List<?>> variables) {
		variables.entrySet().forEach(e -> e.setValue(new ArrayList<>(e.getValue())));
		return variables.entrySet().stream()
				.filter(e -> !e.getValue().isEmpty() && e.getValue().get(0) != null)
				.map(this::variableOf).collect(toList());
	}

	private Variable variableOf(Map.Entry<String, List<?>> variable) {
		Object value = variable.getValue().get(0);
		if (value instanceof Concept) return newVariableOfList(variable.getKey(), conceptOf(variable.getValue()));
		if (value instanceof URL) return newVariableOfList(variable.getKey(), resourceOf(variable.getValue()));
		if (value instanceof Layer) return newVariableOfList(variable.getKey(), refsOfLayers(variable.getValue()));
		if (value instanceof Enum) return newVariableOfList(variable.getKey(), words(variable.getValue()));
		if (value instanceof NativeCode) return newVariableOfList(variable.getKey(), classesOf(variable.getValue()));
		if (value instanceof Instant) return newVariableOfList(variable.getKey(), instantOf(variable.getValue()));
		if (value instanceof DateX) return newVariableOfList(variable.getKey(), dateOf(variable.getValue()));
		if (value instanceof LocalTime) return newVariableOfList(variable.getKey(), timeOf(variable.getValue()));
		else return newVariableOfList(variable.getKey(), variable.getValue());
	}

	private List<String> conceptOf(List<?> values) {
		return values.stream().map(v -> ((Concept) v).id()).collect(toList());
	}

	private List<String> resourceOf(List<?> values) {
		return values.stream().map(v -> model.store.relativePathOf(((URL) v))).collect(toList());
	}

	private List<String> timeOf(List<?> values) {
		return values.stream().map(v -> ((LocalTime) v).format(ofPattern("HH:mm:ss"))).collect(toList());
	}

	private List<Long> instantOf(List<?> values) {
		return values.stream().map(i -> ((Instant) i).toEpochMilli()).collect(toList());
	}

	private List<String> dateOf(List<?> values) {
		return values.stream().map(v -> ((LocalDateTime) v).format(ofPattern("dd/MM/yyyy HH:mm:ss"))).collect(toList());
	}

	private List<Object> objectOf(List<?> values) {
		return values.stream().map(v -> ((Object) v)).collect(toList());
	}

	private List<String> classesOf(List<?> values) {
		return values.stream().map(v -> v.getClass().getCanonicalName()).collect(toList());
	}

	private List<String> refsOfLayers(List<?> layers) {
		return layers.stream().map(l -> ((Layer) l).core$().id()).collect(toList());
	}

	private List<String> words(List<?> values) {
		return values.stream().map(Object::toString).collect(toList());
	}

	private String conceptIdOf(Layer layer) {
		return model.layerFactory.names(layer.getClass()).get(0);
	}
}
