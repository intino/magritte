package monet.tara.compiler.codegeneration.render.grammarcomponents.attribute;


import monet.tara.compiler.codegeneration.render.grammarcomponents.Attributes;
import monet.tara.compiler.codegeneration.render.grammarcomponents.SelectorStringByVariableImplicit;
import monet.tara.lang.ASTWrapper;
import monet.tara.lang.ASTNode;

public class ImplicitAttributes extends Attributes {

	public ImplicitAttributes(ASTNode node, ASTWrapper root) {
		super(node, root);
	}

	@Override
	protected String stringByEvaluationParentType(ASTNode elementType, ASTNode extendType, int i) {
		if (extendType.getVariables().get(i) instanceof ASTNode.Attribute)
			return SelectorStringByVariableImplicit.selectByPositionAttributeImplicit(elementType, extendType, i);
		if (extendType.getVariables().get(i) instanceof ASTNode.Reference)
			return SelectorStringByVariableImplicit.selectByPositionReferenceImplicit(elementType, extendType, i);
		if (extendType.getVariables().get(i) instanceof ASTNode.Word)
			return SelectorStringByVariableImplicit.selectByPositionWordImplicit(elementType, extendType, i);
		return "";
	}

	@Override
	protected String stringByEvaluationType(ASTNode elementType, int i) {
		if (elementType.getVariables().get(i) instanceof ASTNode.Attribute)
			return SelectorStringByVariableImplicit.selectByPositionAttributeImplicit(elementType, null, i);
		if (elementType.getVariables().get(i) instanceof ASTNode.Reference)
			return SelectorStringByVariableImplicit.selectByPositionReferenceImplicit(elementType, null, i);
		if (elementType.getVariables().get(i) instanceof ASTNode.Word)
			return SelectorStringByVariableImplicit.selectByPositionWordImplicit(elementType, null, i);
		return "";
	}
}