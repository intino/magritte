package tafat;

import tara.magritte.Morph;
import tara.magritte.Node;

import java.util.List;
import java.util.Map;


public class Job extends Morph {
    public Job(Node node) {
        super(node);
    }

    public Job(Morph morph, Node node) {
        super(morph, node);
    }

    @Override
    public List<Node> _components() {
        return null;
    }

    @Override
    public Map<String, Object> _variables() {
        return null;
    }

    @Override
    protected void add(Node component) {

    }

    @Override
    protected void set(String name, Object object) {

    }

    // TODO

}
