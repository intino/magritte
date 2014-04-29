package monet.tara.compiler.codegeneration.render.grammarcomponents;


import monet.tara.lang.ASTNode;

public class EndingTokenSelector {

	public static final String referenceIdentifier = "referenceIdentifier";

	private EndingTokenSelector() {
	}

	public static String setAttributeStringWithEndingTokenImplicit(ASTNode.Attribute attribute, String token) {
		if (attribute.isList()) return attribute.getPrimitiveType().toLowerCase() + "List" + token;
		else return attribute.getPrimitiveType().toUpperCase() + "_VALUE_KEY" + token;
	}

	public static String setReferenceStringWithEndingTokenImplicit(ASTNode.Reference attribute, String token) {
		if (attribute.isList())
			return "LEFT_SQUARE identifier+ RIGHT_SQUARE" + token;
		else
			return referenceIdentifier + token;
	}

	public static String setWordStringWithEndingTokenImplicit(ASTNode.Word attribute, String token) {
		return Attributes.getStringOfWordList(attribute.getWordTypes()) + token;
	}

	public static String setAttributeStringWithEndingToken(ASTNode.Attribute attribute, String token) {
		if (attribute.isList())
			return attribute.getName().toUpperCase() + "_ATTRIBUTE COLON " + attribute.getPrimitiveType().toLowerCase() + "List" + token;
		else
			return attribute.getName().toUpperCase() + "_ATTRIBUTE COLON " + attribute.getPrimitiveType().toUpperCase() + "_VALUE_KEY" + token;
	}

	public static String setReferenceStringWithEndingToken(ASTNode.Reference attribute, String token) {
		if (attribute.isList())
			return attribute.getName().toUpperCase() + "_ATTRIBUTE COLON LEFT_SQUARE" + referenceIdentifier + "+ RIGHT_SQUARE" + token;
		else return attribute.getName().toUpperCase() + "_ATTRIBUTE COLON " + referenceIdentifier + token;
	}

	public static String setWordStringWithEndingToken(ASTNode.Word attribute, String token) {
		return attribute.getIdentifier().toUpperCase() + "_ATTRIBUTE COLON " + Attributes.getStringOfWordList(attribute.getWordTypes()) + token;
	}
}
