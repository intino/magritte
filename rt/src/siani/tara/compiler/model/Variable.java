package siani.tara.compiler.model;

import siani.tara.semantic.model.Tag;

import java.util.Collection;
import java.util.List;

public interface Variable extends Cloneable {

	String NATIVE_SEPARATOR = "#";
	String WORD = "word";

	String getName();

	NodeContainer getContainer();

	void setContainer(NodeContainer container);

	String getType();

	boolean isMultiple();

	int getTupleSize();

	String getContract();

	Collection<Tag> getFlags();

	void addFlags(String... flags);

	boolean isTerminal();

	boolean isFinal();

	boolean isPrivate();

	boolean isInherited();

	void setOverriden(boolean overriden);

	boolean isOverriden();

	void setContract(String extension);

	void setName(String name);

	void setType(String type);

	void setMultiple(boolean multiple);

	void setTupleSize(int tupleSize);

	List<Object> getAllowedValues();

	void addAllowedValues(Object... values);

	List<Object> getDefaultValues();

	void addDefaultValues(Object... values);

	String getDefaultExtension();

	void setDefaultExtension(String defaultExtension);

	Variable cloneIt(NodeContainer container);


}
