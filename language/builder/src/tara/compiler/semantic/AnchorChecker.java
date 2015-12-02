package tara.compiler.semantic;

import tara.compiler.model.Model;
import tara.lang.model.Node;
import tara.lang.semantics.errorcollector.SemanticFatalException;
import tara.lang.semantics.errorcollector.SemanticNotification;

import static java.util.Collections.singletonList;
import static tara.lang.semantics.errorcollector.SemanticNotification.ERROR;

public class AnchorChecker {

	public AnchorChecker() {
	}

	public void check(Node node) throws SemanticFatalException {
		if (node == null) return;
		if (!node.isDeclaration() && !node.isReference() && !(node instanceof Model) && (node.anchor() == null || node.anchor().isEmpty()))
			throw new SemanticFatalException(new SemanticNotification(ERROR, "required.anchor", node, singletonList(node.type())));
	}
}
