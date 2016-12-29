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
        if(graph.application != null) graph.application.addNode(node);
        if(graph.platform != null) graph.platform.addNode(node);
    }

    @Override
    protected void remove(Node node) {
        super.remove(node);
        if(graph.application != null) graph.application.removeNode(node);
        if(graph.platform != null) graph.platform.removeNode(node);
    }
}
