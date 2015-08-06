package tara.language.model;

import java.util.Collections;
import java.util.List;

public interface NodeRoot extends Node, NodeContainer, Element {
	@Override
	String toString();

	@Override
	boolean equals(Object obj);

	@Override
	int hashCode();

	@Override
	default String name() {
		return "";
	}

	@Override
	default String simpleType() {
		return "";
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
	default Node resolve() {
		return null;
	}

	@Override
	default boolean isReference() {
		return false;
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
	default List<Facet> facets() {
		return Collections.emptyList();
	}

	@Override
	default List<FacetTarget> facetTargets() {
		return Collections.emptyList();
	}

	@Override
	default boolean isSub() {
		return false;
	}

	@Override
	default boolean isMain() {
		return false;
	}

	@Override
	default List<Node> subs() {
		return Collections.emptyList();
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
	default boolean isFacet() {
		return false;
	}

	@Override
	default boolean isAbstract() {
		return false;
	}

	@Override
	default boolean isRequired() {
		return false;
	}

	@Override
	default boolean isSingle() {
		return false;
	}

	@Override
	default boolean isNamed() {
		return false;
	}

	@Override
	default boolean isFeature() {
		return false;
	}

	@Override
	default boolean isFeatureInstance() {
		return false;
	}

	@Override
	default boolean isFinal() {
		return false;
	}

	@Override
	default boolean isEnclosed() {
		return false;
	}

	@Override
	default boolean isPrototype() {
		return false;
	}

	@Override
	default boolean isTerminal() {
		return false;
	}

	@Override
	default boolean isTerminalInstance() {
		return false;
	}

	@Override
	default boolean intoSingle() {
		return false;
	}

	@Override
	default boolean intoRequired() {
		return false;
	}

	@Override
	default String plate() {
		return null;
	}

	@Override
	default void plate(String plate) {
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
	default List<Parameter> parameters() {
		return Collections.emptyList();
	}
}
