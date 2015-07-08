package magritte.handlers;

import magritte.NativeCode;
import magritte.Node;
import magritte.Set;

import static magritte.Node.Member.Component;
import static magritte.Tag.Case;
import static magritte.Tag.Link.Class;
import static magritte.Tag.Link.*;
import static magritte.handlers.NativeCodeHandler.init;

public class NodeCloner {
	private Node node;

	public NodeCloner(Node node) {
		this.node = node;
	}

	public NodeCloner with(Node prototype) {
		node.set(prototype.tags());
		node.link(prototype).as(Class);
		node.set(Case);
		cloneVariables(prototype);
		cloneComponents(prototype.members(Component));
		return this;
	}

	private void cloneVariables(Node prototype) {
		for (String var : prototype.vars()) {
			if (var.startsWith("-")) continue;
			node.set(var, produceVariable(prototype.get(var)));
		}
	}

	private void cloneComponents(Set<Node> members) {
		members.forEach(member -> new NodeCloner(createComponent()).with(member));
	}

	private Node createComponent() {
		Node member = this.node.graph().createNode();
		member.link(node).as(Owner);
		node.link(member).as(OwnedMember);
		return member;
	}

	private Object produceVariable(Object o) {
		return o instanceof NativeCode ? init(nativeCode(o)).on(node) : o;
	}

	private NativeCode nativeCode(Object o) {
		return (NativeCode) o;
	}

	public Node node() {
		return node;
	}

}
