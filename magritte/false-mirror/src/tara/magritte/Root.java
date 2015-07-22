package tara.magritte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Root extends Morph {

    List<Node> components = new ArrayList<>();

    public Root(Node node) {
        super(node);
    }

    @Override
    public List<Node> _components() {
        return Collections.unmodifiableList(components);
    }

    @Override
    protected void add(Node component) {
        components.add(component);
    }

}
