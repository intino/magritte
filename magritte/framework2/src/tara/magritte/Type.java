package tara.magritte;

import java.util.ArrayList;

public class Type extends Node {

    final ArrayList<Node> components = new ArrayList<>();
    private Node owner = null;

    public Type(String name) {
        super(name);
    }

    @Override
    public void add(Node component) {
        components.add(component);
    }

}
