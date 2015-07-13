package tara.semantic.constraints.flags;

import tara.semantic.SemanticException;
import tara.semantic.model.Node;

public interface AnnotationChecker {
	void check(Node node) throws SemanticException;
}
