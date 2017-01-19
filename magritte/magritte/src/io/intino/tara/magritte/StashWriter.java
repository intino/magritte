package io.intino.tara.magritte;

import io.intino.tara.io.Variable;
import io.intino.tara.magritte.tags.Terminal;
import io.intino.tara.magritte.types.DateX;
import io.intino.tara.magritte.types.InstantX;
import io.intino.tara.magritte.types.ResX;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.intino.tara.io.Helper.*;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.stream.Collectors.toList;

class StashWriter {

    private final GraphHandler model;
    private final String stash;
    private final List<Node> nodes;

    private StashWriter(GraphHandler model, String stash, List<Node> nodes) {
        this.model = model;
        this.stash = stash;
        this.nodes = nodes;
    }

    static void write(GraphHandler model, String stash, List<Node> nodes) {
        new StashWriter(model, stash, nodes).write();
    }

    private void write() {
        model.store.writeStash(newStash(language(), nodes(this.nodes)), stash);
    }

    private String language() {
        List<String> languages = new ArrayList(model.languages);
        return languages.isEmpty() ? null : languages.get(0);
    }

    private List<io.intino.tara.io.Node> nodes(List<Node> nodes) {
        return nodes.stream().map(this::node).collect(toList());
    }

    private io.intino.tara.io.Node node(Node node) {
        return newNode(node.id, facetsOf(node), variablesOf(node.variables()), nodes(node.componentList()));
    }

    private List<String> facetsOf(Node node) {
        return node.layers.stream()
                .filter(l -> l instanceof Terminal)
                .map(this::conceptIdOf).collect(toList());
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
        if (value instanceof ResX) return newResource(variable.getKey(), resourceOf(variable.getValue()));
        if (value instanceof Layer) return newReference(variable.getKey(), refsOfLayers(variable.getValue()));
        if (value instanceof Enum) return newWord(variable.getKey(), words(variable.getValue()));
        if (value instanceof NativeCode) return newFunction(variable.getKey(), classesOf(variable.getValue()));
        if (value instanceof InstantX) return newInstant(variable.getKey(), instantOf(variable.getValue()));
        if (value instanceof DateX) return newDate(variable.getKey(), dateOf(variable.getValue()));
        if (value instanceof LocalTime) return newTime(variable.getKey(), timeOf(variable.getValue()));
        if (value instanceof Concept) return newConcept(variable.getKey(), conceptOf(variable.getValue()));
        return newObject(variable.getKey(), objectOf(variable.getValue()));
    }


    private List<String> conceptOf(List<?> values) {
        return values.stream().map(v -> ((Concept) v).id()).collect(toList());
    }

    private List<String> resourceOf(List<?> values) {
        return values.stream().map(v -> model.store.relativePathOf(((ResX) v).getURL())).collect(toList());
    }

    private List<String> timeOf(List<?> values) {
        return values.stream().map(v -> ((LocalTime) v).format(ofPattern("HH:mm:ss"))).collect(toList());
    }

    private List<String> instantOf(List<?> values) {
        return values.stream().map(Object::toString).collect(toList());
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
        return layers.stream().map(l -> ((Layer) l).id()).collect(toList());
    }

    private List<String> words(List<?> values) {
        return values.stream().map(Object::toString).collect(toList());
    }

    private String conceptIdOf(Layer layer) {
        return model.layerFactory.names(layer.getClass()).get(0);
    }
}
