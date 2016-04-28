package tara.lang.model;

import tara.lang.model.rules.Size;
import tara.lang.model.rules.variable.VariableRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Variable extends Valued, Refactorizable, Cloneable {

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

	default Variable cloneIt(Node container) {
		return null;
	}

	class NativeCounter {
		private static Map<String, Integer> map = new HashMap<>();

		public static int next(Node container, String name) {
			final String key = calculatePackage(container) + "." + name;
			map.put(key, map.containsKey(key) ? map.get(key) + 1 : 0);
			return map.get(key);
		}

		private static String calculatePackage(Node container) {
			final Node node = firstNamedContainer(container);
			return node == null ? "" : node.cleanQn().replace("$", ".").replace("#", ".").toLowerCase();
		}

		private static Node firstNamedContainer(Node container) {
			Node candidate = container;
			while (candidate != null && !(candidate instanceof NodeRoot)) if (candidate.isAnonymous()) return candidate;
			else candidate = candidate.container();
			return container;
		}

	}


}
