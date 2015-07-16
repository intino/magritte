package monopoly.jailscape;

import tafat.Behavior;
import tara.magritte.Morph;
import tara.magritte.Node;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class JailScape extends Morph {
    Modes[] modes;

    public JailScape(Node node) {
        super(node);
    }

    public JailScape(Morph morph, Node node) {
        super(morph, node);
        set("modes", ((JailScape)morph).modes);
    }

    public Modes[] modes() {
        return modes;
    }

    public void Modes(Modes... values) {
        modes = values;
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
        if(name.equalsIgnoreCase("modes")) modes = (Modes[]) object;
    }

    public enum Modes {
        Card,
        Money;
    }
}
