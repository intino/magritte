package monopoly.tafat;

import siani.tara.magritte.Expression;
import siani.tara.magritte.Morph;
import siani.tara.magritte.Node;

import java.time.LocalDateTime;


public class Simulation extends Morph {

    LocalDateTime from;
    LocalDateTime to;

    public Simulation(Node node) {
        super(node);
    }

    public LocalDateTime from() {
        return from;
    }

    public LocalDateTime to() {
        return to;
    }

    public void from(LocalDateTime value) {
        from = value;
    }

    public void from(Expression<Double> value) {
        node.set("from", value);
    }

    public void to(LocalDateTime value) {
        to = value;
    }

    public void to(Expression<Double> value) {
        node.set("to", value);
    }

    @Override
    protected void add(Node component) {
    }

    @Override
    protected void set(String name, Object object) {
        if(name.equalsIgnoreCase("from"))
            from = (LocalDateTime) object;
        if(name.equalsIgnoreCase("to"))
            to = (LocalDateTime) object;
    }
}
