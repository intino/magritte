package monet.tara.compiler.parser.antlr;

import monet.tara.lang.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import static monet.tara.compiler.parser.antlr.TaraM2Grammar.*;

public class TaraAbstractTreeGenerator extends TaraM2GrammarBaseListener {

	public static final String ATTRIBUTE = "ATTRIBUTE";
	private final String file;
	TreeWrapper ast;
	Stack<Node> conceptStack = new Stack<>();
	String packet = "";
	HashMap<String, String> imports = new HashMap<>();

	public TaraAbstractTreeGenerator(TreeWrapper ast, String file) {
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
		imports.put(ctx.headerReference().getText(), "import");
	}

	@Override
	public void enterConcept(@NotNull ConceptContext ctx) {
		Node container = null;
		if (!conceptStack.empty()) container = conceptStack.peek();
		String identifier = "";
		if (ctx.signature().IDENTIFIER() != null)
			identifier = ctx.signature().IDENTIFIER().getText();
		NodeObject object = new NodeObject(identifier);
		Node node = new Node(object, container);
		node.setLine(ctx.getStart().getLine());
		node.setFile(file);
		object.setDeclaredNode(node);
		object.setPackage(packet);
		object.setImports(imports.keySet().toArray(new String[imports.keySet().size()]));
		if (ctx.signature().CASE() != null) {
			object.setCase(true);
			if (container != null)
				object.setBaseParentConcept(container.getObject().getName());
			object.setParentName(null);
		}
		if (container != null) container.add(node);
		else ast.add(node);
		conceptStack.push(node);
	}


	@Override
	public void enterIdentifierReference(@NotNull IdentifierReferenceContext ctx) {
		String identifierName = ctx.getText();
		if (ctx.getParent() instanceof SignatureContext)
			conceptStack.peek().getObject().setParentName(identifierName);
	}

	@Override
	public void exitConcept(@NotNull ConceptContext ctx) {
		Node node = conceptStack.peek();
		if (!node.getObject().isAbstract() && !node.getObject().isBase())
			ast.addIdentifier(node.getObject().getName(), "KEY");
		ast.add(node.getObject().getName(), node);
		conceptStack.pop();
	}

	@Override
	public void enterDoc(@NotNull DocContext ctx) {
		StringBuilder builder = new StringBuilder();
		for (TerminalNode doc : ctx.DOC())
			builder.append(doc.getText().substring(1));
		conceptStack.peek().getObject().setDoc(builder.toString().trim());
	}

	@Override
	public void enterModifier(@NotNull ModifierContext ctx) {
		Node node = conceptStack.peek();
		if (ctx.BASE() != null) conceptStack.peek().getObject().setBase(true);
		else if (ctx.ABSTRACT() != null) node.getObject().setModifier(ctx.ABSTRACT().getText());
		else if (ctx.FINAL() != null) node.getObject().setModifier(ctx.FINAL().getText());
	}


	@Override
	public void enterAttribute(@NotNull AttributeContext ctx) {
		if (ctx.ALIAS_TYPE() != null) {
			conceptStack.peek().getObject().add(new NodeAttribute(ctx.ALIAS_TYPE().getText(), ctx.IDENTIFIER().getText(), false, ctx.PROPERTY() != null));
			ast.addIdentifier(ctx.IDENTIFIER().getText(), ATTRIBUTE);
		} else if (ctx.INT_TYPE() != null)
			addAttribute(ctx, ctx.INT_TYPE(), (ctx.integerValue() != null) ? ctx.integerValue() : ctx.integerList());
		else if (ctx.DOUBLE_TYPE() != null)
			addAttribute(ctx, ctx.DOUBLE_TYPE(), (ctx.doubleValue() != null) ? ctx.doubleValue() : ctx.doubleList());
		else if (ctx.NATURAL_TYPE() != null)
			addAttribute(ctx, ctx.NATURAL_TYPE(), (ctx.naturalValue() != null) ? ctx.naturalValue() : ctx.naturalList());
		else if (ctx.BOOLEAN_TYPE() != null)
			addAttribute(ctx, ctx.BOOLEAN_TYPE(), (ctx.booleanValue() != null) ? ctx.booleanValue() : ctx.booleanList());
		else if (ctx.resource() == null)
			addAttribute(ctx, ctx.STRING_TYPE(), (ctx.stringValue() != null) ? ctx.stringValue() : ctx.stringList());
	}

	@Override
	public void enterResource(@NotNull ResourceContext ctx) {
		conceptStack.peek().getObject().add(new Resource(ctx.IDENTIFIER(0).getText(), ctx.IDENTIFIER(1).getText(), ctx.PROPERTY() != null));
		ast.addIdentifier(ctx.IDENTIFIER(1).getText(), ATTRIBUTE);
	}

	private void addAttribute(AttributeContext ctx, TerminalNode type, ParserRuleContext value) {
		if (ctx.ASSIGN() == null)
			if (ctx.variableNames() != null)
				for (TerminalNode node : ctx.variableNames().IDENTIFIER()) {
					conceptStack.peek().getObject().add(new NodeAttribute(type.getText(), node.getText(), false, ctx.PROPERTY() != null));
					ast.addIdentifier(node.getText(), ATTRIBUTE);
				}
			else
				conceptStack.peek().getObject().add(new NodeAttribute(type.getText(), ctx.IDENTIFIER().getText(), true, ctx.PROPERTY() != null));
		else {
			NodeAttribute attribute = new NodeAttribute(type.getText(), ctx.IDENTIFIER().getText(), ctx.LIST() != null, ctx.PROPERTY() != null);
			String valueFormatted;
			if (ctx.LIST() != null)
				valueFormatted = value.toStringTree().split("\\[ | \\]")[1];
			else
				valueFormatted = value.getText();
			attribute.setValue(valueFormatted);
			conceptStack.peek().getObject().add(attribute);
		}
	}

	@Override
	public void enterWord(@NotNull WordContext ctx) {
		NodeWord word = new NodeWord(ctx.IDENTIFIER(0).getText());
		ast.addIdentifier(word.getName(), ATTRIBUTE);
		for (TerminalNode wordTypes : ctx.IDENTIFIER().subList(1, ctx.IDENTIFIER().size())) {
			word.add(wordTypes.getText());
			ast.addIdentifier(wordTypes.getText(), "WORD");
		}
		conceptStack.peek().getObject().add(word);
	}

	@Override
	public void enterReference(@NotNull ReferenceContext ctx) {
		String parent = ctx.identifierReference().getText();
		String[] identifiers = getIdentifiers(ctx.variableNames());
		for (String identifier : identifiers) {
			conceptStack.peek().getObject().add(new Reference(parent, identifier, ctx.LIST() != null));
			ast.addIdentifier(identifier, ATTRIBUTE);
		}
	}

	@Override
	public void enterAnnotations(@NotNull AnnotationsContext ctx) {
		for (int i = 0; i < ctx.REQUIRED().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.REQUIRED);
		for (int i = 0; i < ctx.MULTIPLE().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.MULTIPLE);
		for (int i = 0; i < ctx.SINGLETON().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.SINGLETON);
		for (int i = 0; i < ctx.ROOT().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.ROOT);
		for (int i = 0; i < ctx.GENERIC().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.GENERIC);
		for (int i = 0; i < ctx.HAS_NAME().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.HAS_NAME);
		for (int i = 0; i < ctx.INTENTION().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.INTENTION);
	}


	private String[] getIdentifiers(VariableNamesContext namesContext) {
		List<String> list = new ArrayList<>();
		for (TerminalNode terminalNode : namesContext.IDENTIFIER())
			list.add(terminalNode.getText());
		return list.toArray(new String[list.size()]);
	}

}
