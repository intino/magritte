package siani.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import siani.tara.lang.*;
import siani.tara.lang.util.ModelLoader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import static siani.tara.compiler.parser.antlr.TaraGrammar.*;
import static siani.tara.lang.Annotations.Annotation;

public class TaraAbstractModelGenerator extends TaraGrammarBaseListener {

	private final String file;
	private final String modelsPath;
	Model model;
	Stack<Node> conceptStack = new Stack<>();
	Stack<NodeObject> facetTargetStack = new Stack<>();
	String box = "";
	Set<String> imports = new HashSet<>();
	String currentDocAttribute = "";

	public TaraAbstractModelGenerator(Model model, String file, String modelsPath) {
		this.model = model;
		this.file = file;
		this.modelsPath = modelsPath;
	}

	@Override
	public void enterBox(@NotNull BoxContext ctx) {
		box = ctx.headerReference().getText();
		imports.clear();
	}

	@Override
	public void enterAnImport(@NotNull AnImportContext ctx) {
		String name = ctx.headerReference().getText();
		if (ctx.METAMODEL() != null) {
			model.setParentModelName(name);
			model.setParentModel(ModelLoader.load(modelsPath, name));
		} else imports.add(name);
	}

	@Override
	public void enterConcept(@NotNull ConceptContext ctx) {
		DeclaredNode container = !conceptStack.empty() ? (DeclaredNode) conceptStack.peek() : null;
		Node node;
		String name = (ctx.signature().IDENTIFIER() != null) ? ctx.signature().IDENTIFIER().getText() : "";
		String type = (ctx.signature().metaidentifier() != null) ?
			ctx.signature().metaidentifier().getText() :
			container.getObject().getType();
		String parent = getParent(ctx);
		node = name.isEmpty() && ctx.body() == null ?
			new LinkNode(parent, container) :
			new DeclaredNode(new NodeObject(type, name), container);
		addHeaderInformation(ctx, node);
		addNodeToModel(ctx, node, parent);
		node.calculateQualifiedName();
		if (node instanceof DeclaredNode) node.getObject().setDeclaredNodeQN(node.getQualifiedName());
		conceptStack.push(node);
	}

	private void addNodeToModel(ConceptContext ctx, Node node, String parent) {
		DeclaredNode container = node.getContainer();
		if (node.is(DeclaredNode.class)) {
			if (container != null) {
				if (isCase(ctx)) {
					node.getObject().setCase(true);
					node.getObject().setParentName(node.getContainer().getQualifiedName());
					node.getObject().setParentObject(container.getObject());
					container.getObject().add((DeclaredNode) node);
				}
				container.add(node);
			} else model.add(node);
			if (parent != null) node.getObject().setParentName(parent);
		} else if (node instanceof LinkNode)
			container.add(node);
		else
			model.add(node);
	}

	private boolean isCase(ConceptContext ctx) {
		return ctx.signature().SUB() != null;
	}

	@Override
	public void exitConcept(@NotNull ConceptContext ctx) {
		Node node = conceptStack.peek();
		if (node instanceof DeclaredNode)
			model.addIdentifier(node.getObject().getName());
		model.add(node.getQualifiedName(), node);
		conceptStack.pop();
	}

	private void addHeaderInformation(ParserRuleContext ctx, Node node) {
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

	}

	@Override
	public void enterFacetTarget(@NotNull FacetTargetContext ctx) {
		NodeObject nodeObject = conceptStack.peek().getObject();
		NodeObject intentionObject = new NodeObject("", ctx.identifierReference().getText());
		if (!facetTargetStack.isEmpty()) intentionObject.setParentObject(facetTargetStack.peek());
		nodeObject.addFacetObjectTarget(intentionObject);
		facetTargetStack.push(intentionObject);
	}

	@Override
	public void exitFacetTarget(@NotNull FacetTargetContext ctx) {
		facetTargetStack.pop();
	}

	@Override
	public void enterDoc(@NotNull DocContext ctx) {
		StringBuilder builder = new StringBuilder();
		for (TerminalNode doc : ctx.DOC())
			builder.append(doc.getText().substring(1));
		String trim = builder.toString().trim();
		if (ctx.getParent() instanceof ConceptContext)
			conceptStack.peek().getObject().setDoc(trim);
		else
			currentDocAttribute = trim;
	}

	@Override
	public void exitDoc(@NotNull DocContext ctx) {
		currentDocAttribute = "";
	}

	@Override
	public void enterParameter(@NotNull ParameterContext ctx) {
		super.enterParameter(ctx);
		NodeObject object = conceptStack.peek().getObject();
		if (ctx.getParent().getParent().getParent() instanceof FacetApplyContext) {
			List<NodeObject> facetApplies = object.getFacets();
			facetApplies.get(facetApplies.size() - 1).addParameter(ctx.getText());
		} else
			object.addParameter(ctx.getText());
	}

	@Override
	public void enterConceptReference(@NotNull ConceptReferenceContext ctx) {
		String parent = ctx.identifierReference().getText();
		LinkNode node = new LinkNode(parent, (DeclaredNode) conceptStack.peek());
		addHeaderInformation(ctx, node);
		((DeclaredNode) conceptStack.peek()).add(node);
		node.calculateQualifiedName();
		model.add(node.getQualifiedName(), node);
		node.calculateQualifiedName();
	}

	@Override
	public void enterIntegerAttribute(@NotNull IntegerAttributeContext ctx) {
		super.enterIntegerAttribute(ctx);
		NodeAttribute variable = new NodeAttribute(ctx.INT_TYPE().getText(), ctx.IDENTIFIER().getText());
		variable.setList(ctx.LIST() != null);
		if (ctx.integerValue() != null) variable.setValue(ctx.integerValue().getText());
		else if (ctx.EMPTY() != null) variable.setValue(Variable.EMPTY);
		if (ctx.measure() != null) variable.setValue(ctx.measure().getText());
		addAttribute(ctx, variable);
	}

	@Override
	public void enterDoubleAttribute(@NotNull DoubleAttributeContext ctx) {
		NodeAttribute variable = new NodeAttribute(ctx.DOUBLE_TYPE().getText(), ctx.IDENTIFIER().getText());
		variable.setList(ctx.LIST() != null);
		if (ctx.doubleValue() != null) variable.setValue(ctx.doubleValue().getText());
		else if (ctx.EMPTY() != null) variable.setValue(Variable.EMPTY);
		if (ctx.measure() != null) variable.setValue(ctx.measure().getText());
		addAttribute(ctx, variable);
	}

	@Override
	public void enterNaturalAttribute(@NotNull NaturalAttributeContext ctx) {
		super.enterNaturalAttribute(ctx);
		NodeAttribute variable = new NodeAttribute(ctx.NATURAL_TYPE().getText(), ctx.IDENTIFIER().getText());
		if (ctx.naturalValue() != null) variable.setValue(ctx.naturalValue().getText());
		if (ctx.EMPTY() != null) variable.setValue(Variable.EMPTY);
		if (ctx.measure() != null) variable.setValue(ctx.measure().getText());
		variable.setList(ctx.LIST() != null);
		addAttribute(ctx, variable);
	}

	@Override
	public void enterDateAttribute(@NotNull DateAttributeContext ctx) {
		NodeAttribute variable = new NodeAttribute(ctx.DATE_TYPE().getText(), ctx.IDENTIFIER().getText());
		if (ctx.dateValue() != null) variable.setValue(ctx.dateValue().getText());
		if (ctx.EMPTY() != null) variable.setValue(Variable.EMPTY);
		variable.setList(ctx.LIST() != null);
		addAttribute(ctx, variable);
	}

	@Override
	public void enterStringAttribute(@NotNull StringAttributeContext ctx) {
		NodeAttribute variable = new NodeAttribute(ctx.STRING_TYPE().getText(), ctx.IDENTIFIER().getText());
		if (ctx.stringValue() != null) variable.setValue(formatText(ctx.stringValue().getText()));
		if (ctx.EMPTY() != null) variable.setValue(Variable.EMPTY);
		variable.setList(ctx.LIST() != null);
		addAttribute(ctx, variable);
	}

	private String formatText(String text) {
		if (!text.startsWith("---")) return text;
		String s = text.replaceAll("---(-*)\\n", "").replaceAll("---(-*)", "");
		String[] splits = s.split("[\t]+|[ ]+");
		char l = 0;
		for (String split : splits) if (!split.isEmpty()) l = split.charAt(0);
		if (l == 0) return s;
		String prefix = s.substring(0, s.indexOf(l) - 1);
		s = s.replaceAll(prefix, "");
		return s.trim();
	}

	@Override
	public void enterBooleanAttribute(@NotNull BooleanAttributeContext ctx) {
		NodeAttribute variable = new NodeAttribute(ctx.BOOLEAN_TYPE().getText(), ctx.IDENTIFIER().getText());
		variable.setList(ctx.LIST() != null);
		if (ctx.EMPTY() != null) variable.setValue(Variable.EMPTY);
		addAttribute(ctx, variable);
	}

	@Override
	public void enterCoordinateAttribute(@NotNull CoordinateAttributeContext ctx) {
		NodeAttribute variable = new NodeAttribute(ctx.COORDINATE_TYPE().getText(), ctx.IDENTIFIER().getText());
		if (ctx.coordinateValue() != null) variable.setValue(ctx.coordinateValue().getText());
		else if (ctx.EMPTY() != null) variable.setValue(Variable.EMPTY);
		variable.setList(ctx.LIST() != null);
		addAttribute(ctx, variable);

	}

	@Override
	public void enterPortAttribute(@NotNull PortAttributeContext ctx) {
		NodeAttribute variable = new NodeAttribute(ctx.PORT_TYPE().getText(), ctx.IDENTIFIER().getText());
		if (ctx.codeValue() != null) variable.setValue(ctx.codeValue().getText());
		else if (ctx.EMPTY() != null) variable.setValue(Variable.EMPTY);
		variable.setList(ctx.LIST() != null);
		addAttribute(ctx, variable);
	}

	@Override
	public void enterResource(@NotNull ResourceContext ctx) {
		Resource variable = new Resource(ctx.attributeType().getText(), ctx.IDENTIFIER().getText());
		addAttribute(ctx, variable);
	}

	@Override
	public void enterWord(@NotNull WordContext ctx) {
		NodeWord variable = new NodeWord(ctx.IDENTIFIER().getText());
		int defaultWord = -1;
		int i = -1;
		for (WordNamesContext word : ctx.wordNames()) {
			i++;
			variable.add(word.IDENTIFIER().getText());
			if (word.STAR() != null) defaultWord = i;
		}
		variable.setDefaultWord((short) defaultWord);
		addAttribute(ctx, variable);
	}

	@Override
	public void enterReference(@NotNull ReferenceContext ctx) {
		Reference variable = new Reference(ctx.identifierReference().getText(), ctx.IDENTIFIER().getText());
		variable.setList(ctx.LIST() != null);
		if (ctx.EMPTY() != null) variable.setEmpty(true);
		addAttribute(ctx, variable);
	}

	private void addAttribute(ParserRuleContext ctx, Variable attribute) {
		NodeObject object = conceptStack.peek().getObject();
		attribute.setDoc(currentDocAttribute);
		if (ctx.getParent().getParent().getParent() instanceof FacetTargetContext) {
			List<NodeObject> targets = object.getFacetTargets();
			targets.get(targets.size() - 1).add(attribute);
		} else object.add(attribute);
	}

	@Override
	public void enterAnnotations(@NotNull AnnotationsContext ctx) {
		if (ctx.getParent() instanceof AttributeContext) {
			processVariableAnnotation(ctx);
			return;
		}
		for (int i = 0; i < ctx.REQUIRED().size(); i++)
			conceptStack.peek().getObject().add(Annotation.REQUIRED);
		for (int i = 0; i < ctx.PROPERTY().size(); i++)
			conceptStack.peek().getObject().add(Annotation.PROPERTY);
		for (int i = 0; i < ctx.SINGLE().size(); i++)
			conceptStack.peek().getObject().add(Annotation.SINGLE);
		for (int i = 0; i < ctx.TERMINAL().size(); i++)
			conceptStack.peek().getObject().add(Annotation.TERMINAL);
		for (int i = 0; i < ctx.COMPONENT().size(); i++)
			conceptStack.peek().getObject().add(Annotation.COMPONENT);
		for (int i = 0; i < ctx.PRIVATE().size(); i++)
			conceptStack.peek().getObject().add(Annotation.PRIVATE);
		for (int i = 0; i < ctx.NAMED().size(); i++)
			conceptStack.peek().getObject().add(Annotation.NAMED);
		for (int i = 0; i < ctx.FACET().size(); i++)
			conceptStack.peek().getObject().add(Annotation.FACET);
		for (int i = 0; i < ctx.INTENTION().size(); i++)
			conceptStack.peek().getObject().add(Annotation.INTENTION);
	}

	private void processVariableAnnotation(AnnotationsContext ctx) {
		List<Variable> variables = conceptStack.peek().getObject().getVariables();
		Variable variable = variables.get(variables.size() - 1);
		variable.setTerminal(!ctx.TERMINAL().isEmpty());
		variable.setProperty(!ctx.PROPERTY().isEmpty());
	}
}
