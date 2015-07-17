package tara.compiler.model;

import java.util.Collection;
import java.util.List;

public interface Variable extends Element, Cloneable {

	String NATIVE_SEPARATOR = "#";
	String WORD = "word";

	String getName();

	void setName(String name);

	NodeContainer getContainer();

	void setContainer(NodeContainer container);

	String getType();

	void setType(String type);

	boolean isMultiple();

	void setMultiple(boolean multiple);

	int getTupleSize();

	void setTupleSize(int tupleSize);

	String getContract();

	void setContract(String extension);

	Collection<Tag> getFlags();

	void addFlags(String... flags);

	boolean isTerminal();

	boolean isTerminalInstance();

	boolean isFinal();

	boolean isPrivate();

	boolean isInherited();

	boolean isOverriden();

	void setOverriden(boolean overriden);

	List<Object> getAllowedValues();

	void addAllowedValues(Object... values);

	List<Object> getDefaultValues();

	void addDefaultValues(Object... values);

	String getDefaultExtension();

	void setDefaultExtension(String defaultExtension);

	String getUID();

	Variable cloneIt(NodeContainer container);

}