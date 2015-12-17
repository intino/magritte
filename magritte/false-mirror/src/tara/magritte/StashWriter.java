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
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static tara.io.Helper.*;

public class StashWriter {

	private static final Logger LOG = Logger.getLogger(StashWriter.class.getName());

	private final ModelHandler model;
	private final String stash;
	private final List<Instance> instances;

	public StashWriter(ModelHandler model, String stash, List<Instance> instances) {
		this.model = model;
		this.stash = stash;
		this.instances = instances;
	}

	public static void write(ModelHandler model, String stash, List<Instance> instances) {
		new StashWriter(model, stash, instances).write();
	}

	private void write() {
		model.store.writeStash(newStash(language(), emptyList(), emptyList(), instances(this.instances)), stash);
	}

	private String language() {
		List<String> languages = new ArrayList(model.languages);
		return languages.size() > 1 ? languages.get(languages.size() - 1) : null;
	}

	private List<tara.io.Instance> instances(List<Instance> instances) {
		return instances.stream().map(this::instance).collect(toList());
	}

	private tara.io.Instance instance(Instance instance) {
		return newInstance(instance.name, facetsOf(instance));
	}

	private List<Facet> facetsOf(Instance instance) {
		return instance.layers.stream()
				.filter(l -> l instanceof tara.magritte.tags.Concept)
				.map(this::facetOf).collect(toList());
	}

	private Facet facetOf(Layer layer) {
		return newFacet(layerName(layer), variablesOf(layer._variables()), instances(layer._instances()));
	}

	private List<? extends Variable> variablesOf(Map<String, List<?>> variables) {
		return variables.entrySet().stream()
				.filter(e -> e.getValue() != null && !e.getValue().isEmpty())
				.map(this::variableOf).collect(toList());
	}

	private Variable variableOf(Map.Entry<String, List<?>> variable) {
		Object value = variable.getValue().get(0);
		if (value instanceof Integer) return newInteger(variable.getKey(), (List<Integer>) variable.getValue());
		else if (value instanceof Double) return newDouble(variable.getKey(), (List<Double>) variable.getValue());
		else if (value instanceof Boolean) return newBoolean(variable.getKey(), (List<Boolean>) variable.getValue());
		else if (value instanceof String) return newString(variable.getKey(), (List<String>) variable.getValue());
		else if (value instanceof URL) return newResource(variable.getKey(), resourceOf(variable.getValue()));
		else if (value instanceof Layer) return newReference(variable.getKey(), refsOfLayers(variable.getValue()));
		else if (value instanceof Reference) return newReference(variable.getKey(), refs(variable.getValue()));
		else if (value instanceof Enum) return newReference(variable.getKey(), words(variable.getValue()));
		else if (value instanceof Function) return newReference(variable.getKey(), classesOf(variable.getValue()));
		else if (value instanceof LocalDateTime) return newReference(variable.getKey(), dateOf(variable.getValue()));
		else if (value instanceof LocalTime) return newReference(variable.getKey(), timeOf(variable.getValue()));
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
		return layers.stream().map(l -> ((Layer)l)._name()).collect(toList());
	}

	private List<String> words(List<?> values) {
		return values.stream().map(Object::toString).collect(toList());
	}

	private List<String> refs(List<?> references) {
		return references.stream().map(r -> ((Reference)r).qn).collect(toList());
	}

	private String layerName(Layer layer) {
		return LayerFactory.names(layer.getClass()).get(0);
	}
}
