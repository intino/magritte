package siani.tara.magritte.wraps;

import siani.tara.magritte.Graph;
import siani.tara.magritte.GraphWrap;
import siani.tara.magritte.Node;
import siani.tara.magritte.Set;
import siani.tara.magritte.handlers.Casting.NodeCasting;
import siani.tara.magritte.handlers.NodeProducer;

import java.util.WeakHashMap;

import static siani.tara.magritte.Tag.Case;
import static siani.tara.magritte.Tag.Root;
import static siani.tara.magritte.handlers.Casting.cast;
import static siani.tara.magritte.helpers.Extract.nameOf;
import static siani.tara.magritte.helpers.Selection.instancesOf;
import static siani.tara.magritte.helpers.Selection.nonSingletonMainTypes;

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
        return cast(casesOf(morphClass)).as(morphClass);
    }

    @SuppressWarnings("unchecked")
    protected <T extends Morph> Set<T> _get(String query, Class<T> morphClass) {
        if (!queries.containsKey(query))
            queries.put(query, cast(rootsOf(morphClass)).as(morphClass));
        return (Set<T>) queries.get(query);
    }

    private <T extends Morph> Set<Node> rootsOf(Class<T> morphClass) {
        return graph.roots().filter(instancesOf(nameOf(morphClass)));
    }

    private <T extends Morph> Set<Node> casesOf(Class<T> morphClass) {
        return graph.find(instancesOf(nameOf(morphClass)));
    }

    protected NodeCasting _create(Type type) {
        return create(type._node());
    }

    protected <T extends Morph> T _create(Class<T> morphClass) {
        return create(_typeOf(morphClass)).as(morphClass);
    }

    protected void _remove(Morph morph) {
        //TODO
    }

    private NodeCasting create(Node type) {
        return cast(make().with(type).node());
    }

    private NodeProducer make() {
        Node node = graph.createNode();
        node.set(Root, Case);
        return NodeProducer.produce(node);
    }

    protected Node _typeOf(Class<? extends Morph> morphClass) {
        return _graph().get(nameOf(morphClass));
    }

    protected Set<Type> _aggregables() {
        return cast(graph.types().filter(nonSingletonMainTypes())).as(Type.class);
    }

    protected Type _aggregable(int index) {
        return _aggregables().get(index);
    }


}
