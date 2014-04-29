package monet.tara.compiler.parser.antlr;

import monet.tara.lang.ASTNode;
import monet.tara.lang.ASTWrapper;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static monet.tara.compiler.parser.antlr.TaraM2Grammar.*;

public class TaraASTGeneratorListener extends TaraM2GrammarBaseListener {

	public static final String ATTRIBUTE = "ATTRIBUTE";
	private final String file;
	ASTWrapper ast;
	Stack<ASTNode> conceptStack = new Stack<>();
	String packet = "";
	ArrayList<String> imports = new ArrayList<>();

	public TaraASTGeneratorListener(ASTWrapper ast, String file) {
		this.ast = ast;
		this.file = file;
	}

	@Override
	public void enterPacket(@NotNull PacketContext ctx) {
		packet = ctx.headerReference().getText();
		imports.clear();
	}

	@Override
	public void enterImports(@NotNull ImportsContext ctx) {
		imports.add(ctx.headerReference().getText());
	}

	@Override
	public void enterConcept(@NotNull ConceptContext ctx) {
		ASTNode parent = null;
		if (!conceptStack.empty()) parent = conceptStack.peek();
		ASTNode node = new ASTNode(ctx.signature().IDENTIFIER().getText(), parent, file);
		node.setPackage(packet);
		node.addImports(imports);
		node.setLine(ctx.getStart().getLine());
		if (parent != null) parent.add(node);
		else ast.add(node);
		ast.add(node.getIdentifier(), node);
		conceptStack.push(node);
	}

	@Override
	public void enterReferenceIdentifier(@NotNull ReferenceIdentifierContext ctx) {
		String identifierName = "";
		for (ChildContext childContext : ctx.child())
			identifierName += childContext;
		identifierName += ctx.IDENTIFIER();
		if (!(ctx.getParent() instanceof ReferenceContext))
			conceptStack.peek().setExtendFrom(identifierName);
	}

	@Override
	public void exitConcept(@NotNull ConceptContext ctx) {
		ASTNode node = conceptStack.peek();
		if (!node.isAbstract() && !node.isBase())
			ast.addIdentifier(node.getIdentifier(), "KEY");
		conceptStack.pop();
	}

	@Override
	public void enterDoc(@NotNull DocContext ctx) {
		StringBuilder builder = new StringBuilder();
		for (TerminalNode doc : ctx.DOC())
			builder.append(doc.getText().substring(1));
		conceptStack.peek().setDoc(builder.toString().trim());
	}

	@Override
	public void enterModifier(@NotNull ModifierContext ctx) {
		ASTNode node = conceptStack.peek();
		if (ctx.BASE() != null) conceptStack.peek().setBase(true);
		else if (ctx.ABSTRACT() != null) node.setModifier(ctx.ABSTRACT().getText());
		else if (ctx.FINAL() != null) node.setModifier(ctx.FINAL().getText());
	}


	@Override
	public void enterAttribute(@NotNull AttributeContext ctx) {

		if (ctx.UID_TYPE() != null) {
			conceptStack.peek().add(new ASTNode.Attribute(ctx.UID_TYPE().getText(), ctx.IDENTIFIER().getText(), false));
			ast.addIdentifier(ctx.IDENTIFIER().getText(), ATTRIBUTE);
		} else if (ctx.INT_TYPE() != null)
			addAttribute(ctx, ctx.INT_TYPE(), (ctx.integerValue() != null) ? ctx.integerValue() : ctx.integerList());
		else if (ctx.DOUBLE_TYPE() != null)
			addAttribute(ctx, ctx.DOUBLE_TYPE(), (ctx.doubleValue() != null) ? ctx.doubleValue() : ctx.doubleList());
		else if (ctx.NATURAL_TYPE() != null)
			addAttribute(ctx, ctx.NATURAL_TYPE(), (ctx.naturalValue() != null) ? ctx.naturalValue() : ctx.naturalList());
		else if (ctx.BOOLEAN_TYPE() != null)
			addAttribute(ctx, ctx.BOOLEAN_TYPE(), (ctx.booleanValue() != null) ? ctx.booleanValue() : ctx.booleanList());
		else
			addAttribute(ctx, ctx.STRING_TYPE(), (ctx.stringValue() != null) ? ctx.stringValue() : ctx.stringList());
	}

	private void addAttribute(AttributeContext ctx, TerminalNode type, ParserRuleContext value) {
		if (ctx.ASSIGN() == null)
			if (ctx.variableNames() != null)
				for (TerminalNode node : ctx.variableNames().IDENTIFIER()) {
					conceptStack.peek().add(new ASTNode.Attribute(type.getText(), node.getText(), false));
					ast.addIdentifier(node.getText(), ATTRIBUTE);
				}
			else conceptStack.peek().add(new ASTNode.Attribute(type.getText(), ctx.IDENTIFIER().getText(), true));
		else {
			ASTNode.Attribute attribute = new ASTNode.Attribute(type.getText(), ctx.IDENTIFIER().getText(), ctx.LIST() != null);
			String valueFormatted;
			if (ctx.LIST() != null)
				valueFormatted = value.toStringTree().split("\\[ | \\]")[1];
			else
				valueFormatted = value.getText();
			attribute.setValue(valueFormatted);
			conceptStack.peek().add(attribute);
		}
	}

	@Override
	public void enterWord(@NotNull WordContext ctx) {
		ASTNode.Word word = new ASTNode.Word(ctx.IDENTIFIER(0).getText());
		ast.addIdentifier(word.getIdentifier(), ATTRIBUTE);
		for (TerminalNode wordTypes : ctx.IDENTIFIER().subList(1, ctx.IDENTIFIER().size())) {
			word.add(wordTypes.getText());
			ast.addIdentifier(wordTypes.getText(), "WORD");
		}
		conceptStack.peek().add(word);
	}

	@Override
	public void enterReference(@NotNull ReferenceContext ctx) {
		String parent = getExtendedConceptString(ctx.referenceIdentifier());
		String[] identifiers = getIdentifiers(ctx.variableNames());
		for (String identifier : identifiers) {
			conceptStack.peek().addReference(parent, identifier, ctx.LIST() != null);
			ast.addIdentifier(identifier, ATTRIBUTE);
		}
	}

	private String getExtendedConceptString(ReferenceIdentifierContext ctx) {
		String extendedConcept = "";
		for (ChildContext childContext : ctx.child())
			extendedConcept += childContext;
		extendedConcept += ctx.IDENTIFIER();
		return extendedConcept;
	}

	@Override
	public void enterAnnotations(@NotNull AnnotationsContext ctx) {
		for (int i = 0; i < ctx.OPTIONAL().size(); i++)
			conceptStack.peek().add(ASTNode.AnnotationType.OPTIONAL);
		for (int i = 0; i < ctx.MULTIPLE().size(); i++)
			conceptStack.peek().add(ASTNode.AnnotationType.MULTIPLE);
		for (int i = 0; i < ctx.extensible().size(); i++)
			conceptStack.peek().add(ASTNode.AnnotationType.EXTENSIBLE);
		for (int i = 0; i < ctx.HAS_CODE().size(); i++)
			conceptStack.peek().add(ASTNode.AnnotationType.HAS_CODE);
		for (int i = 0; i < ctx.SINGLETON().size(); i++)
			conceptStack.peek().add(ASTNode.AnnotationType.SINGLETON);
		for (int i = 0; i < ctx.ROOT().size(); i++)
			conceptStack.peek().add(ASTNode.AnnotationType.ROOT);
		for (int i = 0; i < ctx.GENERIC().size(); i++)
			conceptStack.peek().add(ASTNode.AnnotationType.GENERIC);
	}

	private String[] getIdentifiers(VariableNamesContext namesContext) {
		List<String> list = new ArrayList<>();
		for (TerminalNode terminalNode : namesContext.IDENTIFIER())
			list.add(terminalNode.getText());
		return list.toArray(new String[list.size()]);
	}

}
