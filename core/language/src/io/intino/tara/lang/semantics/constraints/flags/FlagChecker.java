package io.intino.tara.lang.semantics.constraints.flags;

import io.intino.tara.lang.semantics.errorcollector.SemanticException;
import io.intino.tara.lang.model.Node;

public interface FlagChecker {
	void check(Node node) throws SemanticException;
}
