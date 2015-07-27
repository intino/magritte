package tafat;


import tara.magritte.Morph;
import tara.magritte.Node;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Facet extends Morph {
    public Facet(Node node) {
        super(node);
    }

    public Facet(Morph morph, Node node) {
        super(morph, node);
    }

    @Override
    public List<Node> _components() {
        return Collections.emptyList();
    }

    @Override
    public Map<String, Object> _variables() {
        return Collections.emptyMap();
    }

    @Override
    protected void _add(Node component) {
    }

    @Override
    protected void _set(String name, Object object) {
    }
}
