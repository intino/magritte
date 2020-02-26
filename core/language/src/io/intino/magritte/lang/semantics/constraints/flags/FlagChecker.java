package io.intino.magritte.lang.semantics.constraints.flags;

import io.intino.magritte.lang.model.Node;
import io.intino.magritte.lang.semantics.errorcollector.SemanticException;

public interface FlagChecker {
	void check(Node node) throws SemanticException;
}
