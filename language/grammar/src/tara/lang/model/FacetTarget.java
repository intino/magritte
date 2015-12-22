package tara.lang.model;

import java.util.List;

public interface FacetTarget extends Element {

	String ANY = "any";

	Node owner();

	Node parent();

	void parent(Node node);

	String target();

	Node targetNode();

	<T extends Node> void targetNode(T destiny);

	List<String> constraints();

	List<Node> constraintNodes();

	void target(String destiny);

	void constraints(List<String> constraints);

	void constraintNodes(List<Node> constraints);
}
