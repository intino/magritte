package siani.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.*;
import siani.tara.semantic.model.Tag;

import java.util.*;

import static siani.tara.compiler.model.Primitives.*;
import static siani.tara.compiler.parser.antlr.TaraGrammar.*;

public class ModelGenerator extends TaraGrammarBaseListener {

	private final String file;
	private final Deque<NodeContainer> deque = new ArrayDeque<>();
	private final Set<String> imports = new HashSet<>();
	private final Model model;

	public ModelGenerator(String file) {
		model = new Model(file);
		deque.add(model);
		this.file = file;
	}

	@Override
	public void enterAnImport(@NotNull AnImportContext ctx) {
		imports.add(ctx.headerReference().getText());
	}

	@Override
	public void enterDslDeclaration(@NotNull DslDeclarationContext ctx) {
		if (ctx.headerReference() != null) model.setLanguage(ctx.headerReference().getText());
	}

	@Override
	public void enterNode(@NotNull NodeContext ctx) {
		NodeImpl node = new NodeImpl();
		node.setSub(ctx.signature().SUB() != null);
		NodeContainer container = resolveContainer(node);
		container.addIncludedNodes(node);
		node.setContainer(container);
		if (ctx.signature().IDENTIFIER() != null)
			node.setName(ctx.signature().IDENTIFIER().getText());
		node.setType(node.isSub() ?
			((Node) deque.peek()).getType() :
			ctx.signature().metaidentifier().getText());
		resolveParent(ctx, node);
		addTags(ctx.signature().tags(), node);
		addHeaderInformation(ctx, node);
		node.addImports(imports);
		deque.push(node);
	}

	private void addTags(@NotNull TagsContext ctx, Node node) {
		if (ctx == null) return;
		if (ctx.flags() != null) node.addFlags(resolveTags(ctx.flags()));
		if (ctx.annotations() != null) node.addAnnotations(resolveTags(ctx.annotations()));
	}

	private NodeContainer resolveContainer(Node node) {
		NodeContainer context = deque.peek();
		if (node.isSub()) return context.getContainer();
		else return context;
	}

	private void resolveParent(NodeContext ctx, NodeImpl node) {
		if (node.isSub()) {
			Node peek = (Node) deque.peek();
			if (!peek.isAbstract()) peek.addFlags(Tag.ABSTRACT.name());
			node.setParent(peek);
			peek.addChild(node);
			node.setParentName(peek.getName());
		} else node.setParentName(getParent(ctx));
	}

	private String getParent(NodeContext ctx) {
		if (ctx.signature().parent() == null) return null;
		IdentifierReferenceContext identifierReference = ctx.signature().parent().identifierReference();
		return identifierReference != null ? identifierReference.getText() : null;
	}

	private void addHeaderInformation(ParserRuleContext ctx, Element element) {
		element.setLine(ctx.getStart().getLine());
		element.setFile(file);
	}

	@Override
	public void exitNode(@NotNull NodeContext ctx) {
		NodeContainer peek = deque.peek();
		if (((Node) peek).isRoot())
			peek.moveToTheTop();
		deque.pop();
	}

	@Override
	public void enterFacetApply(@NotNull FacetApplyContext ctx) {
		Facet facet = new FacetImpl(ctx.metaidentifier(0).getText());
		addHeaderInformation(ctx, (Element) facet);
		Node peek = (Node) deque.peek();
		peek.addFacets(facet);
		facet.setContainer(peek);
		deque.push(facet);
	}

	@Override
	public void exitFacetApply(@NotNull FacetApplyContext ctx) {
		deque.poll();
	}

	@Override
	public void enterFacetTarget(@NotNull FacetTargetContext ctx) {
		NodeImpl peek = getNodeContainer();
		FacetTarget facetTarget = new FacetTargetImpl();
		addHeaderInformation(ctx, (Element) facetTarget);
		facetTarget.setTarget(ctx.identifierReference().getText());
		peek.addFacetTargets(facetTarget);
		facetTarget.setContainer(peek);
		deque.push(facetTarget);
	}

	private NodeImpl getNodeContainer() {
		NodeContainer peek = deque.peek();
		while (!(peek instanceof NodeImpl))
			peek = peek.getContainer();
		return (NodeImpl) peek;
	}

	@Override
	public void exitFacetTarget(@NotNull FacetTargetContext ctx) {
		deque.poll();
	}

	@Override
	public void enterDoc(@NotNull DocContext ctx) {
		StringBuilder builder = new StringBuilder();
		for (TerminalNode doc : ctx.DOC())
			builder.append(doc.getText().substring(4));
		String trim = builder.toString().trim();
		deque.peek().setDoc(trim);
	}

	@Override
	public void enterExplicitParameter(@NotNull ExplicitParameterContext ctx) {
		int position = ((ParametersContext) ctx.getParent()).explicitParameter().indexOf(ctx);
		String extension = ctx.value().measureValue() != null ? ctx.value().measureValue().getText() : null;
		addParameter(ctx.IDENTIFIER().getText(), position, extension, resolveValue(ctx.value()));
	}

	@Override
	public void enterImplicitParameter(@NotNull ImplicitParameterContext ctx) {
		int position = ((ParametersContext) ctx.getParent()).implicitParameter().indexOf(ctx);
		String contract = ctx.value().measureValue() != null ? ctx.value().measureValue().getText() : null;
		addParameter("", position, contract, resolveValue(ctx.value()));
	}

	public void addParameter(String name, int position, String measureValue, Object[] values) {
		Parametrized object = (Parametrized) deque.peek();
		object.addParameter(name, position, measureValue, values);
	}

	@Override
	public void enterNodeReference(@NotNull NodeReferenceContext ctx) {
		NodeContainer container = deque.peek();
		NodeReference nodeReference = new NodeReference(ctx.identifierReference().getText());
		addHeaderInformation(ctx, nodeReference);
		nodeReference.setHas(true);
		addTags(ctx.tags(), nodeReference);
		nodeReference.setContainer(container);
		container.addIncludedNodes(nodeReference);
	}

	private String[] resolveTags(AnnotationsContext annotations) {
		List<String> values = new ArrayList<>();
		if (annotations == null) return new String[0];
		for (AnnotationContext annotationContext : annotations.annotation())
			values.add(annotationContext.getText());
		return values.toArray(new String[values.size()]);
	}

	private String[] resolveTags(FlagsContext flags) {
		List<String> values = new ArrayList<>();
		if (flags == null) return new String[0];
		for (FlagContext flagContext : flags.flag())
			values.add(flagContext.getText());
		return values.toArray(new String[values.size()]);
	}


	@Override
	public void enterPlate(@NotNull PlateContext ctx) {
		((Node) deque.peek()).setPlate(ctx.getText().substring(1));
	}

	@Override
	public void enterVariable(@NotNull VariableContext ctx) {
		NodeContainer container = deque.peek();
		VariableTypeContext variableType = ctx.variableType();
		Variable variable = variableType.identifierReference() != null ?
			new VariableReference(container, variableType.getText(), ctx.IDENTIFIER().getText()) :
			new VariableImpl(container, variableType.getText(), ctx.IDENTIFIER().getText());
		variable.setMultiple(ctx.LIST() != null);
		if (ctx.word() != null) processAsWord(variable, ctx.word());
		else if (ctx.value() != null) {
			variable.addDefaultValues(resolveValue(ctx.value()));
			if (ctx.value().measureValue() != null)
				variable.setDefaultExtension(ctx.value().measureValue().getText());
		}
		if (ctx.nativeName() != null) variable.setContract(ctx.nativeName().getText().substring(1));

		addHeaderInformation(ctx, (Element) variable);
		for (FlagsContext flagsContext : ctx.flags()) variable.addFlags(resolveTags(flagsContext));
		container.addVariables(variable);
	}

	private void processAsWord(Variable variable, WordContext word) {
		for (WordValueContext value : word.wordValue()) {
			variable.addAllowedValues(value.IDENTIFIER().getText());
			if (value.STAR() != null) variable.addDefaultValues(value.IDENTIFIER().getText());
		}
	}

	@Override
	public void enterVarInit(@NotNull VarInitContext ctx) {
		String extension = ctx.value().measureValue() != null ? ctx.value().measureValue().getText() : null;
		addParameter(ctx.IDENTIFIER().getText(), -1, extension, resolveValue(ctx.value()));
	}

	private Object[] resolveValue(ValueContext ctx) {
		List<Object> values = new ArrayList<>();
		if (!ctx.booleanValue().isEmpty())
			for (BooleanValueContext context : ctx.booleanValue())
				values.add(getConverter(BOOLEAN).convert(context.getText())[0]);
		else if (!ctx.integerValue().isEmpty())
			for (IntegerValueContext context : ctx.integerValue())
				values.add(getConverter(INTEGER).convert(context.getText())[0]);
		else if (!ctx.doubleValue().isEmpty())
			for (DoubleValueContext context : ctx.doubleValue())
				values.add(getConverter(DOUBLE).convert(context.getText())[0]);
		else if (!ctx.naturalValue().isEmpty())
			for (NaturalValueContext context : ctx.naturalValue())
				values.add(getConverter(NATURAL).convert(context.getText())[0]);
		else if (!ctx.stringValue().isEmpty())
			for (StringValueContext context : ctx.stringValue())
				values.add(formatText(context.getText()));
		else if (!ctx.identifierReference().isEmpty())
			for (IdentifierReferenceContext context : ctx.identifierReference())
				values.add("reference:" + context.getText());
		else if (ctx.EMPTY() != null)
			values.add(new EmptyNode());
		return values.toArray(new Object[values.size()]);
	}

	private String formatText(String value) {
		if (!value.trim().startsWith("--")) return value.substring(1, value.length() - 1).replace("\\\"","\"");
		String text = value.replace("\t", "    ");
		if (value.startsWith("\n")) text = text.substring(1);
		String pattern = text.substring(0, text.indexOf("\n")).replace("-", "");
		text = value.trim().replaceAll("--(-*)\\n", "").replaceAll("--(-*)", "");
		String result = "";
		for (String line : text.split("\\n")) result += line.replaceFirst(pattern, "") + "\n";
		while (result.endsWith("\n")) result = result.substring(0, result.length() - 1);
		return result;
	}

	public Model getModel() {
		return model;
	}
}