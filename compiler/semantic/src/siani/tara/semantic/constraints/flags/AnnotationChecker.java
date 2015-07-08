package siani.tara.semantic.constraints.flags;

import siani.tara.semantic.SemanticException;
import siani.tara.semantic.model.Node;

public interface AnnotationChecker {
	void check(Node node) throws SemanticException;
}
