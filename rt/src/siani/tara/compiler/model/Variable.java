package siani.tara.compiler.model;

import siani.tara.semantic.model.Tag;

import java.util.Collection;

public interface Variable  extends Cloneable {

	String NATIVE_SEPARATOR = "#";
	String WORD = "word";

	String getName();

	NodeContainer getContainer();

	void setContainer(NodeContainer container);

	String getType();

	boolean isMultiple();

	String getContract();

	Collection<Tag> getFlags();

	void addFlags(String... flags);

	boolean isTerminal();

	boolean isReadOnly();

	boolean isInherited();

	void setContract(String extension);

	void setName(String name);

	void setType(String type);

	void setMultiple(boolean multiple);

	Collection<Object> getAllowedValues();

	void addAllowedValues(Object... values);

	Collection<Object> getDefaultValues();

	void addDefaultValues(Object... values);

	String getDefaultExtension();

	void setDefaultExtension(String defaultExtension);

	Variable cloneIt(NodeContainer container);
}
