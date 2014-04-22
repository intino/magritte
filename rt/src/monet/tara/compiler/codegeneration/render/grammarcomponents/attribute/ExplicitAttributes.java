package monet.tara.compiler.codegeneration.render.grammarcomponents.attribute;


import monet.tara.compiler.codegeneration.render.grammarcomponents.Attributes;
import monet.tara.compiler.codegeneration.render.grammarcomponents.SelectorStringByVariableExplicit;
import monet.tara.compiler.core.ast.ASTWrapper;
import monet.tara.compiler.core.ast.ASTNode;

public class ExplicitAttributes extends Attributes {

	public ExplicitAttributes(ASTNode node, ASTWrapper root) {
		super(node, root);
	}

	@Override
	protected String stringByEvaluationParentType(ASTNode elementType, ASTNode extendType, int index) {
		if (extendType.getVariables().get(index) instanceof ASTNode.Attribute)
			return SelectorStringByVariableExplicit.selectByPositionAttribute(elementType, extendType, index);
		if (extendType.getVariables().get(index) instanceof ASTNode.Reference)
			return SelectorStringByVariableExplicit.selectByPositionReference(elementType, extendType, index);
		if (extendType.getVariables().get(index) instanceof ASTNode.Word)
			return SelectorStringByVariableExplicit.selectByPositionWord(elementType, extendType, index);
		return "";
	}

	@Override
	protected String stringByEvaluationType(ASTNode elementType, int index) {
		if (elementType.getVariables().get(index) instanceof ASTNode.Attribute)
			return SelectorStringByVariableExplicit.selectByPositionAttribute(elementType, null, index);
		if (elementType.getVariables().get(index) instanceof ASTNode.Reference)
			return SelectorStringByVariableExplicit.selectByPositionReference(elementType, null, index);
		if (elementType.getVariables().get(index) instanceof ASTNode.Word)
			return SelectorStringByVariableExplicit.selectByPositionWord(elementType, null, index);
		return "";
	}
}
