package tafat;


import tara.magritte.Morph;
import tara.magritte.Node;

import java.util.Collections;
import java.util.List;

public class Facet extends Morph {
    public Facet(Node node) {
        super(node);
    }

    public Facet(Morph morph, Node node) {
        super(morph, node);
    }

    @Override
    protected void add(Node component) {
    }

    @Override
    protected void set(String name, Object object) {
    }

    @Override
    public List<Node> components() {
        return Collections.emptyList();
    }
}
