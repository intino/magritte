package siani.tara.compiler.model;

public interface FacetTarget extends NodeContainer {

	String getTarget();

	void setTarget(String destiny);

	Node getTargetNode();

	void setTargetNode(Node destiny);
}