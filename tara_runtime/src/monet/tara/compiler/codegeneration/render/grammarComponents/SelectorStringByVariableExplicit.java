package monet.tara.compiler.codegeneration.render.grammarcomponents;


import monet.tara.compiler.core.ast.ASTNode;

import static monet.tara.compiler.codegeneration.render.grammarcomponents.EndingTokenSelector.*;

public class SelectorStringByVariableExplicit {

	public static String selectByPositionReference(ASTNode elementType, ASTNode extendType, int index) {
		return extendType == null ? processOwnReferences(elementType, index) : processParentReferences(elementType, extendType, index);
	}

	private static String processParentReferences(ASTNode elementType, ASTNode extendType, int index) {
		return setReferenceStringWithEndingToken((ASTNode.Reference) extendType.getVariables().get(index),
			elementType.getVariables().size() == 0 && extendType.getVariables().size() - 1 == index ? "" : "| ");
	}

	private static String processOwnReferences(ASTNode elementType, int index) {
		return setReferenceStringWithEndingToken((ASTNode.Reference) elementType.getVariables().get(index),
			elementType.getVariables().size() - 1 == index ? "" : "| ");
	}

	public static String selectByPositionWord(ASTNode elementType, ASTNode extendType, int index) {
		return extendType == null ? processOwnWords(elementType, index) : processParentWords(elementType, extendType, index);
	}

	private static String processParentWords(ASTNode elementType, ASTNode extendType, int index) {
		return setWordStringWithEndingToken((ASTNode.Word) extendType.getVariables().get(index),
			elementType.getVariables().size() == 0 && extendType.getVariables().size() - 1 == index ? "" : "| ");
	}

	private static String processOwnWords(ASTNode elementType, int index) {
		return setWordStringWithEndingToken((ASTNode.Word) elementType.getVariables().get(index),
			elementType.getVariables().size() - 1 == index ? "" : "| ");
	}

	public static String selectByPositionAttribute(ASTNode elementType, ASTNode extendType, int index) {
		return extendType == null ? processOwnAttributes(elementType, index) : processParentAttributes(elementType, extendType, index);
	}

	private static String processParentAttributes(ASTNode elementType, ASTNode extendType, int index) {
		return setAttributeStringWithEndingToken((ASTNode.Attribute) extendType.getVariables().get(index),
			elementType.getVariables().size() == 0 && extendType.getVariables().size() - 1 == index ? "" : "| ");
	}

	private static String processOwnAttributes(ASTNode elementType, int index) {
		return setAttributeStringWithEndingToken((ASTNode.Attribute) elementType.getVariables().get(index),
			elementType.getVariables().size() - 1 == index ? "" : "| ");
	}
}
