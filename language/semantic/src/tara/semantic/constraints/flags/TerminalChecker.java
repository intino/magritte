package tara.semantic.constraints.flags;

import tara.semantic.SemanticError;
import tara.semantic.SemanticException;
import tara.semantic.model.Node;
import tara.semantic.model.Tag;

import java.util.Arrays;

public class TerminalChecker implements AnnotationChecker {
	@Override
	public void check(Node node) throws SemanticException {
		for (Tag annotation : node.annotations()) {
			if (Tag.FACET.equals(annotation))
				throw new SemanticException(new SemanticError("reject.flag.combination", node, Arrays.asList(annotation, node.type())));
		}
	}
}
