package magritte.wraps;

import magritte.*;
import magritte.Node.Member;
import magritte.editors.NodeEditor;
import magritte.editors.VariableAddEditor;
import magritte.editors.VariableEditor;
import magritte.editors.VariableRemoveEditor;
import magritte.schema.ListSet;
import magritte.wraps.variables.Assignable;
import magritte.wraps.variables.Multiple;
import magritte.wraps.variables.Single;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import static magritte.Node.Member.Aggregable;
import static magritte.Node.Member.Component;
import static magritte.Tag.Abstract;
import static magritte.handlers.Casting.cast;
import static magritte.helpers.Extract.classNameOf;
import static magritte.helpers.Extract.nameOf;
import static magritte.helpers.Selection.instancesOf;
import static magritte.wraps.Operation.Add;
import static magritte.wraps.Operation.Remove;

public class Definition implements NodeWrap {

	protected final static Map<String, Object> queries = new WeakHashMap<>();
	protected Node node;
	protected Node scope;

	@Override
	public Node _node() {
		return node;
	}

	public Node _scope() {
		return scope;
	}

	public void _node(Node node) {
		this.node = node;
	}

	public void _scope(Node scope) {
		this.scope = scope;
	}

	public void _target(Node scope) {
		this.scope = scope;
	}

	Graph _graph() {
		return node.graph();
	}

	public String _name() {
		return node.title();
	}

	public Single _get(String variable) {
		return new Single(node.get(variable), node, scope);
	}

	public Multiple _getMultiple(String variable) {
		return new Multiple(node.get(variable), node);
	}

	public Assignable _getAssignable(String variable) {
		return new Assignable(expand(nodeIn("?" + variable)), scope);
	}

	public Definition _owner() {
		return cast(node.owner(), scope).as(Definition.class);
	}

	public Set<Type> _aggregables(Class<? extends NodeWrap> wrapClass) {
		Set<Node> members = ListSet.cast(members(node, Aggregable)).filter(instancesOf(nameOf(wrapClass)));
		return cast(members, scope).as(Type.class);
	}

	protected NodeEditor _edit() {
		return new NodeEditor(scope);
	}

	protected VariableEditor _edit(Operation operation) {
		return operation == Add ? new VariableAddEditor(node) : operation == Remove ? new VariableRemoveEditor(node) : _edit();
	}


	public <T extends NodeWrap> T _component(Class<T> wrapClass) {
		return _components(wrapClass).get(0);
	}

	@SuppressWarnings("unchecked")
	public <T extends NodeWrap> Set<T> _components(Class<T> wrapClass) {
		return cast(node.members(Component).filter(instancesOf(nameOf(wrapClass))), scope).as(wrapClass);
	}

/*

    @SuppressWarnings("unchecked")
    public <T extends NodeWrap> Set<T> _components(Class<T> wrapClass) {
        String query = query("components", wrapClass);
        Object set = cache(query);
        return set != null ? (Set<T>) set : (Set<T>) cache(query, cast(node.members(Component).filter(instancesOf(nameOf(wrapClass)))).as(wrapClass));
    }

    private Object cache(String query, Object value) {
        queries.put(query, value);
        return value;
    }

    private Object cache(String query) {
        return queries.get(query);
    }


    private String query(String name, Class morphClass) {
        return "name" + "/" + node.hashCode() + "/" + morphClass.hashCode();
    }

    private Set<Node> components(Node type) {
        return ListSet.cast(members(type, Component));
    }
*/

	private List<Node> members(Node type, Member member) {
		List<Node> result = new ArrayList<>();
		if (type == null) return result;
		result.addAll(members(type.parent(), member));
		result.addAll(expand(type.members(member)));
		return result;
	}

	private List<Node> expand(Set<Node> nodes) {
		List<Node> result = new ArrayList<>();
		for (Node node : nodes) result.addAll(expand(node));
		return result;
	}

	private List<Node> expand(Node node) {
		List<Node> result = new ArrayList<>();
		result.addAll(node.children().asList());
		if (!node.is(Abstract)) result.add(node);
		return result;
	}

	private Graph model() {
		return scope.graph();
	}

	private Node nodeIn(String variable) {
		Reference reference = node.get(variable);
		return node(reference);
	}

	private Node node(Reference reference) {
		return node(reference.value());
	}

	private Node node(String value) {
		return model().get(value);
	}

	@Override
	public String toString() {
		return (this.getClass() != Definition.class ? classNameOf(this) : "") + node.toString();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
