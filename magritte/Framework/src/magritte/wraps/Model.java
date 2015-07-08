package magritte.wraps;

import magritte.Graph;
import magritte.GraphWrap;
import magritte.Node;
import magritte.Set;
import magritte.handlers.Casting.NodeCasting;
import magritte.handlers.NodeProducer;

import java.util.WeakHashMap;

import static magritte.Tag.Case;
import static magritte.Tag.Root;
import static magritte.handlers.Casting.cast;
import static magritte.helpers.Extract.nameOf;
import static magritte.helpers.Selection.instancesOf;
import static magritte.helpers.Selection.nonSingletonMainTypes;

public class Model implements GraphWrap {

	protected final static WeakHashMap<String, Object> queries = new WeakHashMap<>();
	protected final Graph graph;

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
