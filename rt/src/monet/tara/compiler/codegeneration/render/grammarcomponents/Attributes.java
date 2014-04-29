package monet.tara.compiler.codegeneration.render.grammarcomponents;

import monet.tara.lang.ASTWrapper;
import monet.tara.lang.ASTNode;

import java.util.List;

public abstract class Attributes {

	private String attributesString;

	protected Attributes(ASTNode node, ASTWrapper root) {
		this.attributesString = getParentAttributes(node, root) + getAttributes(node);
	}

	public static String getStringOfWordList(List<String> wordTypes) {
		String list = "(";
		for (String word : wordTypes) {
			if (wordTypes.indexOf(word) != wordTypes.size() - 1) list += setAttributeWithEndingToken(word, "| ");
			else list += setAttributeWithEndingToken(word, ")");
		}
		return list;
	}

	private static String setAttributeWithEndingToken(String word, String token) {
		return (!word.contains(".")) ? word.toUpperCase() + "_WORD" + token
			: word.substring(word.lastIndexOf('.') + 1).toUpperCase() + "_WORD" + token;
	}

	protected abstract String stringByEvaluationParentType(ASTNode elementType, ASTNode extendType, int i);

	protected abstract String stringByEvaluationType(ASTNode elementType, int i);

	protected String getParentAttributes(ASTNode node, ASTWrapper root) {
		String attributes = "";
		ASTNode extendNode = root.searchAncestry(node);
		if (extendNode != null)
			for (int index = 0; index < extendNode.getVariables().size(); index++)
				attributes += stringByEvaluationParentType(node, extendNode, index);
		if (node.isCase()) {
			extendNode = node.getParent();
			if (extendNode != null)
				for (int index = 0; index < extendNode.getVariables().size(); index++)
					attributes += stringByEvaluationParentType(node, extendNode, index);

		}
		return attributes;
	}

	protected String getAttributes(ASTNode node) {
		String attributes = "";
		for (int index = 0; index < node.getVariables().size(); index++)
			attributes += stringByEvaluationType(node, index);
		return attributes;
	}

	public String getAttributesString() {
		return attributesString;
	}
}