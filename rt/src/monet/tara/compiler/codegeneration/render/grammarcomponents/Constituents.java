package monet.tara.compiler.codegeneration.render.grammarcomponents;


import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.ast.ASTWrapper;
import monet.tara.compiler.core.ast.ASTNode;

public class Constituents {
	private ASTWrapper root;
	private ASTNode node;

	public Constituents(ASTWrapper root, ASTNode node) {
		this.root = root;
		this.node = node;
	}

	public String getConstituentString() {
		String constituent = getStringConstituents(root.searchAncestry(node), "");
		if (node.isMorph()) constituent = getStringConstituentsPolymorphic(node.getParent(), constituent);
		constituent = getStringConstituents(node, constituent);
		return constituent.substring(constituent.indexOf('|') + 1);
	}

	private String getTransformedAbsolutePath(ASTNode node) {
		return node.getAbsolutePath().toLowerCase().replaceAll("\\.", "_");
	}

	private String getStringConstituentsPolymorphic(ASTNode node, String constituents) {
		if (node != null) {
			for (ASTNode child : node.getChildren())
				if (!child.isAbstract() && !child.isMorph() || contains(this.node.getChildren(), node))
					constituents = evaluateStringFormatConstituent(child, constituents);
		}
		return constituents;
	}

	private String getStringConstituents(ASTNode node, String constituents) {
		if (node != null) {
			for (ASTNode child : node.getChildren())
				if (!child.isAbstract()) constituents = evaluateStringFormatConstituent(child, constituents);
		}
		return constituents;
	}

	private String evaluateStringFormatConstituent(ASTNode node, String constituents) {
		String result = constituents;
		if (node.isPolymorphic()) {
			for (ASTNode morphChild : node.getMorphs())
				if (!morphChild.isAbstract()) result = evaluateFormat(morphChild, result);
		} else result = evaluateFormat(node, result);
		return result;
	}

	private String evaluateFormat(ASTNode node, String constituents) {
		ASTNode nodeAncestry = root.searchAncestry(node);
		if (nodeAncestry != null && !nodeAncestry.isPolymorphic())
			constituents += " | " + ((!"".equals(node.getIdentifier())) ?
				getTransformedAbsolutePath(node) : getTransformedAbsolutePath(nodeAncestry));
		else constituents += ("".equals(node.getIdentifier())) ?
			"" : " | " + getTransformedAbsolutePath(node);
		return constituents;
	}

	private boolean contains(AST nodes, ASTNode node) {
		for (ASTNode child : nodes) {
			ASTNode trulyNode = ("".equals(child.getIdentifier())) ? root.searchAncestry(child) : child;
			if (trulyNode.equals(node)) return true;
		}
		return false;
	}
}
