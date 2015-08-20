package tara.compiler.parser.antlr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import tara.compiler.model.*;
import tara.language.grammar.TaraGrammar;
import tara.language.grammar.TaraGrammar.*;
import tara.language.grammar.TaraGrammarBaseListener;
import tara.language.model.*;

import java.util.*;
import java.util.stream.Collectors;

import static tara.language.model.Primitives.*;

public class ModelGenerator extends TaraGrammarBaseListener {

	private final String file;
	private final Deque<NodeContainer> deque = new ArrayDeque<>();
	private final Set<String> uses = new HashSet<>();
	private final Model model;

	public ModelGenerator(String file) {
		model = new Model(file);
		deque.add(model);
		this.file = file;
	}

	@Override
	public void enterAnImport(@NotNull TaraGrammar.AnImportContext ctx) {
		uses.add(ctx.headerReference().getText());
	}

	@Override
	public void enterDslDeclaration(@NotNull TaraGrammar.DslDeclarationContext ctx) {
		if (ctx.headerReference() != null) model.language(ctx.headerReference().getText());
	}

	@Override
	public void enterNode(@NotNull NodeContext ctx) {
		NodeImpl node = new NodeImpl();
		node.language(model.language());
		node.setSub(ctx.signature().SUB() != null);
		NodeContainer container = resolveContainer(node);
		container.add(node);
		node.container(container);
		if (ctx.signature().IDENTIFIER() != null)
			node.name(ctx.signature().IDENTIFIER().getText());
		node.type(node.isSub() ?
			deque.peek().type() :
			ctx.signature().metaidentifier().getText());
		resolveParent(ctx, node);
		addTags(ctx.signature().tags(), node);
		addHeaderInformation(ctx, node);
		node.addUses(new ArrayList<>(uses));
		deque.push(node);
	}

	private void addTags(@NotNull TaraGrammar.TagsContext ctx, Node node) {
		if (ctx == null) return;
		if (ctx.flags() != null) node.addFlags(resolveTags(ctx.flags()));
		if (ctx.annotations() != null) node.addAnnotations(resolveTags(ctx.annotations()));
	}

	private NodeContainer resolveContainer(Node node) {
		NodeContainer context = deque.peek();
		if (node.isSub()) return context.container();
		else return context;
	}

	private void resolveParent(NodeContext ctx, NodeImpl node) {
		if (node.isSub()) {
			Node peek = (Node) deque.peek();
			if (!peek.isAbstract()) peek.addFlags(Tag.ABSTRACT);
			node.setParent(peek);
			peek.addChild(node);
			node.setParentName(peek.name());
		} else node.setParentName(getParent(ctx));
	}

	private String getParent(NodeContext ctx) {
		if (ctx.signature().parent() == null) return null;
		IdentifierReferenceContext identifierReference = ctx.signature().parent().identifierReference();
		return identifierReference != null ? identifierReference.getText() : null;
	}

	private void addHeaderInformation(ParserRuleContext ctx, Element element) {
		element.line(ctx.getStart().getLine());
		element.file(file);
	}

	@Override
	public void exitNode(@NotNull NodeContext ctx) {
		NodeContainer peek = deque.peek();
		if (((Node) peek).isMain())
			peek.moveToTheTop();
		deque.pop();
	}

	@Override
	public void enterFacetApply(@NotNull FacetApplyContext ctx) {
		FacetImpl facet = new FacetImpl(ctx.metaidentifier().getText());
		addHeaderInformation(ctx, facet);
		facet.setUses(new ArrayList<>(uses));
		Node peek = (Node) deque.peek();
		peek.addFacets(facet);
		facet.container(peek);
		deque.push(facet);
	}

	@Override
	public void exitFacetApply(@NotNull FacetApplyContext ctx) {
		deque.poll();
	}

	@Override
	public void enterFacetTarget(@NotNull FacetTargetContext ctx) {
		NodeImpl peek = getNodeContainer();
		FacetTargetImpl facetTarget = new FacetTargetImpl();
		addHeaderInformation(ctx, facetTarget);
		facetTarget.setUses(new ArrayList<>(uses));
		facetTarget.target(ctx.identifierReference().getText());
		if (ctx.with() != null) facetTarget.constraints(collectConstrains(ctx.with().identifierReference()));
		peek.addFacetTargets(facetTarget);
		facetTarget.container(peek);
		deque.push(facetTarget);
	}

	private List<String> collectConstrains(List<IdentifierReferenceContext> contexts) {
		return contexts.stream().map(IdentifierReferenceContext::getText).collect(Collectors.toList());
	}

	private NodeImpl getNodeContainer() {
		NodeContainer peek = deque.peek();
		while (!(peek instanceof NodeImpl))
			peek = peek.container();
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
			builder.append(doc.getText().substring(2));
		String trim = builder.toString().trim();
		deque.peek().addDoc(trim);
	}

	@Override
	public void enterParameter(@NotNull ParameterContext ctx) {
		int position = ((ParametersContext) ctx.getParent()).parameter().indexOf(ctx);
		String extension = ctx.value().measureValue() != null ? ctx.value().measureValue().getText() : null;
		addParameter(ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : "", position, extension, resolveValue(ctx.value()));
	}

	public void addParameter(String name, int position, String measureValue, Object[] values) {
		Parametrized object = (Parametrized) deque.peek();
		object.addParameter(name, position, measureValue, values);
	}

	@Override
	public void enterNodeReference(@NotNull NodeReferenceContext ctx) {
		NodeContainer container = deque.peek();
		NodeReference nodeReference = new NodeReference(ctx.identifierReference().getText());
		nodeReference.addUses(new ArrayList<>(uses));
		addHeaderInformation(ctx, nodeReference);
		nodeReference.setHas(true);
		addTags(ctx.tags(), nodeReference);
		nodeReference.container(container);
		container.add(nodeReference);
	}

	private Tag[] resolveTags(AnnotationsContext annotations) {
		List<Tag> values = new ArrayList<>();
		if (annotations == null) return new Tag[0];
		values.addAll(annotations.annotation().stream().map(a -> Tag.valueOf(a.getText().toUpperCase())).collect(Collectors.toList()));
		return values.toArray(new Tag[values.size()]);
	}

	private Tag[] resolveTags(FlagsContext flags) {
		List<Tag> values = new ArrayList<>();
		if (flags == null) return new Tag[0];
		values.addAll(flags.flag().stream().map(f -> Tag.valueOf(f.getText().toUpperCase())).collect(Collectors.toList()));
		return values.toArray(new Tag[values.size()]);
	}


	@Override
	public void enterPlate(@NotNull PlateContext ctx) {
		((Node) deque.peek()).plate(ctx.getText().substring(1));
	}

	@Override
	public void enterVariable(@NotNull VariableContext ctx) {
		NodeContainer container = deque.peek();
		Variable variable = createVariable(ctx, container);
		if ("word".equals(ctx.variableType().getText()))
			processAsWord(variable, ctx);
		else {
			addValue(variable, ctx);
			if (ctx.contract() != null) variable.contract(ctx.contract().getText().substring(1));
		}
		addHeaderInformation(ctx, variable);
		variable.addFlags(resolveTags(ctx.flags()));
		container.add(variable);
	}

	private Variable createVariable(@NotNull VariableContext ctx, NodeContainer container) {
		VariableTypeContext variableType = ctx.variableType();
		Variable variable = variableType.identifierReference() != null ?
			new VariableReference(container, variableType.getText(), ctx.IDENTIFIER().getText()) :
			new VariableImpl(container, variableType.getText(), ctx.IDENTIFIER().getText());
		if (ctx.LIST() != null) variable.size(0);
		if (ctx.count() != null) variable.size(Integer.parseInt(ctx.count().NATURAL_VALUE().getText()));
		return variable;
	}

	private void addValue(Variable variable, @NotNull VariableContext ctx) {
		if (ctx.value() == null) return;
		variable.addDefaultValues(resolveValue(ctx.value()));
		if (ctx.value().measureValue() != null) variable.defaultExtension(ctx.value().measureValue().getText());
	}

	private void processAsWord(Variable variable, VariableContext context) {
		ContractValueContext contract = context.contract().contractValue();
		if (contract.LEFT_SQUARE() != null)
			for (TerminalNode value : contract.IDENTIFIER())
				variable.addAllowedValues(value.getText());
		else variable.contract(contract.getText());
		if (context.value() == null) return;
		for (IdentifierReferenceContext id : context.value().identifierReference())
			variable.addDefaultValues(id.getText());
	}

	@Override
	public void enterVarInit(@NotNull VarInitContext ctx) {
		String extension = ctx.value().measureValue() != null ? ctx.value().measureValue().getText() : null;
		addParameter(ctx.IDENTIFIER().getText(), -1, extension, resolveValue(ctx.value()));
	}

	private Object[] resolveValue(ValueContext ctx) {
		List<Object> values = new ArrayList<>();
		if (!ctx.booleanValue().isEmpty())
			values.addAll(ctx.booleanValue().stream().
				map(context -> getConverter(BOOLEAN).convert(context.getText())[0]).collect(Collectors.toList()));
		else if (!ctx.integerValue().isEmpty())
			values.addAll(ctx.integerValue().stream().
				map(context -> getConverter(INTEGER).convert(context.getText())[0]).collect(Collectors.toList()));
		else if (!ctx.doubleValue().isEmpty())
			values.addAll(ctx.doubleValue().stream().
				map(context -> getConverter(DOUBLE).convert(context.getText())[0]).collect(Collectors.toList()));
		else if (!ctx.naturalValue().isEmpty())
			values.addAll(ctx.naturalValue().stream().
				map(context -> getConverter(NATURAL).convert(context.getText())[0]).collect(Collectors.toList()));
		else if (!ctx.tupleValue().isEmpty())
			values.addAll(ctx.tupleValue().stream().
				map(context -> new AbstractMap.SimpleEntry<>(context.stringValue().getText(), getConverter(DOUBLE).convert(context.doubleValue().getText())[0])).collect(Collectors.toList()));
		else if (!ctx.stringValue().isEmpty())
			values.addAll(ctx.stringValue().stream().
				map(context -> formatString(context.getText())).collect(Collectors.toList()));
		else if (!ctx.identifierReference().isEmpty())
			values.addAll(ctx.identifierReference().stream().
				map(context -> Parameter.REFERENCE + context.getText()).collect(Collectors.toList()));
		else if (!ctx.expression().isEmpty())
			values.addAll(ctx.expression().stream().
				map(context -> new Primitives.Expression(formatExpression(context.getText()).trim())).collect(Collectors.toList()))
				;
		else if (ctx.EMPTY() != null)
			values.add(new EmptyNode());
		return values.toArray(new Object[values.size()]);
	}

	private String formatExpression(String value) {
		if (!value.trim().startsWith("--")) return value.substring(1, value.length() - 1).replace("\\\"", "\"");
		String text = value.replace("\t", "    ");
		if (value.startsWith("\n")) text = text.substring(1);
		String pattern = text.substring(0, text.indexOf("\n")).replace("-", "");
		text = value.trim().replaceAll("--(-*)\\n", "").replaceAll("--(-*)", "");
		String result = "";
		for (String line : text.split("\\n")) result += line.replaceFirst(pattern, "") + "\n";
		while (result.endsWith("\n")) result = result.substring(0, result.length() - 1);
		return result;
	}

	private String formatString(String value) {
		if (!value.trim().startsWith("==")) return value.substring(1, value.length() - 1).replace("\\\"", "\"");
		String text = value.replace("\t", "    ");
		if (value.startsWith("\n")) text = text.substring(1);
		String pattern = text.substring(0, text.indexOf("\n")).replace("-", "");
		text = value.trim().replaceAll("==(=*)\\n", "").replaceAll("==(=*)", "");
		String result = "";
		for (String line : text.split("\\n")) result += line.replaceFirst(pattern, "") + "\n";
		while (result.endsWith("\n")) result = result.substring(0, result.length() - 1);
		return result;
	}

	public Model getModel() {
		model.setUses(new ArrayList<>(uses));
		return model;
	}
}