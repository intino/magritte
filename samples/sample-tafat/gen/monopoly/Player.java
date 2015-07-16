package monopoly;

import tara.magritte.Morph;
import tara.magritte.Node;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Player extends Morph {

    protected String id;
    protected Square square;

    public Player(Node node) {
        super(node);
    }

    public Player(Morph morph, Node node) {
        super(morph, node);
        set("id", ((Player) morph).id);
        set("square", ((Player) morph).square);
    }

    public String id() {
        return id;
    }

    public void id(String value) {
        id = value;
    }

    public monopoly.Square square() {
        return square;
    }

    public void square(monopoly.Square value) {
        square = value;
    }

    @Override
    public List<Node> _components() {
        return Collections.emptyList();
    }

    @Override
    public Map<String, Object> _variables() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", id);
        map.put("square", square);
        return map;
    }

    @Override
    protected void add(Node component) {
    }

    @Override
    protected void set(String name, Object object) {
        if (name.equalsIgnoreCase("id")) id = (String) object;
        if (name.equalsIgnoreCase("square")) square = (Square) object;
    }
}
