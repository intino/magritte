package monopoly.monopoly;

import wata._magritte.lite.Morph;
import wata._magritte.lite.Node;

public class Player extends Morph {

    protected String id;
    protected Square square = null;

    public Player(Node node) {
        super(node);
    }

    public String id() {
        return id;
    }

    public Square square() {
        return square;
    }

    public void id(String value) {
        id = value;
    }

//    public void id(Expression<String> value) {
//        node.set("id", value);
//    }

    public void square(Square value) {
        square = value;
    }

    @Override
    protected void add(Node component) {
    }

    @Override
    protected void set(String name, Object object) {
        if(name.equalsIgnoreCase("id"))
            id = (String) object;
        // TODO como no se setean no est� hecho
    }
}
