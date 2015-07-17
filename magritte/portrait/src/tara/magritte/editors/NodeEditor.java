package tara.magritte.editors;

import tara.magritte.NativeCode;
import tara.magritte.Node;
import tara.magritte.Set;
import tara.magritte.Tag;
import tara.magritte.handlers.NodeProducer;
import tara.magritte.schema.ListSet;

import static tara.magritte.helpers.Selection.instancesOf;

public class NodeEditor extends VariableEditor {

    public NodeEditor(Node node) {
        super(node);
    }

    public NodeEditor parent(String parent) {
        return parent($(parent));
    }

    public NodeEditor parent(Node parent) {
        if (parent == null) return this;
        node.link(parent).as(Tag.Link.Parent);
        parent.link(node).as(Tag.Link.Child);
        return this;
    }

    public NodeEditor type(String type) {
        Node node = override(type);
        return node != null ? new NodeEditor(node) : type($(type));
    }

    private Node override(String type) {
        if (node.owner() == null || !node.is(Tag.Case)) return null;
        return override(node.owner().members(Node.Member.Component).filter(instancesOf(type)));
    }

    private Node override(Set<Node> candidates) {
        return candidates.size() == 1 ? override(candidates.get(0)) : null;
    }

    private Node override(Node candidate) {
        if (candidate.is(Tag.Edited)) return null;
        deleteFrom(node.owner());
        candidate.set(Tag.Edited);
        return candidate;
    }

    private void deleteFrom(Node owner) {
        node.unlink(owner).as(Tag.Link.Owner);
        owner.unlink(node).as(Tag.Link.OwnedMember);
    }


    public NodeEditor type(Node type) {
        if (type == null) return this;
        node.link(type).as(Tag.Link.Class);
        node.set(Tag.Edited);
        return node.is(Tag.Case) ? produceWith(type) : initWith(type);
    }

    private NodeEditor initWith(Node type) {
        for (String var : type.vars()) {
            if (!var.startsWith("*")) continue;
            node.set(var.substring(1), produceVariable(type.get(var)));
        }
        return this;
    }

    private Object produceVariable(Object o) {
        return o instanceof NativeCode ? produceNativeCode((NativeCode) o) : o;
    }

    private Object produceNativeCode(NativeCode o) {
        try {
            NativeCode clone = (NativeCode) o.clone();
            clone._target(node.root());
            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    private NodeEditor produceWith(Node type) {
        NodeProducer.produce(node).with(type);
        return this;
    }

    public NodeEditor has(String... components) {
        return has(ListSet.cast(components).map(this::$));
    }

    public NodeEditor has(Node... components) {
        return has(ListSet.cast(components));
    }

    public NodeEditor has(Set<Node> components) {
        return linkAsMember(components, Tag.Link.OwnedMember);
    }

    public NodeEditor remove(Node... components) {
        return remove(ListSet.cast(components));
    }

    public NodeEditor remove(Set<Node> components) {
        return unlinkAsMember(components, Tag.Link.OwnedMember);
    }

    public NodeEditor requires(String... members) {
        return requires(ListSet.cast(members).map(this::$));
    }

    public NodeEditor requires(Node... members) {
        return requires(ListSet.cast(members));
    }

    public NodeEditor requires(Set<Node> members) {
        return linkAsMember(members, Tag.Link.RequiredMember);
    }

    public NodeEditor allows(String... members) {
        return allows(ListSet.cast(members).map(this::$));
    }

    public NodeEditor allows(Node... members) {
        return allows(ListSet.cast(members));
    }

    public NodeEditor allows(Set<Node> members) {
        return linkAsMember(members, Tag.Link.OptionalMember);
    }

    public NodeEditor holds(String... members) {
        return holds(ListSet.cast(members).map(this::$));
    }

    public NodeEditor holds(Node... members) {
        return holds(ListSet.cast(members));
    }

    public NodeEditor holds(Set<Node> members) {
        return linkAsMember(members, Tag.Link.AggregableMember);
    }

    public NodeEditor fanOut(Node... references) {
        for (Node reference : references) node.link(reference).as(Tag.Link.FanOut);
        return this;
    }

    public NodeEditor fanIn(Node... referees) {
        for (Node referee : referees)
            node.link(referee).as(Tag.Link.FanIn);
        return this;
    }

    private NodeEditor linkAsMember(Set<Node> nodes, Tag.Link link) {
        nodes.forEach(item -> linkAsMember(item, link));
        return this;
    }

    private NodeEditor unlinkAsMember(Set<Node> nodes, Tag.Link link) {
        nodes.forEach(item -> unlinkAsMember(item, link));
        return this;
    }

    private NodeEditor linkAsMember(Node node, Tag.Link link) {
        this.node.link(node).as(link);
        node.link(this.node).as(Tag.Link.Owner);
        return this;
    }

    private NodeEditor unlinkAsMember(Node node, Tag.Link link) {
        this.node.unlink(node).as(link);
        node.unlink(this.node).as(Tag.Link.Owner);
        return this;
    }

    public NodeEditor set(Tag... tags) {
        node.set(tags);
        graph().index(node);
        return this;
    }

    private Node $(String name) {
        Node node = graph().exists(name) ? graph().get(name) : createNode();
        if (node.type() == null) node.set(name);
        return node;
    }

    protected Node createNode() {
        return graph().createNode();
    }

}
