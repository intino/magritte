package tara.lang.model;

import tara.lang.model.rules.Size;

import java.util.List;

public interface Variable extends Element, Cloneable {

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

	List<Object> defaultValues();

	default void setDefaultValues(List<Object> values) {
	}

	String defaultMetric();

	void defaultMetric(String defaultExtension);

	String getUID();

	default Variable cloneIt(NodeContainer container) {
		return null;
	}

	class NativeCounter {
		static int count = 0;

		public static int next() {
			return count++;
		}
	}
}
