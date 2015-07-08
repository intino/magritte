package magritte.wraps.variables;

import magritte.Node;
import magritte.NodeWrap;
import magritte.Set;
import magritte.handlers.Casting;
import magritte.schema.ListSet;

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
