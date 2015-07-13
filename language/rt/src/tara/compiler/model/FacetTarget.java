package tara.compiler.model;

import java.util.List;

public interface FacetTarget extends Element, NodeContainer {

	String getTarget();

	List<String> getConstraints();

	void setTarget(String destiny);

	void setConstraint(List<String> constraints);

	Node getTargetNode();

	void setTargetNode(Node destiny);
}
