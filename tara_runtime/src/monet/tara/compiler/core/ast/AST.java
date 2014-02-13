package monet.tara.compiler.core.ast;

import java.util.HashMap;

public class AST extends HashMap<String, ASTNode> {

	public ASTNode findNodeByFullName(String name) {
		String[] tree = name.split("\\.");
		if (this.get(tree[0]) == null) return null;
		ASTNode node = this.get(tree[0]);
		for (int i = 1; i < tree.length; i++)
			if (node != null)
				node = node.getChildByName(tree[i]);
		return node;
	}
}
