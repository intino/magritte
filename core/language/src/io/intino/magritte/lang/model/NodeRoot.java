package io.intino.magritte.lang.model;

import java.io.File;
import java.util.Collections;
import java.util.List;

public interface NodeRoot extends Node, Element {
	@Override
	String toString();

	default File resourcesRoot() {
		return null;
	}

	@Override
	boolean equals(Object obj);

	@Override
	int hashCode();


	@Override
	default String name() {
		return "";
	}

	@Override
	default void name(String name) {

	}

	@Override
	default String qualifiedName() {
		return "";
	}

	@Override
	default Node container() {
		return null;
	}

	@Override
	default boolean isSub() {
		return false;
	}

	@Override
	default List<Node> subs() {
		return Collections.emptyList();
	}

	@Override
	default boolean isAspect() {
		return false;
	}

	@Override
	default boolean is(Tag tag) {
		return false;
	}

	@Override
	default boolean into(Tag tag) {
		return false;
	}

	@Override
	default boolean isAbstract() {
		return false;
	}

	@Override
	default boolean isTerminal() {
		return false;
	}

	@Override
	default List<Tag> annotations() {
		return Collections.emptyList();
	}

	@Override
	default List<Tag> flags() {
		return Collections.emptyList();
	}

	@Override
	default void addAnnotations(Tag... annotations) {

	}

	@Override
	default void addFlags(Tag... flags) {

	}

	@Override
	default Node parent() {
		return null;
	}

	@Override
	default String parentName() {
		return null;
	}

	@Override
	default boolean isAnonymous() {
		return false;
	}

	@Override
	default List<String> types() {
		return Collections.singletonList("");
	}

	@Override
	default List<String> secondaryTypes() {
		return Collections.emptyList();
	}

	@Override
	default void type(String type) {

	}

	@Override
	default void stashNodeName(String name) {

	}

	@Override
	default Node resolve() {
		return null;
	}

	@Override
	default boolean isReference() {
		return false;
	}

	@Override
	default List<Node> siblings() {
		return Collections.emptyList();
	}

	@Override
	default List<Variable> variables() {
		return Collections.emptyList();
	}

	@Override
	default List<Node> referenceComponents() {
		return Collections.emptyList();
	}

	@Override
	default Node destinyOfReference() {
		return null;
	}

	@Override
	default List<Node> children() {
		return Collections.emptyList();
	}

	@Override
	default List<Aspect> appliedAspects() {
		return Collections.emptyList();
	}

	@Override
	default List<Parameter> parameters() {
		return Collections.emptyList();
	}
}
