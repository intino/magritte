package tara.compiler.semantic;

import tara.compiler.model.Model;
import tara.lang.model.Node;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import static java.util.Collections.singletonList;
import static tara.lang.semantics.errorcollector.SemanticNotification.ERROR;

public class AnchorChecker {

	public AnchorChecker() {
	}

	public void check(Node node) throws SemanticException {
		if (node == null) return;
		if (!node.isTerminalInstance() && !node.isReference() && !(node instanceof Model) && (node.anchor() == null || node.anchor().isEmpty()))
			throw new SemanticException(new SemanticNotification(ERROR, "required.anchor", node, singletonList(node.type())));
	}
}
