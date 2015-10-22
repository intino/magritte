package tara.language.model;

import java.util.List;

public interface Variable extends Element, Cloneable {

	String NATIVE_SEPARATOR = "#";

	String name();

	Primitive type();

	String contract();

	List<Tag> flags();

	boolean isReference();

	boolean isMultiple();

	int getSize();

	boolean isOverriden();

	void name(String name);

	NodeContainer container();

	Node destinyOfReference();

	default void container(NodeContainer container) {
	}

	void type(Primitive type);

	int size();

	void size(int tupleSize);

	void contract(String contract);

	void addFlags(Tag... flags);

	boolean isTerminal();

	boolean isTerminalInstance();

	boolean isFinal();

	boolean isPrivate();

	boolean isInherited();

	void overriden(boolean overriden);

	List<Object> allowedValues();

	default void addAllowedValues(Object... values) {
	}

	List<Object> defaultValues();

	default void addDefaultValues(Object... values) {
	}

	String defaultExtension();

	void defaultExtension(String defaultExtension);

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
