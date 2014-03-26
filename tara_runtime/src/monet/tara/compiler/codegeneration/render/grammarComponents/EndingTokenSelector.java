package monet.tara.compiler.codegeneration.render.grammarcomponents;


import monet.tara.compiler.core.ast.ASTNode;

public class EndingTokenSelector {

	public static String setAttributeStringWithEndingTokenImplicit(ASTNode.Attribute attribute, String token) {
		if (attribute.isList()) return attribute.getPrimitiveType().toLowerCase() + "List" + token;
		else return attribute.getPrimitiveType().toUpperCase() + "_VALUE_KEY" + token;
	}

	public static String setReferenceStringWithEndingTokenImplicit(ASTNode.Reference attribute, String token) {
		if (attribute.isList())
			return "LEFT_SQUARE identifier+ RIGHT_SQUARE" + token;
		else
			return "IDENTIFIER_KEY" + token;
	}

	public static String setWordStringWithEndingTokenImplicit(ASTNode.Word attribute, String token) {
		return Attributes.getStringOfWordList(attribute.getWordTypes()) + token;
	}

	public static String setAttributeStringWithEndingToken(ASTNode.Attribute attribute, String token) {
		if (attribute.isList())
			return attribute.getName().toUpperCase() + "_ATTRIBUTE ASSIGN " + attribute.getPrimitiveType().toLowerCase() + "List" + token;
		else
			return attribute.getName().toUpperCase() + "_ATTRIBUTE ASSIGN " + attribute.getPrimitiveType().toUpperCase() + "_VALUE_KEY" + token;
	}

	public static String setReferenceStringWithEndingToken(ASTNode.Reference attribute, String token) {
		if (attribute.isList())
			return attribute.getName().toUpperCase() + "_ATTRIBUTE ASSIGN LEFT_SQUARE identifier+ RIGHT_SQUARE" + token;
		else return attribute.getName().toUpperCase() + "_ATTRIBUTE ASSIGN IDENTIFIER_KEY" + token;
	}

	public static String setWordStringWithEndingToken(ASTNode.Word attribute, String token) {
		return attribute.getIdentifier().toUpperCase() + "_ATTRIBUTE ASSIGN " + Attributes.getStringOfWordList(attribute.getWordTypes()) + token;
	}
}
