package siani.tara.semantic.constraints.flags;

import siani.tara.semantic.SemanticError;
import siani.tara.semantic.SemanticException;
import siani.tara.semantic.model.Node;

public class FeatureChecker implements AnnotationChecker {
	@Override
	public void check(Node node) throws SemanticException {
		for (String flag : node.flags())
			if (flag.equals("component") || flag.equals("aggregated") || flag.equals("associated")
				|| flag.equals("composable") || flag.equals("aggregable") || flag.equals("associable")
				|| flag.equals("property") || flag.equals("multiple"))
				throw new SemanticException(new SemanticError("reject.flag", node, new String[]{flag, node.type()}));
	}
}
