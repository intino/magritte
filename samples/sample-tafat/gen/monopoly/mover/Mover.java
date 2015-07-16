package monopoly.mover;

import tafat.Behavior;
import tara.magritte.Morph;
import tara.magritte.Node;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class Mover extends Morph {

    Behavior _type;

    public Mover(Node node) {
        super(node);
        _type = node.morph(Behavior.class);
    }

    public Mover(Morph morph, Node node) {
        super(morph, node);
        _type = node.morph(Behavior.class);
    }

    public int step() {
        return _type.step();
    }

    public void step(Integer value) {
        _type.step(value);
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
