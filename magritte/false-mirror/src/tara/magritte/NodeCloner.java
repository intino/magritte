package tara.magritte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class NodeCloner {

    private final List<Node> nodes;
    private final Node node;
    private final ModelHandler model;
    private final Map<String, Node> cloneMap = new HashMap<>();
    private final NodeLoader loader = cloneMap::get;

    private NodeCloner(List<Node> nodes, Node node, ModelHandler model) {
        this.nodes = nodes;
        this.node = node;
        this.model = model;
    }

    public static void clone(List<Node> toClone, Node node, ModelHandler model) {
        new NodeCloner(toClone, node, model).execute();
    }

    private void execute() {
        model.loaders.add(loader);
        nodes.stream()
            .map(p -> clone(node.id() + "." + model.createNodeId(), p, node))
                .forEach(node::add);
        model.loaders.remove(loader);
    }

    private Node clone(String name, Node toClone, Node owner) {
        Node clone = new Node(name);
        clone.owner(owner);
        toClone.typeNames.forEach(n -> clone.addLayer(model.$concept(n)));
        clone.syncLayers();
        cloneComponents(toClone, clone, name);
        cloneMap.put(toClone.id, clone);
        copyVariables(toClone, clone);
        return clone;
    }

    private void cloneComponents(Node toClone, Node clone, String name) {
        toClone.layers.forEach(origin -> {
            Layer destination = getLayerFrom(clone, origin);
            toClone.content().forEach(c -> destination.addNode(clone(name + "." + c.name(), c, clone)));
        });
    }

    private void copyVariables(Node toClone, Node clone) {
        toClone.layers.forEach(origin -> {
            Layer destination = getLayerFrom(clone, origin);
            origin.variables().entrySet().stream()
                    .filter(e -> !e.getValue().isEmpty())
                    .forEach(e -> destination._set(e.getKey(), e.getValue()));
        });
    }

    private Layer getLayerFrom(Node clone, Layer origin) {
        return clone.layers.stream().filter(l -> l.getClass() == origin.getClass()).findFirst().get();
    }

}
