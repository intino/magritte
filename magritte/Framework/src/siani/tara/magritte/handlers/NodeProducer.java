package siani.tara.magritte.handlers;

import siani.tara.magritte.NativeCode;
import siani.tara.magritte.Node;
import siani.tara.magritte.Tag;

import static siani.tara.magritte.Node.Member.*;

public class NodeProducer {

    private final Node node;

    public static NodeProducer produce(Node node) {
        return new NodeProducer(node);
    }

    private NodeProducer(Node node) {
        this.node = node;
    }

    public Node node() {
        return node;
    }

    public NodeProducer with(Node type) {
        node.set(Tag.Case);
        node.set(type.is(Tag.Main) ? Tag.Root : Tag.None);
        node.link(type).as(Tag.Link.Class);
        produceVariables(type);
        produceComponents(type);
        return this;
    }

    private void produceComponents(Node type) {
        if (type == null) return;
        produceComponents(type.parent());
        type.members(Required).forEach(this::produceComponent);
        type.members(Component).filter(member -> member.is(Tag.Prototype) && !member.is(Tag.Abstract)).forEach(this::cloneComponent);
    }

    private void produceVariables(Node type) {
        if (type == null) return;
        produceVariables(type.parent());
        for (String var : type.vars()) {
            if (!var.startsWith("*")) continue;
            node.set(var.substring(1), produceVariable(type.get(var)));
        }
    }

    private Node produceComponent(Node type) {
        return new NodeProducer(createComponent()).with(type).node();
    }

    private Object produceVariable(Object o) {
        return o instanceof NativeCode ? NativeCodeHandler.init(nativeCode(o)).on(node) : o;
    }

    private Node cloneComponent(Node prototype) {
        return new NodeCloner(createComponent()).with(prototype).node();
    }

    private Node createComponent() {
        Node member = this.node.graph().createNode();
        member.link(node).as(Tag.Link.Owner);
        node.link(member).as(Tag.Link.OwnedMember);
        return member;
    }

    private NativeCode nativeCode(Object o) {
        return (NativeCode) o;
    }


}
