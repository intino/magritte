package magritte.wraps;

import magritte.Graph;
import magritte.Node;
import magritte.NodeWrap;
import magritte.Set;
import magritte.editors.NodeEditor;
import magritte.editors.VariableAddEditor;
import magritte.editors.VariableEditor;
import magritte.editors.VariableRemoveEditor;
import magritte.handlers.Casting.NodeCasting;
import magritte.handlers.NodeProducer;
import magritte.helpers.Extract;
import magritte.wraps.variables.Multiple;
import magritte.wraps.variables.Single;

import java.util.Map;
import java.util.WeakHashMap;

import static magritte.Node.Member.Component;
import static magritte.Tag.Case;
import static magritte.Tag.Link.OwnedMember;
import static magritte.Tag.Link.Owner;
import static magritte.handlers.Casting.cast;
import static magritte.helpers.Check.check;
import static magritte.helpers.Extract.nameOf;
import static magritte.helpers.Selection.instancesOf;
import static magritte.wraps.Operation.Add;
import static magritte.wraps.Operation.Remove;

public class Morph implements NodeWrap, Cloneable {

	private static Map<String, Set> queries = new WeakHashMap<>();
	protected Node node;
	protected Definition definition;

	@Override
	public Node _node() {
		return node;
	}

	public Node _target() {
		return node;
	}

	public void _node(Node node) {
		this.node = node;
	}

	private Definition createDefinition() {
		Definition definition = new Definition();
		definition._node(type());
		definition._scope(node);
		return definition;
	}

	public void _target(Node node) {
		this.node = node;
	}

	public String _name() {
		return node.title();
	}

	public void _name(String name) {
		node.set(name);
	}

	public Set<Type> _types() {
		return cast(node.types()).as(Type.class);
	}

	public Definition _definition() {
		if (definition == null) definition = createDefinition();
		return definition;
	}

	public boolean is(String type) {
		return check(node, type);
	}

	public boolean is(Class<? extends NodeWrap> morphClass) {
		return check(node, nameOf(morphClass));
	}

	public <T extends NodeWrap> T as(Class<T> morphClass) {
		return cast(node).as(morphClass);
	}

	Graph _graph() {
		return node.graph();
	}

	protected VariableEditor _edit() {
		return new NodeEditor(node);
	}

	protected VariableEditor _edit(Operation operation) {
		return operation == Add ? new VariableAddEditor(node) : operation == Remove ? new VariableRemoveEditor(node) : _edit();
	}

	protected Single _get(String variable) {
		return new Single(node.get(variable), node);
	}

	protected Multiple _getMultiple(String variable) {
		return new Multiple(node.get(variable), node);
	}

	public Morph _owner() {
		return _owner(Morph.class);
	}

	public <T extends Morph> T _owner(Class<T> morphClass) {
		Node owner = this.node.owner();
		while (owner != null) {
			if (check(owner, nameOf(morphClass))) return cast(owner).as(morphClass);
			owner = owner.owner();
		}
		return null;
	}

	protected <T extends Morph> T _component(Class<T> morphClass) {
		return _components(morphClass).get(0);
	}

	protected <T extends Morph> Set<T> _components(Class<T> morphClass) {
		String query = "componets" + morphClass.hashCode() + node.hashCode();
		return cache(query, () -> cast(node.members(Component).filter(instancesOf(nameOf(morphClass)))).as(morphClass));
	}

	private <T extends Morph> Set cache(String key, Query<T> query) {
		if (!queries.containsKey(key)) queries.put(key, query.set());
		return queries.get(key);
	}

	public Set<Morph> _referees() {
		return cast(node.fanIn()).as(Morph.class);
	}

	public Morph _referee(int index) {
		return _referees().get(index);
	}

	public <T extends Morph> Set<T> _referees(Class<T> morphClass) {
		return cast(node.fanIn().filter(instancesOf(nameOf(morphClass)))).as(morphClass);
	}

	public Set<Morph> _references() {
		return cast(node.fanOut()).as(Morph.class);
	}

	public Morph _reference(int index) {
		return _references().get(index);
	}

	public <T extends Morph> Set<T> _references(Class<T> morphClass) {
		return cast(node.fanOut().filter(instancesOf(nameOf(morphClass)))).as(morphClass);
	}

	private Node type() {
		for (Node type : node.types())
			if (check(type, nameOf(this.getClass()))) return type;
		return node.type();
	}

	protected <T extends Morph> T _create(Class<T> morphClass) {
		return cast(make(morphClass)).as(morphClass);
	}

	protected NodeCasting _new(Type type) {
		return create(type._node());
	}

	protected void _delete(Morph morph) {
		node.unlink(morph.node);
		morph.node.unlink(node);
	}

	private NodeCasting create(Node type) {
		return cast(make().with(type).node());
	}

	private Node make(Class<? extends Morph> morphClass) {
		return make().with(typeOf(morphClass)).node();
	}

	private NodeProducer make() {
		Node node = _graph().createNode();
		node.set(Case);
		node.link(this.node).as(Owner);
		this.node.link(node).as(OwnedMember);
		return NodeProducer.produce(node);
	}

	private Node typeOf(Class<? extends Morph> morphClass) {
		return _graph().get(nameOf(morphClass));
	}

	private Morph morph(Object object) {
		return (Morph) object;
	}

	@Override
	public boolean equals(Object object) {
		return object != null && object instanceof Morph && node == morph(object).node;
	}

	@Override
	public String toString() {
		return Extract.classNameOf(this) + (node != null ? " of " + node.toString() : "(without node)");
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	private static interface Query<T> {
		Set<T> set();
	}

}
