package tara.lang.semantics.constraints.flags;

import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.model.Node;

public interface AnnotationChecker {
	void check(Node node) throws SemanticException;
}
