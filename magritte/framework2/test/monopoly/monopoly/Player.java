package monopoly.monopoly;

import siani.tara.magritte.Morph;
import siani.tara.magritte.Node;
import sun.awt.motif.X11CNS11643;

public class Player extends Morph {

    protected String id;
    protected Square square = null;
    protected Engine x;

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
        if(name)
            x = ((Node)object).morph(Engine.class);
        // TODO como no se setean no est� hecho
    }
}
