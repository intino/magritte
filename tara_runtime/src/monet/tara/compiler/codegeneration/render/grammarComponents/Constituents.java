package monet.tara.compiler.codegeneration.render.grammarcomponents;


import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.ast.ASTNode;

public class Constituents {
	private AST root;
	private ASTNode node;

	public Constituents(AST root, ASTNode node) {
		this.root = root;
		this.node = node;
	}

	public String getConstituentString() {
		String constituent = getStringConstituents(root.searchAncestry(node), "");
		if (node.isMorph()) constituent = getStringConstituentsPolymorphic(node.getParent(), constituent);
		constituent = getStringConstituents(node, constituent);
		return constituent.substring(constituent.indexOf('|') + 1);
	}

	private String getStringConstituents(ASTNode node, String constituents) {
		String result = null;
		if (node != null) {
			for (ASTNode child : node.getChildren())
				if (!child.isAbstract()) result = evaluateStringFormatConstituent(child, constituents);
		}
		return result;
	}

	private String getStringConstituentsPolymorphic(ASTNode node, String constituents) {
		String result = null;
		if (node != null)
			for (ASTNode child : node.getChildren())
				if (!child.isAbstract() && !child.isMorph() || contains(this.node.getChildren(), node))
					result = evaluateStringFormatConstituent(child, constituents);
		return result;
	}

	private boolean contains(ASTNode[] nodes, ASTNode node) {
		for (ASTNode child : nodes) {
			ASTNode trulyNode = ("".equals(child.getIdentifier())) ? root.searchAncestry(child) : child;
			if (trulyNode.equals(node)) return true;
		}
		return false;
	}

	private String evaluateStringFormatConstituent(ASTNode node, String constituents) {
		String result = null;
		if (node.isPolymorphic()) {
			for (ASTNode morphChild : node.getMorphs())
				if (!morphChild.isAbstract()) result = evaluateFormat(morphChild, constituents);
		} else result = evaluateFormat(node, constituents);
		return result;
	}

	private String evaluateFormat(ASTNode node, String constituents) {
		String result = constituents;
		ASTNode nodeAncestry = root.searchAncestry(node);
		if (nodeAncestry != null && !nodeAncestry.isPolymorphic())
			result += " | " + ((!"".equals(node.getIdentifier())) ?
				getTransformedAbsolutePath(node) : getTransformedAbsolutePath(nodeAncestry));
		else result += ("".equals(node.getIdentifier())) ?
			"" : " | " + getTransformedAbsolutePath(node);
		return result;
	}

	private String getTransformedAbsolutePath(ASTNode node) {
		return node.getAbsolutePath().toLowerCase().replaceAll("\\.", "_");
	}
}
