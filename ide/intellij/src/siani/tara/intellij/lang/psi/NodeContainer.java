package siani.tara.intellij.lang.psi;

import java.util.List;

public interface NodeContainer extends TaraPsiElement {

	Node getContainer();

	List<Node> getIncludes();

	List<Variable> getVariables();

	String getQualifiedName();
}
