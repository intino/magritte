package io.intino.tara.lang.model;

import io.intino.tara.Language;
import io.intino.tara.lang.model.rules.Size;
import io.intino.tara.lang.model.rules.variable.VariableRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Variable extends Valued, Cloneable {

	String name();

	Primitive type();

	List<Tag> flags();

	boolean isReference();

	boolean isMultiple();

	VariableRule rule();

	void rule(VariableRule rule);

	String scope();

	Size size();

	void size(Size size);

	boolean isOverriden();

	void name(String name);

	Node container();

	Node destinyOfReference();

	default void container(Node container) {
	}

	void type(Primitive type);

	void addFlags(Tag... flags);

	boolean isTerminal();

	boolean isFinal();

	boolean isPrivate();

	boolean isInherited();

	void overriden(boolean overriden);

	default void values(List<Object> values) {
	}

	String defaultMetric();

	void defaultMetric(String defaultExtension);

	String getUID();

	default Language language() {
		return nodeRoot().language();
	}

	default NodeRoot nodeRoot() {
		Node container = container();
		while (container != null && !(container instanceof NodeRoot))
			container = container.container();
		return (NodeRoot) container;
	}

	default Variable cloneIt(Node container) {
		return null;
	}

	class NativeCounter {
		private static Map<String, Integer> map = new HashMap<>();

		public synchronized static int next(NodeContainer container, String name) {
			final String key = calculatePackage(container) + "." + name;
			map.put(key, map.containsKey(key) ? map.get(key) + 1 : 0);
			return map.get(key);
		}

		private static String calculatePackage(NodeContainer container) {
			final Node nodeContainer = (Node) firstNamedContainer(container);
			return nodeContainer == null ? "" : nodeContainer.cleanQn().replace("$", ".").replace("#", ".").toLowerCase();
		}

		private static NodeContainer firstNamedContainer(NodeContainer container) {
			List<NodeContainer> containers = collectStructure(container);
			NodeContainer candidate = null;
			for (NodeContainer nodeContainer : containers) {
				if (nodeContainer instanceof Node && !((Node) nodeContainer).isAnonymous()) candidate = nodeContainer;
				else if (nodeContainer instanceof Node) break;
				else candidate = nodeContainer;
			}
			return candidate;
		}

		private static List<NodeContainer> collectStructure(NodeContainer container) {
			List<NodeContainer> containers = new ArrayList<>();
			NodeContainer current = container;
			while (current != null && !(current instanceof NodeRoot)) {
				containers.add(0, current);
				current = current.container();
			}
			return containers;
		}

	}


}
