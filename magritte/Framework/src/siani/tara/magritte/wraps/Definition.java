package siani.tara.magritte.wraps;

import siani.tara.magritte.*;
import siani.tara.magritte.editors.NodeEditor;
import siani.tara.magritte.editors.VariableAddEditor;
import siani.tara.magritte.editors.VariableEditor;
import siani.tara.magritte.editors.VariableRemoveEditor;
import siani.tara.magritte.handlers.Casting;
import siani.tara.magritte.helpers.Extract;
import siani.tara.magritte.helpers.Selection;
import siani.tara.magritte.schema.ListSet;
import siani.tara.magritte.wraps.variables.Assignable;
import siani.tara.magritte.wraps.variables.Multiple;
import siani.tara.magritte.wraps.variables.Single;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

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
        return Casting.cast(node.owner(), scope).as(Definition.class);
    }

    public Set<Type> _aggregables(Class<? extends NodeWrap> wrapClass) {
        Set<Node> members = ListSet.cast(members(node, Node.Member.Aggregable)).filter(Selection.instancesOf(Extract.nameOf(wrapClass)));
        return Casting.cast(members, scope).as(Type.class);
    }

    protected NodeEditor _edit() {
        return new NodeEditor(scope);
    }

    protected VariableEditor _edit(Operation operation) {
        return operation == Operation.Add ? new VariableAddEditor(node) : operation == Operation.Remove ? new VariableRemoveEditor(node) : _edit();
    }


    public <T extends NodeWrap> T _component(Class<T> wrapClass) {
        return _components(wrapClass).get(0);
    }

    @SuppressWarnings("unchecked")
    public <T extends NodeWrap> Set<T> _components(Class<T> wrapClass) {
        return Casting.cast(node.members(Node.Member.Component).filter(Selection.instancesOf(Extract.nameOf(wrapClass))), scope).as(wrapClass);
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

    private List<Node> members(Node type, Node.Member member) {
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
        if (!node.is(Tag.Abstract)) result.add(node);
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
        return (this.getClass() != Definition.class ? Extract.classNameOf(this) : "") + node.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
