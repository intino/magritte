package siani.tara.compiler.parser.antlr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.*;

import java.util.*;

import static siani.tara.compiler.model.Primitives.*;
import static siani.tara.compiler.parser.antlr.TaraGrammar.*;

public class ModelGenerator extends TaraGrammarBaseListener {

	private final String file;
	private final Deque<NodeContainer> deque = new ArrayDeque<>();
	private final Set<String> imports = new HashSet<>();
	private final String currentDocAttribute = "";
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
		if (ctx.signature().annotations() != null)
			node.addAnnotations(resolveAnnotations(ctx.signature().annotations()));
		addHeaderInformation(ctx, node);
		deque.push(node);
	}

	private NodeContainer resolveContainer(Node node) {
		NodeContainer context = deque.peek();
		if (node.isSub()) return context.getContainer();
		else return context;
	}

	private void resolveParent(NodeContext ctx, NodeImpl node) {
		if (node.isSub()) {
			Node peek = (Node) deque.peek();
			if (!peek.isAbstract()) peek.addAnnotations("abstract");
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

	private void addHeaderInformation(ParserRuleContext ctx, Node node) {
		node.setLine(ctx.getStart().getLine());
		node.setFile(file);
		node.addImports(imports);
	}

	@Override
	public void exitNode(@NotNull NodeContext ctx) {
		deque.pop();
	}

	@Override
	public void enterFacetApply(@NotNull FacetApplyContext ctx) {
		Facet facet = new FacetImpl(ctx.metaidentifier(0).getText());
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
		NodeImpl node = (NodeImpl) deque.peek();
		FacetTarget facetTarget = new FacetTargetImpl();
		facetTarget.setDestiny(ctx.identifierReference().getText());
		node.addFacetTargets(facetTarget);
		deque.push(facetTarget);
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
//		deque.peek().addDoc(trim); //TODO
	}

	@Override
	public void enterExplicitParameter(@NotNull ExplicitParameterContext ctx) {
		int position = ((ParametersContext) ctx.getParent()).explicitParameter().indexOf(ctx);
		addParameter(ctx.IDENTIFIER().getText(), position, resolveValue(ctx.value()));
	}

	@Override
	public void enterImplicitParameter(@NotNull ImplicitParameterContext ctx) {
		int position = ((ParametersContext) ctx.getParent()).implicitParameter().indexOf(ctx);
		addParameter("", position, resolveValue(ctx.value()));
	}

	public void addParameter(String name, int position, Object[] values) {
		Parameterized object = (Parameterized) deque.peek();
		object.addParameter(name, position, values);
	}

	@Override
	public void enterNodeReference(@NotNull NodeReferenceContext ctx) {
		NodeContainer container = deque.peek();
		NodeReference nodeReference = new NodeReference(ctx.identifierReference().getText());
		nodeReference.setHas(true);
		if (ctx.annotations() != null) nodeReference.addAnnotations(resolveAnnotations(ctx.annotations()));
		nodeReference.setContainer(container);
		container.addIncludedNodes(nodeReference);
		addHeaderInformation(ctx, nodeReference);
	}

	private String[] resolveAnnotations(AnnotationsContext annotations) {
		List<String> values = new ArrayList<>();
		for (AnnotationContext annotationContext : annotations.annotation())
			values.add(annotationContext.getText());
		return values.toArray(new String[values.size()]);
	}


	@Override
	public void enterAddress(@NotNull AddressContext ctx) {
		((Node) deque.peek()).setAddress(Long.parseLong(ctx.getText().substring(1).replace(".", "")));
	}

	@Override
	public void enterVariable(@NotNull VariableContext ctx) {
		NodeContainer container = deque.peek();
		VariableImpl variable = new VariableImpl(ctx.variableType().getText(), ctx.IDENTIFIER().getText());
		if (ctx.value() != null)
			variable.addDefaultValues(resolveValue(ctx.value()));
		container.addVariables(variable);
	}

	@Override
	public void enterVarInit(@NotNull VarInitContext ctx) {
		addParameter(ctx.IDENTIFIER().getText(), -1, resolveValue(ctx.value()));
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

	public Model getModel() {
		return model;
	}
}