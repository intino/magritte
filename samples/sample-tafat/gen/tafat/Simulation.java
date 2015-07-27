package tafat;

import tara.magritte.Morph;
import tara.magritte.Node;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Simulation extends Morph {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Simulation(Node node) {
        super(node);
    }

    public Simulation(Morph morph, Node node) {
        super(morph, node);
    }

    public LocalDateTime from() {
        return from;
    }

    public void from(LocalDateTime value) {
        from = value;
    }

    public LocalDateTime to() {
        return to;
    }

    public void to(LocalDateTime value) {
        to = value;
    }

    @Override
    public List<Node> _components() {
        return Collections.emptyList();
    }

    @Override
    public Map<String, Object> _variables() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("from", from);
        map.put("to", to);
        return map;
    }

    @Override
    protected void _add(Node component) {
    }

    @Override
    protected void _set(String name, Object object) {
        if (name.equalsIgnoreCase("from")) from = (LocalDateTime) object;
        else if (name.equalsIgnoreCase("to")) to = (LocalDateTime) object;
    }
}
