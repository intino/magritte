package io.intino.tara.magritte;

import java.util.Map;

class Model extends Node {

    Graph graph;
    private Map<Class<? extends GraphView>, GraphView> views;

    Model(Graph graph, Map<Class<? extends GraphView>, GraphView> views) {
        this.graph = graph;
        this.views = views;
        addLayer(M1.class);
        addLayer(M2.class);
        addLayer(M3.class);
        typeNames.add("Model");
    }

    @Override
    public Graph graph() {
        return graph;
    }

    @Override
    public void add(Node node) {
        super.add(node);
        views.values().forEach(view -> view.addNode(node));
    }

    @Override
    protected void remove(Node node) {
        super.remove(node);
        views.values().forEach(view -> view.removeNode(node));
    }
}
