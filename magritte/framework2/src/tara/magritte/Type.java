package tara.magritte;

import java.util.ArrayList;
import java.util.List;

public class Type extends Node {

    final List<Node> components = new ArrayList<>();
    private Node owner = null;

    public Type(String name) {
        super(name);
    }

    @Override
    public boolean is(String type) {
        return name.equalsIgnoreCase(type) || super.is(type);
    }

    @Override
    public void add(Node component) {
        components.add(component);
    }

}
