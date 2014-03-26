package monet.tara.compiler.codegeneration.render.grammarcomponents.types;


import monet.tara.compiler.codegeneration.render.grammarcomponents.Attributes;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.ast.ASTNode;

public class ExplicitAttributes extends Attributes {

	public ExplicitAttributes(ASTNode node, AST root) {
		super(node, root);
	}

	@Override
	protected String stringByEvaluationType(ASTNode elementType, int index) {
		if (elementType.getVariables().get(index) instanceof ASTNode.Attribute) {
			ASTNode.Attribute attribute = (ASTNode.Attribute) elementType.getVariables().get(index);
			if (elementType.getVariables().size() - 1 == index)
				return setAttributeStringWithEndingToken(attribute, "");
			else
				return setAttributeStringWithEndingToken(attribute, "| ");
		}
		if (elementType.getVariables().get(index) instanceof ASTNode.Reference) {
			ASTNode.Reference attribute = (ASTNode.Reference) elementType.getVariables().get(index);
			if (elementType.getVariables().size() - 1 == index)
				return setReferenceStringWithEndingToken(attribute, "");
			else
				return setReferenceStringWithEndingToken(attribute, "| ");
		}
		if (elementType.getVariables().get(index) instanceof ASTNode.Word) {
			ASTNode.Word attribute = (ASTNode.Word) elementType.getVariables().get(index);
			if (elementType.getVariables().size() - 1 == index)
				return setWordStringWithEndingToken(attribute, "");
			else
				return setWordStringWithEndingToken(attribute, "| ");
		}
		return "";
	}

	@Override
	protected String stringByEvaluationParentType(ASTNode elementType, ASTNode extendType, int index) {
		if (extendType.getVariables().get(index) instanceof ASTNode.Attribute) {
			ASTNode.Attribute attribute = (ASTNode.Attribute) extendType.getVariables().get(index);
			if (elementType.getVariables().size() == 0 && extendType.getVariables().size() - 1 == index)
				return setAttributeStringWithEndingToken(attribute, "");
			else
				return setAttributeStringWithEndingToken(attribute, "| ");
		}
		if (extendType.getVariables().get(index) instanceof ASTNode.Reference) {
			ASTNode.Reference attribute = (ASTNode.Reference) extendType.getVariables().get(index);
			if (elementType.getVariables().size() == 0 && extendType.getVariables().size() - 1 == index)
				return setReferenceStringWithEndingToken(attribute, "");
			else
				return setReferenceStringWithEndingToken(attribute, "| ");
		}
		if (extendType.getVariables().get(index) instanceof ASTNode.Word) {
			ASTNode.Word attribute = (ASTNode.Word) extendType.getVariables().get(index);
			if (elementType.getVariables().size() == 0 && extendType.getVariables().size() - 1 == index)
				return setWordStringWithEndingToken(attribute, "");
			else
				return setWordStringWithEndingToken(attribute, "| ");
		}
		return "";
	}


	private String setAttributeStringWithEndingToken(ASTNode.Attribute attribute, String token) {
		if (attribute.isList())
			return attribute.getName().toUpperCase() + "_ATTRIBUTE ASSIGN " + attribute.getPrimitiveType().toLowerCase() + "List" + token;
		else
			return attribute.getName().toUpperCase() + "_ATTRIBUTE ASSIGN " + attribute.getPrimitiveType().toUpperCase() + "_VALUE_KEY" + token;
	}

	private String setReferenceStringWithEndingToken(ASTNode.Reference attribute, String token) {
		if (attribute.isList())
			return attribute.getName().toUpperCase() + "_ATTRIBUTE ASSIGN LEFT_SQUARE identifier+ RIGHT_SQUARE" + token;
		else return attribute.getName().toUpperCase() + "_ATTRIBUTE ASSIGN IDENTIFIER_KEY" + token;
	}

	private String setWordStringWithEndingToken(ASTNode.Word attribute, String token) {
		return attribute.getIdentifier().toUpperCase() + "_ATTRIBUTE ASSIGN " + getStringOfWordList(attribute.getWordTypes()) + token;
	}
}
