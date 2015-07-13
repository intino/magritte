package tara.magritte.wraps;

import tara.magritte.Graph;
import tara.magritte.GraphWrap;
import tara.magritte.Node;
import tara.magritte.Set;
import tara.magritte.handlers.NodeProducer;
import tara.magritte.*;
import tara.magritte.handlers.Casting;
import tara.magritte.helpers.Extract;
import tara.magritte.helpers.Selection;

import java.util.WeakHashMap;

import static tara.magritte.handlers.Casting.cast;
import static tara.magritte.helpers.Selection.instancesOf;

public class Model implements GraphWrap {

    protected final Graph graph;
    protected final static WeakHashMap<String, Object> queries = new WeakHashMap<>();

    protected Model(Graph graph) {
        this.graph = graph;
    }

    protected Model(Morph morph) {
        this.graph = morph._graph();
    }


    @Override
    public Graph _graph() {
        return graph;
    }

    public <T extends Morph> Set<T> _find(Class<T> morphClass) {
        return Casting.cast(casesOf(morphClass)).as(morphClass);
    }

    @SuppressWarnings("unchecked")
    protected <T extends Morph> Set<T> _get(String query, Class<T> morphClass) {
        if (!queries.containsKey(query))
            queries.put(query, Casting.cast(rootsOf(morphClass)).as(morphClass));
        return (Set<T>) queries.get(query);
    }

    private <T extends Morph> Set<Node> rootsOf(Class<T> morphClass) {
        return graph.roots().filter(Selection.instancesOf(Extract.nameOf(morphClass)));
    }

    private <T extends Morph> Set<Node> casesOf(Class<T> morphClass) {
        return graph.find(Selection.instancesOf(Extract.nameOf(morphClass)));
    }

    protected Casting.NodeCasting _create(Type type) {
        return create(type._node());
    }

    protected <T extends Morph> T _create(Class<T> morphClass) {
        return create(_typeOf(morphClass)).as(morphClass);
    }

    protected void _remove(Morph morph) {
        //TODO
    }

    private Casting.NodeCasting create(Node type) {
        return Casting.cast(make().with(type).node());
    }

    private NodeProducer make() {
        Node node = graph.createNode();
        node.set(Tag.Root, Tag.Case);
        return NodeProducer.produce(node);
    }

    protected Node _typeOf(Class<? extends Morph> morphClass) {
        return _graph().get(Extract.nameOf(morphClass));
    }

    protected Set<Type> _aggregables() {
        return Casting.cast(graph.types().filter(Selection.nonSingletonMainTypes())).as(Type.class);
    }

    protected Type _aggregable(int index) {
        return _aggregables().get(index);
    }


}
