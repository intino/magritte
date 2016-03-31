package tara.lang.model;

import tara.lang.model.rules.Size;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Variable extends Valued, Refactorizable, Cloneable {

	String name();

	Primitive type();

	List<Tag> flags();

	boolean isReference();

	boolean isMultiple();

	Rule rule();

	Size size();

	void size(Size size);

	void rule(Rule rule);

	boolean isOverriden();

	void name(String name);

	NodeContainer container();

	Node destinyOfReference();

	default void container(NodeContainer container) {
	}

	void type(Primitive type);

	void addFlags(Tag... flags);

	boolean isTerminal();

	boolean isTerminalInstance();

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
		private static Map<NodeContainer, Integer> map = new HashMap<>();

		public static int next(NodeContainer container) {
			map.put(container, map.containsKey(container) ? map.get(container) + 1 : 0);
			return map.get(container);
		}
	}
}
