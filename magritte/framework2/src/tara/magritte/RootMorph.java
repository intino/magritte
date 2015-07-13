package tara.magritte;

import java.util.ArrayList;

public class RootMorph extends Morph {

    ArrayList<Node> components = new ArrayList<>(0);

    public RootMorph(Node node) {
        super(node);
    }

    @Override
    protected void add(Node component) {
        components.add(component);
    }

    @Override
    protected void set(String name, Object object) {
    }
}
