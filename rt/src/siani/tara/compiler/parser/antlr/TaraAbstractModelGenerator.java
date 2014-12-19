package siani.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import siani.tara.lang.*;
import siani.tara.lang.util.ModelLoader;

import java.util.*;

import static siani.tara.compiler.parser.antlr.TaraGrammar.*;
import static siani.tara.lang.Primitives.*;

public class TaraAbstractModelGenerator extends TaraGrammarBaseListener {

	private final String file;
	private final String modelsPath;
	Model model;
	Stack<Node> conceptStack = new Stack<>();
	Stack<FacetTarget> facetTargetStack = new Stack<>();
	Set<String> imports = new HashSet<>();
	String currentDocAttribute = "";

	public TaraAbstractModelGenerator(Model model, String file, String modelsPath) {
		this.model = model;
		this.file = file;
		this.modelsPath = modelsPath;
	}

	@Override
	public void enterAnImport(@NotNull AnImportContext ctx) {
		String name = ctx.headerReference().getText();
		if (ctx.IDENTIFIER() != null) {
			model.setParentModelName(name);
			model.setParentModel(ModelLoader.load(modelsPath, name));
		} else imports.add(name);
	}

	@Override
	public void enterConcept(@NotNull ConceptContext ctx) {
		DeclaredNode container = (!conceptStack.empty() ? (DeclaredNode) conceptStack.peek() : null);
		Node node;
		String name = (ctx.signature().IDENTIFIER() != null) ? ctx.signature().IDENTIFIER().getText() : "";
		String type = (ctx.signature().metaidentifier() != null) ?
			ctx.signature().metaidentifier().getText() : container.getObject().getType();
		String parent = getParent(ctx);
		node = name.isEmpty() && ctx.body() == null && parent != null ? new LinkNode(parent, container) :
			new DeclaredNode(new NodeObject(type, name), container);
		addHeaderInformation(ctx, node);
		addNodeToModel(ctx, node, parent);
		node.calculateQualifiedName();
		if (node instanceof DeclaredNode) node.getObject().setDeclaredNodeQN(node.getQualifiedName());
		conceptStack.push(node);
	}

	private boolean isInFacetTarget(ConceptContext ctx) {
		return ctx.getParent().getParent() instanceof FacetTargetContext;
	}

	private void addNodeToModel(ConceptContext concept, Node node, String parent) {
		DeclaredNode container = node.getContainer();
		if (node.is(DeclaredNode.class)) {
			if (container != null) {
				if (isSub(concept)) {
					node.getObject().setSub(true);
					node.getObject().setParentName(node.getContainer().getQualifiedName());
					node.getObject().setParentObject(container.getObject());
					container.getObject().add((DeclaredNode) node);
					container.add(node);
				} else if (isInFacetTarget(concept)) {
					List<FacetTarget> facetTargets = conceptStack.peek().getObject().getFacetTargets();
					facetTargets.get(facetTargets.size() - 1).add(node);
					((DeclaredNode) node).setFacetTargetParent(facetTargets.get(facetTargets.size() - 1));
				} else container.add(node);
			} else model.add(node);
			if (parent != null) node.getObject().setParentName(parent);
		} else
			container.add(node);
	}

	private boolean isSub(ConceptContext ctx) {
		return ctx.signature().SUB() != null;
	}

	@Override
	public void exitConcept(@NotNull ConceptContext ctx) {
		Node node = conceptStack.peek();
		if (node instanceof DeclaredNode) {
			if (hasSubs(node)) node.getObject().add(Annotation.ABSTRACT);
			else if (!node.is(Annotation.ABSTRACT)) model.addIdentifier(node.getObject().getName());
		}
		model.add(node.getQualifiedName(), node);
		conceptStack.pop();
	}

	private boolean hasSubs(Node node) {
		for (Node inner : node.getInnerNodes()) if (inner.is(DeclaredNode.class) && inner.isSub()) return true;
		return false;
	}

	private void addHeaderInformation(ParserRuleContext ctx, Node node) {
		node.setLine(ctx.getStart().getLine());
		node.setFile(file);
		node.addImports(imports);
	}

	private String getParent(ConceptContext ctx) {
		IdentifierReferenceContext identifierReference = ctx.signature().identifierReference();
		return identifierReference != null ? identifierReference.getText() : null;
	}

	@Override
	public void enterFacetApply(@NotNull FacetApplyContext ctx) {
		NodeObject nodeObject = conceptStack.peek().getObject();
		List<MetaidentifierContext> names = ctx.metaidentifier();
		nodeObject.addFacet(new Facet(names.get(0).getText(), names.size() == 2 ? names.get(1).getText() : null));
	}

	@Override
	public void enterFacetTarget(@NotNull FacetTargetContext ctx) {
		NodeObject nodeObject = conceptStack.peek().getObject();
		FacetTarget facetTarget = new FacetTarget(ctx.identifierReference().getText(),
			!facetTargetStack.isEmpty() ? facetTargetStack.peek() : null);
		nodeObject.addFacetTarget(facetTarget);
		facetTargetStack.push(facetTarget);
	}

	@Override
	public void exitFacetTarget(@NotNull FacetTargetContext ctx) {
		facetTargetStack.pop();
	}

	@Override
	public void enterDoc(@NotNull DocContext ctx) {
		StringBuilder builder = new StringBuilder();
		for (TerminalNode doc : ctx.DOC())
			builder.append(doc.getText().substring(4));
		String trim = builder.toString().trim();
		conceptStack.peek().getObject().addDoc(trim);
	}

	@Override
	public void enterParameterList(@NotNull ParameterListContext ctx) {
		boolean explicit = ctx.explicit().isEmpty();
		for (int i = 0; i < ctx.initValue().size(); i++) {
			String name = explicit ? "" + i : ctx.explicit(i).IDENTIFIER().getText();
			addParameter(name, ctx.initValue(i));
		}
	}

	public void addParameter(String name, @NotNull InitValueContext ctx) {
		NodeObject object = conceptStack.peek().getObject();
		Variable valueOfInit = getValueOfInit(name, ctx);
		if (inFacetApply(ctx)) {
			List<Facet> facetApplies = object.getFacets();
			facetApplies.get(facetApplies.size() - 1).add(name, valueOfInit);
		} else
			object.addParameter(name, valueOfInit);
	}

	private boolean inFacetApply(InitValueContext ctx) {
		return ctx.getParent().getParent().getParent() instanceof FacetApplyContext;
	}

	@Override
	public void enterConceptReference(@NotNull ConceptReferenceContext ctx) {
		String parent = ctx.identifierReference().getText();
		LinkNode node = new LinkNode(parent, (DeclaredNode) conceptStack.peek());
		node.setReference(true);
		addHeaderInformation(ctx, node);
		((DeclaredNode) conceptStack.peek()).add(node);

	}

	@Override
	public void exitConceptReference(@NotNull ConceptReferenceContext ctx) {
		List<Node> innerNodes = conceptStack.peek().getInnerNodes();
		Node node = innerNodes.get(innerNodes.size() - 1);
		node.calculateQualifiedName();
		model.add(node.getQualifiedName(), node);
	}

	@Override
	public void enterIntegerAttribute(@NotNull IntegerAttributeContext ctx) {
		super.enterIntegerAttribute(ctx);
		Attribute variable = new Attribute(ctx.INT_TYPE().getText(), ctx.IDENTIFIER().getText());
		variable.setList(ctx.LIST() != null);
		if (ctx.integerValue() != null)
			variable.setDefaultValues(getTextArrayOfContextList(ctx.integerValue()));
//		else if (ctx.EMPTY() != null) variable.setDefaultValues(new String[]{Variable.EMPTY});
//		if (ctx.measureValue() != null) variable.setMeasureValue(ctx.measureValue().getText());
		addAttribute(ctx, variable);
	}

	@Override
	public void enterDoubleAttribute(@NotNull DoubleAttributeContext ctx) {
		Attribute variable = new Attribute(ctx.DOUBLE_TYPE().getText(), ctx.IDENTIFIER().getText());
		variable.setList(ctx.LIST() != null);
		if (ctx.count() != null) variable.setCount(Integer.parseInt(ctx.count().naturalValue().getText()));
		if (ctx.doubleValue() != null) variable.setDefaultValues(getTextArrayOfContextList(ctx.doubleValue()));
		if (ctx.doubleMeasure() != null) variable.setMeasureType(ctx.doubleMeasure().getText());
		if (ctx.measureValue() != null) variable.setMeasureValue(ctx.measureValue().getText());
		addAttribute(ctx, variable);
	}

	@Override
	public void enterNaturalAttribute(@NotNull NaturalAttributeContext ctx) {
		super.enterNaturalAttribute(ctx);
		Attribute variable = new Attribute(ctx.NATURAL_TYPE().getText(), ctx.IDENTIFIER().getText());
		if (ctx.naturalValue() != null) variable.setDefaultValues(getTextArrayOfContextList(ctx.naturalValue()));
		if (ctx.measureValue() != null) variable.setMeasureValue(ctx.measureValue().getText());
		variable.setList(ctx.LIST() != null);
		addAttribute(ctx, variable);
	}

	@Override
	public void enterDateAttribute(@NotNull DateAttributeContext ctx) {
		Attribute variable = new Attribute(ctx.DATE_TYPE().getText(), ctx.IDENTIFIER().getText());
		if (ctx.dateValue() != null) variable.setDefaultValues(getTextArrayOfContextList(ctx.dateValue()));
		variable.setList(ctx.LIST() != null);
		addAttribute(ctx, variable);
	}

	@Override
	public void enterMeasureAttribute(@NotNull MeasureAttributeContext ctx) {
		Attribute variable = new Attribute(ctx.MEASURE_TYPE().getText(), ctx.IDENTIFIER().getText());
		variable.setList(ctx.LIST() != null);
		if (ctx.doubleValue() != null) variable.setDefaultValues(getTextArrayOfContextList(ctx.doubleValue()));
		if (ctx.attributeType() != null) variable.setMeasureType(ctx.attributeType().measureType().getText());
		if (ctx.measureValue() != null) variable.setMeasureValue(ctx.measureValue().getText());
		addAttribute(ctx, variable);
	}

	@Override
	public void enterRatioAttribute(@NotNull RatioAttributeContext ctx) {
	}

	@Override
	public void enterStringAttribute(@NotNull StringAttributeContext ctx) {
		Attribute variable = new Attribute(ctx.STRING_TYPE().getText(), ctx.IDENTIFIER().getText());
		if (ctx.stringValue() != null) variable.setDefaultValues(formatValues(ctx.stringValue()));
//		if (ctx.EMPTY() != null) variable.setDefaultValues(new String[]{Variable.EMPTY});
		variable.setList(ctx.LIST() != null);
		addAttribute(ctx, variable);
	}

	private String[] formatValues(List<StringValueContext> values) {
		List<String> strings = new ArrayList<>();
		for (StringValueContext value : values) strings.add(formatText(value.getText()));
		return strings.toArray(new String[strings.size()]);
	}

	private String formatText(String text) {
		if (!text.startsWith("---")) return text.substring(1, text.length() - 1);
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
		Attribute variable = new Attribute(ctx.BOOLEAN_TYPE().getText(), ctx.IDENTIFIER().getText());
		variable.setList(ctx.LIST() != null);
		if (ctx.booleanValue() != null) variable.setDefaultValues(getTextArrayOfContextList(ctx.booleanValue()));
//		if (ctx.EMPTY() != null) variable.setDefaultValues(new String[]{Variable.EMPTY});
		addAttribute(ctx, variable);
	}

	@Override
	public void enterAddress(@NotNull AddressContext ctx) {
		conceptStack.peek().getObject().setAddress(ctx.getText().substring(1));
	}

	@Override
	public void enterResource(@NotNull ResourceContext ctx) {
		Resource variable = new Resource(ctx.attributeType().getText(), ctx.IDENTIFIER().getText());
		addAttribute(ctx, variable);
	}

	@Override
	public void enterWord(@NotNull WordContext ctx) {
		Word word = new Word(ctx.IDENTIFIER().getText());
		int defaultWord = -1;
		int i = -1;
		for (WordNamesContext wordName : ctx.wordNames()) {
			word.add(wordName.IDENTIFIER().getText());
			if (wordName.STAR() != null) defaultWord = ++i;
		}
		if (defaultWord >= 0)
			word.setDefaultValues(new String[]{word.wordTypes.get(defaultWord)});
		if (ctx.LIST() != null) word.setList(true);
		addAttribute(ctx, word);
	}

	@Override
	public void enterReference(@NotNull ReferenceContext ctx) {
		Reference reference = new Reference(ctx.identifierReference().getText(), ctx.IDENTIFIER().getText());
		reference.setList(ctx.LIST() != null);
		if (ctx.EMPTY() != null) reference.setEmpty(true);
		addAttribute(ctx, reference);
	}

	private void addAttribute(ParserRuleContext ctx, Variable attribute) {
		NodeObject object = conceptStack.peek().getObject();
		attribute.setDoc(currentDocAttribute);
		if (ctx.getParent().getParent().getParent() instanceof FacetTargetContext) {
			List<FacetTarget> targets = object.getFacetTargets();
			targets.get(targets.size() - 1).add(attribute);
		} else object.add(attribute);
	}

	@Override
	public void enterVarInit(@NotNull VarInitContext ctx) {
		NodeObject object = conceptStack.peek().getObject();
		String name = ctx.IDENTIFIER().getText();
		object.putVariableInitialisation(name, getValueOfInit(name, ctx.initValue()));
	}

	private Variable getValueOfInit(String name, InitValueContext ctx) {
		Variable variable = null;
		String measure = ctx.measureValue() != null ? ctx.measureValue().getText() : "";
		if (!ctx.booleanValue().isEmpty()) {
			variable = new Attribute(BOOLEAN, name, ctx.booleanValue().size() > 1);
			for (BooleanValueContext context : ctx.booleanValue())
				variable.addValue(getConverter(BOOLEAN).convert(context.getText())[0]);
		} else if (!ctx.integerValue().isEmpty()) {
			variable = new Attribute(INTEGER, name, ctx.integerValue().size() > 1);
			((Attribute) variable).setMeasureValue(measure);
			for (IntegerValueContext context : ctx.integerValue())
				variable.addValue(getConverter(INTEGER).convert(context.getText())[0]);
		} else if (!ctx.doubleValue().isEmpty()) {
			variable = new Attribute(DOUBLE, name, ctx.doubleValue().size() > 1);
			((Attribute) variable).setMeasureValue(measure);
			for (DoubleValueContext context : ctx.doubleValue())
				variable.addValue(getConverter(DOUBLE).convert(context.getText())[0]);
		} else if (!ctx.naturalValue().isEmpty()) {
			variable = new Attribute(NATURAL, name, ctx.naturalValue().size() > 1);
			((Attribute) variable).setMeasureValue(measure);
			for (NaturalValueContext context : ctx.naturalValue())
				variable.addValue(getConverter(NATURAL).convert(context.getText())[0]);
		} else if (!ctx.stringValue().isEmpty()) {
			variable = new Attribute(STRING, name, ctx.stringValue().size() > 1);
			for (StringValueContext context : ctx.stringValue())
				variable.addValue(formatText(context.getText()));
		} else if (!ctx.dateValue().isEmpty()) {
			variable = new Attribute(DATE, name, ctx.dateValue().size() > 1);
			for (DateValueContext context : ctx.dateValue())
				variable.addValue(getConverter(DATE).convert(context.getText())[0]);
		} else if (!ctx.identifierReference().isEmpty()) {
			variable = new Reference(REFERENCE, name, ctx.identifierReference().size() > 1);
			for (IdentifierReferenceContext context : ctx.identifierReference()) variable.addValue(context.getText());
		}
		return variable;
	}

	@Override
	public void enterAnnotations(@NotNull AnnotationsContext ctx) {
		List<Annotation> annotations = getAnnotations(ctx);
		if (ctx.getParent() instanceof VariableContext) {
			List<Variable> variables = conceptStack.peek().getObject().getVariables();
			Variable variable = variables.get(variables.size() - 1);
			variable.addAll(annotations);
		} else if (ctx.getParent() instanceof ConceptReferenceContext) {
			List<Node> innerNodes = conceptStack.peek().getInnerNodes();
			((LinkNode) innerNodes.get(innerNodes.size() - 1)).addAll(annotations);
		} else
			conceptStack.peek().getObject().addAll(annotations);
	}

	private String[] getTextArrayOfContextList(List<? extends ParserRuleContext> ctx) {
		List<String> list = new ArrayList<>();
		for (ParserRuleContext parserRuleContext : ctx) list.add(parserRuleContext.getText());
		return list.toArray(new String[list.size()]);
	}

	private List<Annotation> getAnnotations(AnnotationsContext context) {
		List<Annotation> annotations = new ArrayList<>();
		for (AnnotationContext annotation : context.annotation())
			annotations.add(Annotation.valueOf(annotation.getText().toUpperCase().replace("+", "META_")));
		return annotations;
	}
}