package tara.magritte.handlers;

import tara.magritte.NativeCode;
import tara.magritte.Node;
import tara.magritte.Set;
import tara.magritte.Tag;

public class NodeCloner {
    private Node node;

    public NodeCloner(Node node) {
        this.node = node;
    }

    public NodeCloner with(Node prototype) {
        node.set(prototype.tags());
        node.link(prototype).as(Tag.Link.Class);
        node.set(Tag.Case);
        cloneVariables(prototype);
        cloneComponents(prototype.members(Node.Member.Component));
        return this;
    }

    private void cloneVariables(Node prototype) {
        for (String var : prototype.vars()) {
            if (var.startsWith("-")) continue;
            node.set(var, produceVariable(prototype.get(var)));
        }
    }

    private void cloneComponents(Set<Node> members) {
        members.forEach(member->new NodeCloner(createComponent()).with(member));
    }

    private Node createComponent() {
        Node member = this.node.graph().createNode();
        member.link(node).as(Tag.Link.Owner);
        node.link(member).as(Tag.Link.OwnedMember);
        return member;
    }

    private Object produceVariable(Object o) {
        return o instanceof NativeCode ? NativeCodeHandler.init(nativeCode(o)).on(node) : o;
    }

    private NativeCode nativeCode(Object o) {
        return (NativeCode) o;
    }

    public Node node() {
        return node;
    }

}
