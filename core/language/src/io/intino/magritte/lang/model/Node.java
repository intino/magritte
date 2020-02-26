package io.intino.magritte.lang.model;

import java.util.Collections;
import java.util.List;

public interface Node extends Parametrized, NodeContainer {

	String ANONYMOUS = "anonymous@";

	String name();

	void name(String name);

	String type();

	String qualifiedName();

	Node container();

	boolean isSub();

	boolean isAspect();

	boolean isMetaAspect();

	default List<AspectConstraint> aspectConstraints() {
		return Collections.emptyList();
	}

	List<Aspect> appliedAspects();

	boolean is(Tag tag);

	boolean into(Tag tag);

	boolean isAbstract();

	boolean isTerminal();

	List<Tag> annotations();

	List<Tag> flags();

	void addAnnotations(Tag... annotations);

	void addFlags(Tag... flags);

	Node parent();

	String parentName();

	boolean isAnonymous();

	List<String> types();

	List<String> secondaryTypes();

	void type(String type);

	void stashNodeName(String name);

	default List<String> metaTypes() {
		return Collections.emptyList();
	}

	default void metaTypes(List<String> types) {

	}

	Node resolve();

	boolean isReference();

	List<Variable> variables();

	Node destinyOfReference();

	List<Node> children();

	List<Node> subs();

	default void addUses(List<String> imports) {
	}

	default <T extends Node> void addChild(T node) {
	}

	default void applyAspects(Aspect... aspects) {
	}

	@Override
	String toString();

	@Override
	boolean equals(Object obj);

	@Override
	int hashCode();

	interface AspectConstraint {
		String name();

		Node node();
	}
}