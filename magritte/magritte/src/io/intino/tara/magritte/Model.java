package io.intino.tara.magritte;

class Model extends Node {

    Graph graph;

    Model() {}

    @Override
    public Graph graph() {
        return graph;
    }

    @Override
    public void add(Node node) {
        super.add(node);
        graph.wrappers.values().forEach(wrapper -> wrapper.addNode(node));
    }

    @Override
    protected void remove(Node node) {
        super.remove(node);
        graph.wrappers.values().forEach(wrapper -> wrapper.removeNode(node));
    }
}
