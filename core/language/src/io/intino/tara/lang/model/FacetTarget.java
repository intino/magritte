package io.intino.tara.lang.model;

import java.util.List;

public interface FacetTarget extends Element {

	String ANY = "any";

	Node owner();

	Node parent();

	boolean inherited();

	void inherited(boolean inherited);

	void parent(Node node);

	String target();

	Node targetNode();

	<T extends Node> void targetNode(T destiny);

	List<Constraint> constraints();

	void target(String destiny);

	void constraints(List<String> constraints);

	interface Constraint extends Cloneable {
		String name();

		Node node();

		void node(Node node);

		boolean negated();

		FacetTarget.Constraint clone() throws CloneNotSupportedException;


	}
}
