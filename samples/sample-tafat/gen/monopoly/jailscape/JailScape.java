package monopoly.jailscape;

import tafat.Behavior;
import tara.magritte.Expression;
import tara.magritte.Morph;
import tara.magritte.Node;

import java.util.Collections;
import java.util.List;

public class JailScape extends Morph {
    Behavior _type;
    Modes[] modes;

    public JailScape(Node node) {
        super(node);
        _type = node.morph(Behavior.class);
    }

    public JailScape(Morph morph, Node node) {
        super(morph, node);
        set("modes", ((JailScape)morph).modes);
        _type = node.morph(Behavior.class);
    }

    public int step() {
        return _type.step();
    }

    public void step(int value) {
        _type.step(value);
    }

    public Modes[] modes() {
        return modes;
    }

    public void Modes(Modes... values) {
        modes = values;
    }

    @Override
    protected void add(Node component) {
    }

    @Override
    protected void set(String name, Object object) {
        if(name.equalsIgnoreCase("modes"))
            modes = (Modes[]) object;
    }

    @Override
    public List<Node> components() {
        return Collections.emptyList();
    }

    public enum Modes {
        Card,
        Money;
    }
}
