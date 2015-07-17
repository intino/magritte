package tara.magritte.wraps.variables;

import tara.magritte.Node;
import tara.magritte.NodeWrap;
import tara.magritte.Set;
import tara.magritte.handlers.Casting;
import tara.magritte.schema.ListSet;

import java.util.List;


public class Assignable {
    private final List<Node> nodes;
    private final Node scope;

    public Assignable(List<Node> nodes, Node scope) {
        this.nodes = nodes;
        this.scope = scope;
    }

    public Set<Node> nodes() {
        return ListSet.cast(nodes);
    }

    public <T extends NodeWrap> Set<T> as(Class<T> wrapClass) {
        return Casting.cast(nodes(), scope).as(wrapClass);
    }
}
