package tara.lang.semantics.constraints.flags;

import tara.lang.semantics.SemanticException;
import tara.lang.model.Node;

public interface AnnotationChecker {
	void check(Node node) throws SemanticException;
}
