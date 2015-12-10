package tara.lang.semantics.constraints.flags;

import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.model.Node;

public interface FlagChecker {
	void check(Node node) throws SemanticException;
}
