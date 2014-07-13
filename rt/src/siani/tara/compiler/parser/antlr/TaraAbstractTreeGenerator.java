package siani.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import siani.tara.lang.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import static siani.tara.compiler.parser.antlr.TaraGrammar.*;

public class TaraAbstractTreeGenerator extends TaraGrammarBaseListener {

	private final String file;
	Model model;
	Stack<Node> conceptStack = new Stack<>();
	Stack<NodeObject> facetApplyStack = new Stack<>();
	String box = "";
	Set<String> imports = new HashSet<>();
	String currentDocAttribute = "";

	public TaraAbstractTreeGenerator(Model model, String file) {
		this.model = model;
		this.file = file;
	}

	@Override
	public void enterBox(@NotNull BoxContext ctx) {
		box = ctx.headerReference().getText();
		imports.clear();
	}

	@Override
	public void enterAnImport(@NotNull AnImportContext ctx) {
		String text = ctx.headerReference().getText();
		if (ctx.METAMODEL() != null)
			model.setParentModelName(text);
		else
			imports.add(text);
	}

	@Override
	public void enterConcept(@NotNull ConceptContext ctx) {
		DeclaredNode container = !conceptStack.empty() ? (DeclaredNode) conceptStack.peek() : null;
		Node node;
		String name = (ctx.signature().IDENTIFIER() != null) ? ctx.signature().IDENTIFIER().getText() : "";
		String type = (ctx.signature().metaidentifier() != null) ? ctx.signature().metaidentifier().getText() :
			container.getObject().getType();// container can't be null in case
		NodeObject object = new NodeObject(type, name);
		String parent = getParent(ctx);
		node = name.isEmpty() && ctx.body() == null ? new LinkNode(parent, container) : new DeclaredNode(object, container);
		addHeaderInformation(ctx, node);
		if (container != null) {
			if (ctx.signature().CASE() != null) {
				object.setCase(true);
				object.setParentName(container.getQualifiedName());
				object.setParentObject(container.getObject());
			} else object.setParentName(parent);
			container.add(node);
		} else model.add((DeclaredNode) node);
		node.calculateQualifiedName();
		if (node instanceof DeclaredNode) object.setDeclaredNodeQN(node.getQualifiedName());
		conceptStack.push(node);
	}

	@Override
	public void exitConcept(@NotNull ConceptContext ctx) {
		Node node = conceptStack.peek();
		if (node instanceof DeclaredNode)
			model.addIdentifier(node.getObject().getName());
		model.add(node.getQualifiedName(), node);
		conceptStack.pop();
	}

	private void addHeaderInformation(ConceptContext ctx, Node node) {
		node.setLine(ctx.getStart().getLine());
		node.setFile(file);
		node.setBox(box);
		node.addImports(imports);
	}

	private String getParent(ConceptContext ctx) {
		IdentifierReferenceContext identifierReference = ctx.signature().identifierReference();
		return identifierReference != null ? identifierReference.getText() : null;
	}

	@Override
	public void enterFacetApply(@NotNull FacetApplyContext ctx) {
		NodeObject object = conceptStack.peek().getObject();
		if (!facetApplyStack.isEmpty()) object.setParentObject(facetApplyStack.peek());
		DeclaredNode facetNode = new DeclaredNode(new NodeObject("", ctx.identifierReference(0).getText()), null);
		object.applyFacet(facetNode);
		conceptStack.push(facetNode);
	}

	@Override
	public void exitFacetApply(@NotNull FacetApplyContext ctx) {
		conceptStack.pop();
	}

	@Override
	public void enterFacetTarget(@NotNull FacetTargetContext ctx) {
		NodeObject object = conceptStack.peek().getObject();
		object.addFacetTarget(new DeclaredNode(new NodeObject("", ctx.identifierReference().getText()), null));
	}

	@Override
	public void enterDoc(@NotNull DocContext ctx) {
		StringBuilder builder = new StringBuilder();
		for (TerminalNode doc : ctx.DOC())
			builder.append(doc.getText().substring(1));
		String trim = format(builder.toString().trim());
		if (ctx.getParent() instanceof ConceptContext)
			conceptStack.peek().getObject().setDoc(trim);
		else
			currentDocAttribute = trim;
	}

	@Override
	public void exitDoc(@NotNull DocContext ctx) {
		currentDocAttribute = "";
	}

	private String format(String text) {
		return text;
	}

	@Override
	public void enterIntegerAttribute(@NotNull IntegerAttributeContext ctx) {
		super.enterIntegerAttribute(ctx);
		NodeAttribute attribute = new NodeAttribute(ctx.INT_TYPE().getText(), ctx.IDENTIFIER().getText());
		attribute.setDoc(currentDocAttribute);
		conceptStack.peek().getObject().add(attribute);
	}

	@Override
	public void enterParameter(@NotNull ParameterContext ctx) {
		super.enterParameter(ctx);
		NodeObject object = conceptStack.peek().getObject();
		if (ctx.getParent().getParent().getParent() instanceof FacetApplyContext) {
			List<DeclaredNode> facetApplies = object.getFacetApplies();
			facetApplies.get(facetApplies.size() - 1).getObject().addParameter(ctx.getText());
		} else
			object.addParameter(ctx.getText());
	}

	@Override
	public void enterDoubleAttribute(@NotNull DoubleAttributeContext ctx) {
		NodeAttribute variable = new NodeAttribute(ctx.DOUBLE_TYPE().getText(), ctx.IDENTIFIER().getText());
		addAttribute(ctx, variable);
	}

	@Override
	public void enterDateAttribute(@NotNull DateAttributeContext ctx) {
		NodeAttribute variable = new NodeAttribute(ctx.DATE_TYPE().getText(), ctx.IDENTIFIER().getText());
		if (ctx.naturalValue() != null) variable.setValue(ctx.naturalValue().getText());
		addAttribute(ctx, variable);
	}

	@Override
	public void enterNaturalAttribute(@NotNull NaturalAttributeContext ctx) {
		super.enterNaturalAttribute(ctx);
		NodeAttribute variable = new NodeAttribute(ctx.NATURAL_TYPE().getText(), ctx.IDENTIFIER().getText());
		if (ctx.naturalValue() != null) variable.setValue(ctx.naturalValue().getText());
		addAttribute(ctx, variable);
	}

	@Override
	public void enterStringAttribute(@NotNull StringAttributeContext ctx) {
		NodeAttribute variable = new NodeAttribute(ctx.STRING_TYPE().getText(), ctx.IDENTIFIER().getText());
		if (ctx.stringValue() != null) variable.setValue(ctx.stringValue().getText());
		addAttribute(ctx, variable);
	}

	@Override
	public void enterAliasAttribute(@NotNull AliasAttributeContext ctx) {
		NodeAttribute variable = new NodeAttribute(ctx.ALIAS_TYPE().getText(), ctx.IDENTIFIER().getText());
		if (ctx.stringValue() != null) variable.setValue(ctx.stringValue().getText());
		addAttribute(ctx, variable);
	}

	@Override
	public void enterResource(@NotNull ResourceContext ctx) {
		Resource variable = new Resource(ctx.IDENTIFIER(0).getText(), ctx.IDENTIFIER(1).getText());
		addAttribute(ctx, variable);
	}

	@Override
	public void enterWord(@NotNull WordContext ctx) {
		NodeWord variable = new NodeWord(ctx.IDENTIFIER().getText());
		int defaultWord = -1;
		int i = -1;
		for (WordNamesContext word : ctx.wordNames()) {
			i++;
			variable.add(word.getText());
			if (word.STAR() != null) defaultWord = i;
		}
		variable.setDefaultWord(defaultWord);
		addAttribute(ctx, variable);
	}

	@Override
	public void enterReference(@NotNull ReferenceContext ctx) {
		Reference variable = new Reference(ctx.identifierReference().getText(), ctx.IDENTIFIER().getText());
		addAttribute(ctx, variable);
	}

	@Override
	public void enterBooleanAttribute(@NotNull BooleanAttributeContext ctx) {
		NodeAttribute attribute = new NodeAttribute(ctx.BOOLEAN_TYPE().getText(), ctx.IDENTIFIER().getText());
		addAttribute(ctx, attribute);
	}

	private void addAttribute(ParserRuleContext ctx, Variable attribute) {
		NodeObject object = conceptStack.peek().getObject();
		attribute.setDoc(currentDocAttribute);
		if (ctx.getParent().getParent().getParent() instanceof FacetTargetContext) {
			List<DeclaredNode> targets = object.getFacetTargets();
			targets.get(targets.size() - 1).getObject().add(attribute);
		} else object.add(attribute);
	}

	@Override
	public void enterAnnotations(@NotNull AnnotationsContext ctx) {
		if (ctx.getParent() instanceof AttributeContext) {
			processVariableAnnotation(ctx);
			return;
		}
		for (int i = 0; i < ctx.REQUIRED().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.REQUIRED);
		for (int i = 0; i < ctx.SINGLE().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.SINGLE);
		for (int i = 0; i < ctx.TERMINAL().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.TERMINAL);
		for (int i = 0; i < ctx.ROOT().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.ROOT);
		for (int i = 0; i < ctx.PRIVATE().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.PRIVATE);
		for (int i = 0; i < ctx.NAMEABLE().size(); i++)
			conceptStack.peek().getObject().add(NodeObject.AnnotationType.NAMEABLE);
	}

	private void processVariableAnnotation(AnnotationsContext ctx) {
		List<Variable> variables = conceptStack.peek().getObject().getVariables();
		Variable variable = variables.get(variables.size() - 1);
		variable.setTerminal(!ctx.TERMINAL().isEmpty());
		variable.setSingle(!ctx.SINGLE().isEmpty());
		variable.setProperty(!ctx.PROPERTY().isEmpty());
	}
}
