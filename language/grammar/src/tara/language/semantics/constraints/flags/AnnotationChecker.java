package tara.language.semantics.constraints.flags;

import tara.language.semantics.SemanticException;
import tara.language.model.Node;

public interface AnnotationChecker {
	void check(Node node) throws SemanticException;
}
