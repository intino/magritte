package monet.tara.compiler.parser.antlr;

import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.ast.ASTNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

import static monet.tara.compiler.parser.antlr.TaraM2Grammar.*;

public class TaraASTGeneratorListener extends TaraM2GrammarBaseListener {

	AST ast;
	Stack<ASTNode> conceptStack = new Stack<>();
	Stack<ASTNode.SubModel> subModelStack = new Stack<>();

	public TaraASTGeneratorListener(AST ast) {
		this.ast = ast;
	}

	@Override
	public void enterConcept(@NotNull ConceptContext ctx) {
		ASTNode node = new ASTNode(ctx.IDENTIFIER().getText(), null);
		ast.put(ctx.IDENTIFIER().getText(), node);
		conceptStack.push(node);
	}

	@Override
	public void enterExtendedConcept(@NotNull ExtendedConceptContext ctx) {
		String identifierName = "";
		for (TerminalNode identifier : ctx.IDENTIFIER())
			identifierName += identifier + ".";
		conceptStack.peek().setExtendFrom(identifierName.substring(0, identifierName.length() - 1));
	}

	@Override
	public void enterComponent(@NotNull ComponentContext ctx) {
		ASTNode componentNode;
		if (ctx.IDENTIFIER() != null)
			componentNode = new ASTNode(ctx.IDENTIFIER().getText(), conceptStack.peek());
		else
			componentNode = new ASTNode();
		conceptStack.peek().add(componentNode);
		conceptStack.push(componentNode);
	}

	@Override
	public void enterFrom(@NotNull FromContext ctx) {
		ASTNode.SubModel subModel = new ASTNode.SubModel();
		conceptStack.peek().add(subModel);
		subModelStack.push(subModel);
	}

	@Override
	public void exitFrom(@NotNull FromContext ctx) {
		subModelStack.pop();
	}

	@Override
	public void enterFromComponent(@NotNull FromComponentContext ctx) {
		ASTNode componentNode = new ASTNode();
		if (ctx.IDENTIFIER() != null)
			componentNode.setIdentifier(ctx.IDENTIFIER().getText());
		componentNode.setParent(conceptStack.peek());
		subModelStack.peek().add(componentNode);
		conceptStack.push(componentNode);
	}

	@Override
	public void enterFromAnnotations(@NotNull FromAnnotationsContext ctx) {
		ASTNode.SubModel node = subModelStack.peek();
		if (!ctx.OPTIONAL().isEmpty()) node.add(ASTNode.AnnotationType.Optional);
		if (!ctx.MULTIPLE().isEmpty()) node.add(ASTNode.AnnotationType.Multiple);
	}

	@Override
	public void enterFromConceptAnnotations(@NotNull FromConceptAnnotationsContext ctx) {
		ASTNode node = conceptStack.peek();
		if (!ctx.HAS_CODE().isEmpty()) node.add(ASTNode.AnnotationType.HasCode);
		if (!ctx.EXTENSIBLE().isEmpty()) node.add(ASTNode.AnnotationType.Extensible);
		if (!ctx.SINGLETON().isEmpty()) node.add(ASTNode.AnnotationType.Singleton);
	}

	@Override
	public void exitFromComponent(@NotNull FromComponentContext ctx) {
		conceptStack.pop();
	}

	@Override
	public void exitConcept(@NotNull ConceptContext ctx) {
		conceptStack.pop();
	}

	@Override
	public void exitComponent(@NotNull ComponentContext ctx) {
		conceptStack.pop();
	}

	@Override
	public void enterDoc(@NotNull DocContext ctx) {
		conceptStack.peek().setDoc((ctx.DOC_BLOCK() != null) ? ctx.DOC_BLOCK().getText() : ctx.DOC_LINE().getText());
	}

	@Override
	public void enterModifier(@NotNull ModifierContext ctx) {
		ASTNode node = conceptStack.peek();
		node.setModifier((ctx.ABSTRACT() != null) ? ctx.ABSTRACT().getText() : ctx.FINAL().getText());
	}

	@Override
	public void enterConceptAnnotations(@NotNull ConceptAnnotationsContext ctx) {
		ASTNode node = conceptStack.peek();
		if (!ctx.ROOT().isEmpty()) node.add(ASTNode.AnnotationType.Root);
		if (!ctx.HAS_CODE().isEmpty()) node.add(ASTNode.AnnotationType.HasCode);
		if (!ctx.EXTENSIBLE().isEmpty()) node.add(ASTNode.AnnotationType.Extensible);
		if (!ctx.SINGLETON().isEmpty()) node.add(ASTNode.AnnotationType.Singleton);
	}

	@Override
	public void enterComponentAnnotations(@NotNull ComponentAnnotationsContext ctx) {
		ASTNode node = conceptStack.peek();
		if (!ctx.HAS_CODE().isEmpty()) node.add(ASTNode.AnnotationType.HasCode);
		if (!ctx.EXTENSIBLE().isEmpty()) node.add(ASTNode.AnnotationType.Extensible);
		if (!ctx.SINGLETON().isEmpty()) node.add(ASTNode.AnnotationType.Singleton);
		if (!ctx.MULTIPLE().isEmpty()) node.add(ASTNode.AnnotationType.Multiple);
		if (!ctx.OPTIONAL().isEmpty()) node.add(ASTNode.AnnotationType.Optional);
	}


	@Override
	public void enterAttribute(@NotNull AttributeContext ctx) {
		ASTNode.Attribute attribute;
		if (ctx.UID_TYPE() != null)
			attribute = new ASTNode.Attribute(ctx.UID_TYPE().getText(), ctx.IDENTIFIER().getText());
		else if (ctx.INT_TYPE() != null)
			attribute = new ASTNode.Attribute(ctx.INT_TYPE().getText() + ((ctx.LIST() != null) ? "[]" : ""), ctx.IDENTIFIER().getText());
		else if (ctx.DOUBLE_TYPE() != null)
			attribute = new ASTNode.Attribute(ctx.DOUBLE_TYPE().getText() + ((ctx.LIST() != null) ? "[]" : ""), ctx.IDENTIFIER().getText());
		else if (ctx.NATURAL_TYPE() != null)
			attribute = new ASTNode.Attribute(ctx.NATURAL_TYPE().getText() + ((ctx.LIST() != null) ? "[]" : ""), ctx.IDENTIFIER().getText());
		else if (ctx.BOOLEAN_TYPE() != null)
			attribute = new ASTNode.Attribute(ctx.BOOLEAN_TYPE().getText() + ((ctx.LIST() != null) ? "[]" : ""), ctx.IDENTIFIER().getText());
		else
			attribute = new ASTNode.Attribute(ctx.STRING_TYPE().getText() + ((ctx.LIST() != null) ? "[]" : ""), ctx.IDENTIFIER().getText());
		conceptStack.peek().add(attribute);
	}

	@Override
	public void enterWord(@NotNull WordContext ctx) {
		ASTNode.Word word = new ASTNode.Word(ctx.IDENTIFIER(0).getText());
		for (TerminalNode wordTypes : ctx.IDENTIFIER().subList(1, ctx.IDENTIFIER().size()))
			word.add(wordTypes.getText());
		conceptStack.peek().add(word);
	}

	@Override
	public void enterReference(@NotNull ReferenceContext ctx) {
		String parent = "";
		for (int i = 0; i < ctx.IDENTIFIER().size() - 1; i++)
			parent += ctx.IDENTIFIER(i).getText() + ".";
		conceptStack.peek().addReference(parent.substring(0, parent.length() - 1) +
			((ctx.LIST() != null) ? "[]" : ""), ctx.IDENTIFIER(ctx.IDENTIFIER().size() - 1).getText());
	}
}
