package magritte;


import magritte.Set.Filter;

public interface Graph {
    boolean exists(String name);
    Node get(String name);
    Set<Node> types();
    Set<Node> roots();
    Set<Node> find(Filter filter);

    Node createNode();

    void index(Node node);

}
