package monopoly.mover;

import tara.magritte.Morph;
import tara.magritte.Node;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class Mover extends Morph {


    public Mover(Node node) {
        super(node);
    }

    public Mover(Morph morph, Node node) {
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
    protected void add(Node component) {
    }

    @Override
    protected void set(String name, Object object) {
    }
}
