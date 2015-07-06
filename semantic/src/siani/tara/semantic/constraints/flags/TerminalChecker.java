package siani.tara.semantic.constraints.flags;

import siani.tara.semantic.SemanticError;
import siani.tara.semantic.SemanticException;
import siani.tara.semantic.model.Node;

import java.util.Arrays;

public class TerminalChecker implements AnnotationChecker {
	@Override
	public void check(Node node) throws SemanticException {
		for (String annotation : node.annotations()) {
			if ("facet".equals(annotation))
				throw new SemanticException(new SemanticError("reject.flag.combination", node, Arrays.asList(annotation, node.type())));
		}
	}
}
