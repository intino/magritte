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
import tara.lang.model.Primitive.*;
import tara.lang.model.rules.CompositionRule;
import tara.lang.model.rules.Size;
import tara.lang.model.rules.composition.CompositionCustomRule;
import tara.lang.model.rules.variable.*;

import java.util.*;
import java.util.stream.Collectors;

import static tara.lang.model.Primitive.*;
import static tara.lang.model.Primitive.WORD;

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
		CompositionRule rule = createCompositionRule(ctx.signature().ruleContainer());
		container.add(node, rule);
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

	private CompositionRule createCompositionRule(RuleContainerContext ruleContainer) {
		if (ruleContainer == null) return Size.MULTIPLE;
		final RuleValueContext rule = ruleContainer.ruleValue();
		if (rule.LEFT_CURLY() == null) {
			return new CompositionCustomRule(rule.getText());
		} else return processLambdaRule(rule);

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
			if (!peek.isAbstract()) peek.addFlag(Tag.ABSTRACT);
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
		container.add(nodeReference, createCompositionRule(ctx.ruleContainer()));
	}

	private Tag[] resolveTags(AnnotationsContext annotations) {
		List<Tag> values = new ArrayList<>();
		if (annotations == null) return new Tag[0];
		values.addAll(annotations.annotation().stream().map(a -> Tag.valueOf(a.getText().toUpperCase())).collect(Collectors.toList()));
		return values.toArray(new Tag[values.size()]);
	}

	private List<Tag> resolveTags(FlagsContext flags) {
		List<Tag> tags = new ArrayList<>();
		if (flags == null) return Collections.emptyList();
		tags.addAll(flags.flag().stream().map(f -> Tag.valueOf(f.getText().toUpperCase())).collect(Collectors.toList()));
		return tags;
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
		addHeaderInformation(ctx, variable);
		addValue(variable, ctx);
		final Size size = createSize(ctx);
		variable.size(size);
		variable.rule(ctx.ruleContainer() != null ? createRule(variable, ctx.ruleContainer().ruleValue(), size) : size);
		final List<Tag> tags = resolveTags(ctx.flags());
		variable.addFlags(tags.toArray(new Tag[tags.size()]));
		container.add(variable);
	}

	private Size createSize(VariableContext context) {
		final SizeContext sizeContext = context.size();
		if (sizeContext == null) return Size.SINGLE_REQUIRED;
		final SizeRangeContext rangeContext = sizeContext.sizeRange();
		if (rangeContext == null) return new Size(1, Integer.MAX_VALUE);
		final ListRangeContext listRange = rangeContext.listRange();
		if (listRange != null)
			return new Size(Integer.parseInt(listRange.children.get(0).getText()), Integer.parseInt(listRange.children.get(listRange.children.size() - 1).getText()));
		final int minMax = Integer.parseInt(rangeContext.getText());
		return new Size(minMax, minMax);
	}

	private Rule createRule(Variable variable, RuleValueContext rule, Size size) {
		if (rule.LEFT_CURLY() == null) {
			if (variable.type().equals(NATIVE)) return new NativeRule(rule.getText());
			else return new CustomRule(rule.getText());
		} else return processLambdaRule(variable, rule, size);
	}

	private Rule processLambdaRule(Variable var, RuleValueContext rule, Size size) {
		List<ParseTree> params = rule.children.subList(1, ((ArrayList) rule.children).size() - 1);
		if (DOUBLE.equals(var.type())) return new DoubleRule(minOf(params), maxOf(params), metric(params));
		else if (INTEGER.equals(var.type()))
			return new IntegerRule(minOf(params).intValue(), maxOf(params).intValue(), metric(params));
		else if (STRING.equals(var.type())) createStringVariable(var, size, params);
		else if (FILE.equals(var.type())) return new FileRule(valuesOf(params));
		else if (NATIVE.equals(var.type())) return new NativeRule(params.get(0).getText());
		else if (WORD.equals(var.type())) return new WordRule(valuesOf(params));
		return null;
	}

	private CompositionRule processLambdaRule(RuleValueContext rule) {//TODO
//		List<ParseTree> params = rule.children.subList(1, ((ArrayList) rule.children).size() - 1);
//		if (DOUBLE.equals(var.type())) return new DoubleRule(minOf(params), maxOf(params), metric(params), size);
//		else if (INTEGER.equals(var.type()))
//			return new IntegerRule(minOf(params).intValue(), maxOf(params).intValue(), metric(params), size);
//		else if (STRING.equals(var.type())) createStringVariable(var, size, params);
//		else if (FILE.equals(var.type())) return new FileRule(valuesOf(params), size);
//		else if (NATIVE.equals(var.type())) return new NativeRule(params.get(0).getText());
//		else if (WORD.equals(var.type())) return new WordRule(valuesOf(params), size);
		return null;
	}

	private void createStringVariable(Variable variable, Size size, List<ParseTree> parameters) {
		final String value = valueOf(parameters, StringValueContext.class);
		variable.rule(new StringRule(value.substring(1, value.length() - 1)));
	}

	private String metric(List<ParseTree> parameters) {
		for (ParseTree parameter : parameters)
			if (parameter instanceof TerminalNode || parameter instanceof MetricContext) return parameter.getText();
		return "";
	}

	private List<String> valuesOf(List<ParseTree> parameters) {
		return parameters.stream().map(ParseTree::getText).collect(Collectors.toList());
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
		return variableType.identifierReference() != null ?
			new VariableReference(container, variableType.getText(), ctx.IDENTIFIER().getText()) :
			new VariableImpl(container, value(variableType.getText()), ctx.IDENTIFIER().getText());
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
				map(context -> BOOLEAN.convert(context.getText())[0]).collect(Collectors.toList()));
		else if (!ctx.integerValue().isEmpty())
			values.addAll(ctx.integerValue().stream().
				map(context -> INTEGER.convert((String) context.getText())[0]).collect(Collectors.toList()));
		else if (!ctx.doubleValue().isEmpty())
			values.addAll(ctx.doubleValue().stream().
				map(context -> DOUBLE.convert((String) context.getText())[0]).collect(Collectors.toList()));
		else if (!ctx.tupleValue().isEmpty())
			values.addAll(ctx.tupleValue().stream().
				map(context -> new AbstractMap.SimpleEntry<>(context.stringValue().getText(), DOUBLE.convert((String) context.doubleValue().getText())[0])).collect(Collectors.toList()));
		else if (!ctx.stringValue().isEmpty())
			values.addAll(ctx.stringValue().stream().
				map(context -> formatString(context.getText())).collect(Collectors.toList()));
		else if (!ctx.identifierReference().isEmpty())
			values.addAll(ctx.identifierReference().stream().
				map(context -> new Reference(context.getText())).collect(Collectors.toList()));
		else if (!ctx.expression().isEmpty())
			values.addAll(ctx.expression().stream().
				map(context -> new Expression(formatExpression(context.getText()).trim())).collect(Collectors.toList()));
		else if (ctx.EMPTY() != null) values.add(new EmptyNode());
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