package tafat;

import tara.magritte.Expression;
import tara.magritte.Morph;
import tara.magritte.Node;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


public class Simulation extends Morph {

    LocalDateTime from;
    LocalDateTime to;

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
    protected void add(Node component) {
    }

    @Override
    protected void set(String name, Object object) {
        if(name.equalsIgnoreCase("from"))
            from = (LocalDateTime) object;
        else if(name.equalsIgnoreCase("to"))
            to = (LocalDateTime) object;
    }

    @Override
    public List<Node> components() {
        return Collections.emptyList();
    }
}
