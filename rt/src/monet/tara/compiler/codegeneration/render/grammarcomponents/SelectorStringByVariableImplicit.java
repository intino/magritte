package monet.tara.compiler.codegeneration.render.grammarcomponents;


import monet.tara.lang.ASTNode;

import static monet.tara.compiler.codegeneration.render.grammarcomponents.EndingTokenSelector.*;

public class SelectorStringByVariableImplicit {

	private SelectorStringByVariableImplicit() {
	}

	public static String selectByPositionAttributeImplicit(ASTNode elementType, ASTNode extendType, int index) {
		return extendType == null ? processOwnAttributes(elementType, index) : processParentAttributes(elementType, extendType, index);
	}

	private static String processOwnAttributes(ASTNode elementType, int index) {
		return elementType.getVariables().size() - 1 == index ?
			setAttributeStringWithEndingTokenImplicit((ASTNode.Attribute) elementType.getVariables().get(index), "") :
			setAttributeStringWithEndingTokenImplicit((ASTNode.Attribute) elementType.getVariables().get(index), " COMMA ");
	}

	private static String processParentAttributes(ASTNode elementType, ASTNode extendType, int index) {
		return elementType.getVariables().size() == 0 && extendType.getVariables().size() - 1 == index ?
			setAttributeStringWithEndingTokenImplicit((ASTNode.Attribute) extendType.getVariables().get(index), "") :
			setAttributeStringWithEndingTokenImplicit((ASTNode.Attribute) extendType.getVariables().get(index), " COMMA ");
	}

	public static String selectByPositionReferenceImplicit(ASTNode elementType, ASTNode extendType, int index) {
		return extendType == null ? processOwnReference(elementType, index) : processParentReference(elementType, extendType, index);
	}

	private static String processParentReference(ASTNode elementType, ASTNode extendType, int index) {
		return elementType.getVariables().size() == 0 && extendType.getVariables().size() - 1 == index ?
			setReferenceStringWithEndingTokenImplicit((ASTNode.Reference) extendType.getVariables().get(index), "") :
			setReferenceStringWithEndingTokenImplicit((ASTNode.Reference) extendType.getVariables().get(index), " COMMA ");
	}

	private static String processOwnReference(ASTNode elementType, int index) {
		return elementType.getVariables().size() - 1 == index ?
			setReferenceStringWithEndingTokenImplicit((ASTNode.Reference) elementType.getVariables().get(index), "") :
			setReferenceStringWithEndingTokenImplicit((ASTNode.Reference) elementType.getVariables().get(index), " COMMA ");
	}

	public static String selectByPositionWordImplicit(ASTNode elementType, ASTNode extendType, int index) {
		return extendType == null ? processOwnWords(elementType, index) : processParentWords(elementType, extendType, index);
	}

	private static String processParentWords(ASTNode elementType, ASTNode extendType, int index) {
		return elementType.getVariables().size() == 0 && extendType.getVariables().size() - 1 == index ?
			setWordStringWithEndingTokenImplicit((ASTNode.Word) extendType.getVariables().get(index), "") :
			setWordStringWithEndingTokenImplicit((ASTNode.Word) extendType.getVariables().get(index), " COMMA ");
	}

	private static String processOwnWords(ASTNode elementType, int index) {
		return elementType.getVariables().size() - 1 == index ?
			setWordStringWithEndingTokenImplicit((ASTNode.Word) elementType.getVariables().get(index), "") :
			setWordStringWithEndingTokenImplicit((ASTNode.Word) elementType.getVariables().get(index), " COMMA ");
	}
}
