package siani.tara.magritte.wraps.variables;

import siani.tara.magritte.Node;
import siani.tara.magritte.NodeWrap;
import siani.tara.magritte.Set;
import siani.tara.magritte.handlers.Casting;
import siani.tara.magritte.schema.ListSet;

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
