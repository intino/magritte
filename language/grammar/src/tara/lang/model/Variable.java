package tara.lang.model;

import tara.lang.model.rules.Size;
import tara.lang.model.rules.variable.VariableRule;

import java.util.ArrayList;
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

	NodeContainer container();

	Node destinyOfReference();

	default void container(NodeContainer container) {
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

	default Variable cloneIt(NodeContainer container) {
		return null;
	}

	class NativeCounter {
		private static Map<String, Integer> map = new HashMap<>();

		public static int next(Parametrized container, String name) {
			final String key = calculatePackage(container) + "." + name;
			map.put(key, map.containsKey(key) ? map.get(key) + 1 : 0);
			return map.get(key);
		}

		private static String calculatePackage(Parametrized container) {
			final Parametrized nodeContainer = firstNamedContainer(container);
			return nodeContainer == null ? "" : nodeContainer.qualifiedNameCleaned().replace("$", ".").replace("#", ".").toLowerCase();
		}

		private static Parametrized firstNamedContainer(Parametrized container) {
			List<Parametrized> containers = collectStructure(container);
			Node candidate = null;
			for (Parametrized paremetrized : containers) {
				if (paremetrized instanceof Node && !((Node) paremetrized).isAnonymous()) candidate = paremetrized;
				else if (paremetrized instanceof Node) break;
				else candidate = paremetrized;
			}
			return candidate;
		}

		private static List<Parametrized> collectStructure(Parametrized container) {
			List<Parametrized> containers = new ArrayList<>();
			Parametrized current = container;
			while (current != null && !(current instanceof NodeRoot)) {
				containers.add(0, current);
				current = current.container();
			}
			return containers;
		}

	}


}
