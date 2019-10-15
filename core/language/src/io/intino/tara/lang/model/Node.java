package io.intino.tara.lang.model;

import java.util.Collections;
import java.util.List;

public interface Node extends Parametrized, NodeContainer {

	String ANONYMOUS = "anonymous@";

	String name();

	void name(String name);

	default String layerName() {
		String name = name();
		name = name.isEmpty() ? "" : name.substring(0, 1).toUpperCase() + name.substring(1);
		return isAspect() ? name + "Aspect" : name;
	}

	String qualifiedName();

	String layerQualifiedName();

	Node container();

	boolean isSub();

	List<Node> subs();

	boolean isAspect();

	default List<AspectConstraint> aspectConstraints() {
		return Collections.emptyList();
	}

	boolean is(Tag tag);

	boolean into(Tag tag);

	boolean isAbstract();

	boolean isTerminal();

	List<Tag> annotations();

	List<Tag> flags();

	void addAnnotations(Tag... annotations);

	void addFlags(List<Tag> flags);

	void addFlag(Tag flags);

	Node parent();

	String parentName();

	boolean isAnonymous();

	default String simpleType() {
		return type();
	}

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

	List<Node> siblings();

	List<Variable> variables();

	List<Node> referenceComponents();

	Node destinyOfReference();

	List<Node> children();

	default void addUses(List<String> imports) {
	}

	default <T extends Node> void addChild(T node) {
	}

	List<Aspect> appliedAspects();

	default void applyAspects(Aspect... aspects) {
	}

	default void applyAspect(String aspectType) {
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