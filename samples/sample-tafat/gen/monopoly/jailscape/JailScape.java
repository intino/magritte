package monopoly.jailscape;

import tafat.Behavior;
import tara.magritte.Morph;
import tara.magritte.Node;

import java.util.*;

public abstract class JailScape extends Morph {
    Behavior _type;
    List<Modes> modes;

    public JailScape(Node node) {
        super(node);
        _type = node.morph(Behavior.class);
        set("modes", Arrays.asList(Modes.Card, Modes.Money));
    }

    public JailScape(Morph morph, Node node) {
        super(morph, node);
        _type = node.morph(Behavior.class);
        set("modes", ((JailScape) morph).modes);
    }

    public int step() {
        return _type.step();
    }

    public void step(Integer value) {
        _type.step(value);
    }

    public List<Modes> modes() {
        return modes;
    }

    public void Modes(Modes... values) {
	    modes = Arrays.asList(values);
    }

    @Override
    public List<Node> _components() {
        return Collections.emptyList();
    }

    @Override
    public Map<String, Object> _variables() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("modes", modes);
        return map;
    }

    @Override
    protected void add(Node component) {
    }

    @Override
    protected void set(String name, Object object) {
        if (name.equalsIgnoreCase("modes")) modes = (List<Modes>) object;
    }

    public enum Modes {
        Card,
        Money;
    }
}
