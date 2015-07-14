package tara.magritte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Root extends Morph {

    List<Node> components = new ArrayList<>();

    public Root(Node node) {
        super(node);
    }

    @Override
    protected void add(Node component) {
        components.add(component);
    }

    @Override
    protected void set(String name, Object object) {
    }

    @Override
    public List<Node> components() {
        return Collections.unmodifiableList(components);
    }
}
