package tara.compiler.semantic;

import tara.compiler.model.Model;
import tara.lang.model.Node;
import tara.lang.semantics.SemanticError;
import tara.lang.semantics.SemanticException;

import static java.util.Collections.singletonList;

public class AnchorChecker {


	public AnchorChecker() {
	}

	public void check(Node node) throws SemanticException {
		if (node == null) return;
		if (!node.isReference() && !(node instanceof Model) && (node.anchor() == null || node.anchor().isEmpty()))
			throw new SemanticException(new SemanticError("required.anchor", node, singletonList(node.type())));
	}
}
