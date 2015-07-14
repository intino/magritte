package monopoly;

import tara.magritte.Morph;
import tara.magritte.Node;

import java.util.Collections;
import java.util.List;

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
    protected void add(Node component) {
    }

    @Override
    protected void set(String name, Object object) {
        if (name.equalsIgnoreCase("id")) id = (String) object;
        if (name.equalsIgnoreCase("square")) square = (Square) object;
    }

    @Override
    public List<Node> components() {
        return Collections.emptyList();
    }
}
