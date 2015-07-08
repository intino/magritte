package magritte.handlers;

import magritte.NativeCode;
import magritte.Node;

import static magritte.Node.Member.Component;
import static magritte.Node.Member.Required;
import static magritte.Tag.*;
import static magritte.Tag.Link.Class;
import static magritte.Tag.Link.*;
import static magritte.handlers.NativeCodeHandler.init;

public class NodeProducer {

	private final Node node;

	private NodeProducer(Node node) {
		this.node = node;
	}

	public static NodeProducer produce(Node node) {
		return new NodeProducer(node);
	}

	public Node node() {
		return node;
	}

	public NodeProducer with(Node type) {
		node.set(Case);
		node.set(type.is(Main) ? Root : None);
		node.link(type).as(Class);
		produceVariables(type);
		produceComponents(type);
		return this;
	}

	private void produceComponents(Node type) {
		if (type == null) return;
		produceComponents(type.parent());
		type.members(Required).forEach(this::produceComponent);
		type.members(Component).filter(member -> member.is(Prototype) && !member.is(Abstract)).forEach(this::cloneComponent);
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
		return o instanceof NativeCode ? init(nativeCode(o)).on(node) : o;
	}

	private Node cloneComponent(Node prototype) {
		return new NodeCloner(createComponent()).with(prototype).node();
	}

	private Node createComponent() {
		Node member = this.node.graph().createNode();
		member.link(node).as(Owner);
		node.link(member).as(OwnedMember);
		return member;
	}

	private NativeCode nativeCode(Object o) {
		return (NativeCode) o;
	}


}
