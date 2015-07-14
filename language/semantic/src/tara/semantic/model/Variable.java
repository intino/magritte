package tara.semantic.model;

import java.util.List;

public interface Variable extends Element {

	String NATIVE_SEPARATOR = "#";
	String WORD = "word";

	String name();

	String type();

	String contract();

	List<Tag> flags();

	boolean isReference();

	boolean isMultiple();

	int getSize();

	boolean isOverriden();

	List<Object> defaultValue();

	void name(String name);

	NodeContainer container();

	void container(NodeContainer container);

	void type(String type);

	int size();

	void size(int tupleSize);

	void contract(String extension);

	void addFlags(Tag... flags);

	boolean isTerminal();

	boolean isTerminalInstance();

	boolean isFinal();

	boolean isPrivate();

	boolean isInherited();

	void overriden(boolean overriden);

	List<Object> allowedValues();

	void addAllowedValues(Object... values);

	List<Object> getDefaultValues();

	void addDefaultValues(Object... values);

	String defaultExtension();

	void defaultExtension(String defaultExtension);

	String getUID();

	Variable cloneIt(NodeContainer container);
}
