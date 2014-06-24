package siani.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import siani.tara.lang.*;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import static siani.tara.compiler.parser.antlr.TaraGrammar.*;

public class TaraAbstractTreeGenerator extends TaraGrammarBaseListener {

	private final String file;
	TreeWrapper treeWrapper;
	Stack<Node> conceptStack = new Stack<>();
	String packet = "";
	HashMap<String, String> imports = new HashMap<>();

	public TaraAbstractTreeGenerator(TreeWrapper treeWrapper, String file) {
		this.treeWrapper = treeWrapper;
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
		Node container = !conceptStack.empty() ? conceptStack.peek() : null;
		String identifier = "";
		if (ctx.signature().IDENTIFIER() != null)
			identifier = ctx.signature().IDENTIFIER().getText();
		NodeObject object = new NodeObject(identifier);
		Node node = new Node(object, container, false);
		node.setLine(ctx.getStart().getLine());
		node.setFile(file);
		object.setDeclaredNode(node);
		object.setPackage(packet);
		object.setImports(imports.keySet().toArray(new String[imports.keySet().size()]));
		if (ctx.signature().CASE() != null) {
			object.setCase(true);
			object.setBaseNode(conceptStack.peek());
			object.setParentName(null);
		}
		if (container != null) {
			//if (ctx.body() != null)
				container.add(node);
		} else treeWrapper.add(node);
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
//		if (!node.getObject().isAbstract() && !node.getObject().isBase())
			treeWrapper.addIdentifier(node.getObject().getName());
		node.calculateQualifiedName();
		if (node.getContainer() != null && ctx.body() == null)
			node.getContainer().add(node.getQualifiedName());
		treeWrapper.add(node.getQualifiedName(), node);
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
	public void enterIntegerAttribute(@NotNull IntegerAttributeContext ctx) {
		super.enterIntegerAttribute(ctx);
		NodeAttribute attribute = new NodeAttribute(ctx.INT_TYPE().getText(), ctx.IDENTIFIER().getText());
		attribute.setDoc((ctx.doc() != null) ? ctx.doc().getText() : "");
		conceptStack.peek().getObject().add(attribute);
	}

	@Override
	public void enterParameter(@NotNull ParameterContext ctx) {
		super.enterParameter(ctx);
		conceptStack.peek().getObject().addParameter(ctx.getText());
	}

	@Override
	public void enterDoubleAttribute(@NotNull DoubleAttributeContext ctx) {
		super.enterDoubleAttribute(ctx);
		NodeAttribute attribute = new NodeAttribute(ctx.DOUBLE_TYPE().getText(), ctx.IDENTIFIER().getText());
		attribute.setDoc((ctx.doc() != null) ? ctx.doc().getText() : "");
		conceptStack.peek().getObject().add(attribute);
	}

	@Override
	public void enterNaturalAttribute(@NotNull NaturalAttributeContext ctx) {
		super.enterNaturalAttribute(ctx);
		NodeAttribute attribute = new NodeAttribute(ctx.NATURAL_TYPE().getText(), ctx.IDENTIFIER().getText());
		attribute.setDoc((ctx.doc() != null) ? ctx.doc().getText() : "");
		conceptStack.peek().getObject().add(attribute);
	}

	@Override
	public void enterStringAttribute(@NotNull StringAttributeContext ctx) {
		super.enterStringAttribute(ctx);
		NodeAttribute attribute = new NodeAttribute(ctx.STRING_TYPE().getText(), ctx.IDENTIFIER().getText());
		attribute.setDoc((ctx.doc() != null) ? ctx.doc().getText() : "");
		conceptStack.peek().getObject().add(attribute);
	}

	@Override
	public void enterAliasAttribute(@NotNull AliasAttributeContext ctx) {
		super.enterAliasAttribute(ctx);
		NodeAttribute attribute = new NodeAttribute(ctx.ALIAS_TYPE().getText(), ctx.IDENTIFIER().getText());
		attribute.setDoc((ctx.doc() != null) ? ctx.doc().getText() : "");
		conceptStack.peek().getObject().add(attribute);
	}

	@Override
	public void enterResource(@NotNull ResourceContext ctx) {
		conceptStack.peek().getObject().add(new Resource(ctx.IDENTIFIER(0).getText(), ctx.IDENTIFIER(1).getText()));
	}

	@Override
	public void enterWord(@NotNull WordContext ctx) {
		NodeWord word = new NodeWord(ctx.IDENTIFIER(0).getText());
		for (TerminalNode wordTypes : ctx.IDENTIFIER().subList(1, ctx.IDENTIFIER().size()))
			word.add(wordTypes.getText());
		conceptStack.peek().getObject().add(word);
	}

	@Override
	public void enterReference(@NotNull ReferenceContext ctx) {
		String parent = ctx.identifierReference().getText();
		conceptStack.peek().getObject().add(new Reference(parent, ctx.IDENTIFIER().getText()));
	}

	@Override
	public void enterAnnotations(@NotNull AnnotationsContext ctx) {
		if (ctx.getParent() instanceof AttributeContext) {
			processVariableAnnotation(ctx);
			return;
		}
		for (int i = 0; i < ctx.REQUIRED().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.REQUIRED);
		for (int i = 0; i < ctx.MULTIPLE().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.MULTIPLE);
		for (int i = 0; i < ctx.TERMINAL().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.TERMINAL);
		for (int i = 0; i < ctx.ROOT().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.ROOT);
		for (int i = 0; i < ctx.PRIVATE().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.PRIVATE);
		for (int i = 0; i < ctx.HAS_NAME().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.HAS_NAME);
		for (int i = 0; i < ctx.INTENTION().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.INTENTION);
	}

	private void processVariableAnnotation(AnnotationsContext ctx) {
		List<Variable> variables = conceptStack.peek().getObject().getVariables();
		Variable variable = variables.get(variables.size() - 1);
		variable.setTerminal(ctx.TERMINAL() != null);
		variable.setMultiple(ctx.MULTIPLE() != null);
		variable.setProperty(ctx.PROPERTY() != null);
	}
}
