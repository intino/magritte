package siani.tara.semantic.constraints.flags;

import siani.tara.semantic.SemanticError;
import siani.tara.semantic.SemanticException;
import siani.tara.semantic.model.Node;

import java.util.Arrays;

public class PropertyChecker implements AnnotationChecker {
	@Override
	public void check(Node node) throws SemanticException {
		for (String flag : node.flags()) {
			if (flag.equals("component") || flag.equals("aggregated") || flag.equals("associated")
				|| flag.equals("composable") || flag.equals("aggregable") || flag.equals("associable")
				|| flag.equals("feature") || flag.equals("case"))
				throw new SemanticException(new SemanticError("reject.flag", node, Arrays.asList(flag, node.type())));
		}
	}
}
