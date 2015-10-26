package tara.compiler.parser.antlr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import tara.compiler.core.errorcollection.SyntaxException;
import tara.compiler.model.*;
import tara.lang.grammar.TaraGrammar;
import tara.lang.grammar.TaraGrammar.*;
import tara.lang.grammar.TaraGrammarBaseListener;
import tara.lang.model.*;
import tara.lang.model.rules.*;

import java.util.*;
import java.util.stream.Collectors;

import static tara.lang.model.Primitive.DOUBLE;
import static tara.lang.model.Primitive.INTEGER;

public class ModelGenerator extends TaraGrammarBaseListener {

	private final String file;
	private final Deque<NodeContainer> deque = new ArrayDeque<>();
	private final Set<String> uses = new HashSet<>();
	private final Model model;
	private List<SyntaxException> errors = new ArrayList<>();

	public ModelGenerator(String file) {
		this.file = file;
		model = new Model(file);
		deque.add(model);
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
		if (!errors.isEmpty()) return;
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
		element.language(model.language());
		element.line(ctx.getStart().getLine());
		element.column(ctx.getStart().getCharPositionInLine());
		element.file(file);
	}

	@Override
	public void exitNode(@NotNull NodeContext ctx) {
		if (!errors.isEmpty()) return;
		NodeContainer peek = deque.peek();
		if (((Node) peek).isMain())
			peek.moveToTheTop();
		deque.pop();
	}

	@Override
	public void enterFacetApply(@NotNull FacetApplyContext ctx) {
		if (!errors.isEmpty()) return;
		FacetImpl facet = new FacetImpl(ctx.metaidentifier().getText());
		addHeaderInformation(ctx, facet);
		facet.setUses(new ArrayList<>(uses));
		if (!(deque.peek() instanceof Node)) {
			addError("Unavailable component facet apply in context " + deque.peek().getClass().getInterfaces()[0].getSimpleName(), ctx);
			return;
		}
		Node peek = (Node) deque.peek();
		peek.addFacets(facet);
		facet.container(peek);
		deque.push(facet);
	}

	@Override
	public void exitFacetApply(@NotNull FacetApplyContext ctx) {
		if (!errors.isEmpty()) return;
		deque.poll();
	}

	@Override
	public void enterFacetTarget(@NotNull FacetTargetContext ctx) {
		if (!errors.isEmpty()) return;
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
		if (!errors.isEmpty()) return;
		deque.poll();
	}

	@Override
	public void enterDoc(@NotNull DocContext ctx) {
		if (!errors.isEmpty()) return;
		StringBuilder builder = new StringBuilder();
		for (TerminalNode doc : ctx.DOC())
			builder.append(doc.getText().substring(2));
		String trim = builder.toString().trim();
		deque.peek().addDoc(trim);
	}

	@Override
	public void enterParameter(@NotNull ParameterContext ctx) {
		if (!errors.isEmpty()) return;
		int position = ((ParametersContext) ctx.getParent()).parameter().indexOf(ctx);
		String metric = ctx.value().metric() != null ? ctx.value().metric().getText() : null;
		addParameter(ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : "", position, metric, resolveValue(ctx.value()), ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
	}

	public void addParameter(String name, int position, String measureValue, Object[] values, int line, int column) {
		Parametrized object = (Parametrized) deque.peek();
		object.addParameter(name, position, measureValue, line, column, values);
	}

	@Override
	public void enterNodeReference(@NotNull NodeReferenceContext ctx) {
		if (!errors.isEmpty()) return;
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
		if (!errors.isEmpty()) return;
		((Node) deque.peek()).plate(ctx.getText().substring(1));
	}

	@Override
	public void enterVariable(@NotNull VariableContext ctx) {
		if (!errors.isEmpty()) return;
		NodeContainer container = deque.peek();
		Variable variable = createVariable(ctx, container);
		addValue(variable, ctx);
		if (ctx.ruleContainer() != null)
			addRule(variable, ctx.ruleContainer().ruleValue());
		addHeaderInformation(ctx, variable);
		variable.addFlags(resolveTags(ctx.flags()));
		container.add(variable);
	}

	private void addRule(Variable variable, RuleValueContext rule) {
		if (rule.LEFT_SQUARE() == null) {
			if (variable.type().equals(Primitive.NATIVE)) variable.rule(new NativeRule(rule.getText()));
			else variable.rule(new CustomRule(rule.getText()));
		} else processLambdaRule(variable, rule);
	}

	private void processLambdaRule(Variable variable, RuleValueContext rule) {
		List<ParseTree> parameters = rule.children.subList(1, ((ArrayList) rule.children).size() - 1);
		if (variable.type().equals(Primitive.DOUBLE))
			variable.rule(new DoubleRule(minOf(parameters), maxOf(parameters), valueOf(parameters, MetricContext.class)));
		else if (variable.type().equals(Primitive.INTEGER))
			variable.rule(new IntegerRule(minOf(parameters).intValue(), maxOf(parameters).intValue(), valueOf(parameters, MetricContext.class)));
		else if (variable.type().equals(Primitive.STRING)) {
			final String value = valueOf(parameters, StringValueContext.class);
			variable.rule(new StringRule(value.substring(1, value.length() - 1)));
		} else if (variable.type().equals(Primitive.FILE)) variable.rule(new FileRule(valuesOf(parameters)));
		else if (variable.type().equals(Primitive.NATIVE)) variable.rule(new NativeRule(parameters.get(0).getText()));
		else if (variable.type().equals(Primitive.WORD)) {
			final List<String> values = Arrays.asList(valuesOf(parameters));
			variable.rule(new WordRule(values));
		}
	}

	private String[] valuesOf(List<ParseTree> parameters) {
		List<String> values = parameters.stream().map(ParseTree::getText).collect(Collectors.toList());
		return values.toArray(new String[values.size()]);
	}

	private String valueOf(List<ParseTree> parameters, Class<? extends ParserRuleContext> aClass) {
		ParseTree value = parameters.stream().filter(aClass::isInstance).findFirst().orElse(null);
		return value == null ? "" : value.getText();
	}

	private Double minOf(List<ParseTree> parameters) {
		RangeContext range = (RangeContext) parameters.stream().filter(RangeContext.class::isInstance).findFirst().orElse(null);
		if (range == null) return Double.NEGATIVE_INFINITY;
		final String min = range.children.get(0).getText();
		return min.equals("*") ? Double.NEGATIVE_INFINITY : Double.parseDouble(min);
	}

	private Double maxOf(List<ParseTree> parameters) {
		RangeContext range = (RangeContext) parameters.stream().filter(RangeContext.class::isInstance).findFirst().orElse(null);
		if (range == null) return Double.POSITIVE_INFINITY;
		final String max = range.children.get(range.children.size() - 1).getText();
		return max.equals("*") ? Double.POSITIVE_INFINITY : Double.parseDouble(max);
	}

	private Variable createVariable(@NotNull VariableContext ctx, NodeContainer container) {
		VariableTypeContext variableType = ctx.variableType();
		Variable variable = variableType.identifierReference() != null ?
			new VariableReference(container, variableType.getText(), ctx.IDENTIFIER().getText()) :
			new VariableImpl(container, Primitive.value(variableType.getText()), ctx.IDENTIFIER().getText());
		if (ctx.LIST() != null) variable.size(0);
		if (ctx.count() != null) variable.size(Integer.parseInt(ctx.count().NATURAL_VALUE().getText()));
		return variable;
	}

	private void addValue(Variable variable, @NotNull VariableContext ctx) {
		if (ctx.value() == null) return;
		variable.addDefaultValues(resolveValue(ctx.value()));
		if (ctx.value().metric() != null) variable.defaultExtension(ctx.value().metric().getText());
	}

	@Override
	public void enterVarInit(@NotNull VarInitContext ctx) {
		if (!errors.isEmpty()) return;
		String extension = ctx.value().metric() != null ? ctx.value().metric().getText() : null;
		addParameter(ctx.IDENTIFIER().getText(), -1, extension, resolveValue(ctx.value()), ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
	}

	private Object[] resolveValue(ValueContext ctx) {
		List<Object> values = new ArrayList<>();
		if (!ctx.booleanValue().isEmpty())
			values.addAll(ctx.booleanValue().stream().
				map(context -> Primitive.BOOLEAN.convert(context.getText())[0]).collect(Collectors.toList()));
		else if (!ctx.integerValue().isEmpty())
			values.addAll(ctx.integerValue().stream().
				map(context -> INTEGER.convert(context.getText())[0]).collect(Collectors.toList()));
		else if (!ctx.doubleValue().isEmpty())
			values.addAll(ctx.doubleValue().stream().
				map(context -> DOUBLE.convert(context.getText())[0]).collect(Collectors.toList()));
		else if (!ctx.tupleValue().isEmpty())
			values.addAll(ctx.tupleValue().stream().
				map(context -> new AbstractMap.SimpleEntry<>(context.stringValue().getText(), DOUBLE.convert(context.doubleValue().getText())[0])).collect(Collectors.toList()));
		else if (!ctx.stringValue().isEmpty())
			values.addAll(ctx.stringValue().stream().
				map(context -> formatString(context.getText())).collect(Collectors.toList()));
		else if (!ctx.identifierReference().isEmpty())
			values.addAll(ctx.identifierReference().stream().
				map(context -> Parameter.REFERENCE_PREFIX + context.getText()).collect(Collectors.toList()));
		else if (!ctx.expression().isEmpty())
			values.addAll(ctx.expression().stream().
				map(context -> new Primitive.Expression(formatExpression(context.getText()).trim())).collect(Collectors.toList()))
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

	public List<SyntaxException> getErrors() {
		return errors;
	}

	private void addError(String message, ParserRuleContext ctx) {
		errors.add(new SyntaxException(message, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), ""));
	}
}