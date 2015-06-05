package siani.tara.compiler.model;

import siani.tara.semantic.model.Tag;

import java.util.Collection;
import java.util.List;

public interface Variable extends Cloneable {

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

	Variable cloneIt(NodeContainer container);


}
