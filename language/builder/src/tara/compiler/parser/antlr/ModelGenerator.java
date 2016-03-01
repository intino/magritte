package tara.compiler.parser.antlr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import tara.Language;
import tara.compiler.codegeneration.Format;
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
import static tara.lang.model.Primitive.RESOURCE;
import static tara.lang.model.Primitive.WORD;

public class ModelGenerator extends TaraGrammarBaseListener {

	private final String file;
	private final Deque<NodeContainer> deque = new ArrayDeque<>();
	private final Set<String> uses = new HashSet<>();
	private final Model model;
	private List<SyntaxException> errors = new ArrayList<>();

	public ModelGenerator(String file, Language language) {
		this.file = file;
		model = new Model(file, language);
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
		if (ctx.signature().IDENTIFIER() != null) node.name(ctx.signature().IDENTIFIER().getText());
		NodeContainer container = resolveContainer(node);
		node.type(node.isSub() ? deque.peek().type() : ctx.signature().metaidentifier().getText());
		resolveParent(ctx, node);
		CompositionRule rule = createCompositionRule(getCompositionRules(ctx));
		if (rule == null && node.isSub()) rule = container.ruleOf(node.parent());
		else if (rule == null) rule = Size.MULTIPLE();
		container.add(node, rule);
		node.container(container);
		addTags(ctx.signature().tags(), node);
		setTable(ctx.signature().withTable(), node);
		addHeaderInformation(ctx, node);
		node.addUses(new ArrayList<>(uses));
		deque.push(node);
	}

	public List<RuleContainerContext> getCompositionRules(@NotNull NodeContext ctx) {
		List<RuleContainerContext> contexts = new ArrayList<>();
		final List<RuleContainerContext> container = ctx.signature().ruleContainer();
		if (container.isEmpty()) return Collections.emptyList();
		if (container.size() == 1) {
			if (isInto(container.get(0))) contexts.add(null);
			contexts.add(container.get(0));
		} else return container;
		return contexts;
	}

	private boolean isInto(RuleContainerContext ruleContainer) {
		final List<ParseTree> children = ((ParserRuleContext) ruleContainer.parent).children;
		final ParseTree node = children.get(children.indexOf(ruleContainer) - 1);
		return !(node instanceof MetaidentifierContext) && !(node instanceof TerminalNode && node.getText().equals("sub"));
	}

	private CompositionRule createCompositionRule(List<RuleContainerContext> ruleContainer) {
		if (ruleContainer == null || ruleContainer.isEmpty() || (ruleContainer.get(0) == null && ruleContainer.get(1) == null)) return null;
		final RuleValueContext isRule = ruleContainer.get(0) != null ? ruleContainer.get(0).ruleValue() : null;
		final RuleValueContext intoRule = ruleContainer.size() > 1 ? ruleContainer.get(1).ruleValue() : null;
		return createRule(isRule, intoRule);
	}

	private CompositionRule createRule(RuleValueContext isRule, RuleValueContext intoRule) {
		return isCustom(isRule) ? new CompositionCustomRule(isRule.getText()) : processLambdaCompositionRule(isRule, intoRule);
	}

	private boolean isCustom(RuleValueContext isRule) {
		return isRule != null && isRule.LEFT_CURLY() == null;
	}

	private void addTags(@NotNull TaraGrammar.TagsContext ctx, Node node) {
		if (ctx == null) return;
		if (ctx.flags() != null) node.addFlags(resolveTags(ctx.flags()));
		if (ctx.annotations() != null) node.addAnnotations(resolveTags(ctx.annotations()));
	}

	private void setTable(WithTableContext ctx, NodeImpl node) {
		if (ctx == null) return;
		node.table(ctx.identifierReference().getText(), parameters(ctx.tableParameters()));
	}

	private List<String> parameters(TableParametersContext ctx) {
		List<String> parameters = new ArrayList<>();
		String parameter = "";
		for (ParseTree child : ctx.children.subList(1, ctx.children.size() - 1)) {
			if (!child.getText().equals(",")) parameter += " " + child.getText();
			else {
				parameters.add(parameter.trim());
				parameter = "";
			}
		}
		if (!parameter.isEmpty()) parameters.add(parameter.trim());
		return parameters;
	}

	private NodeContainer resolveContainer(Node node) {
		NodeContainer context = deque.peek();
		if (node.isSub()) return context.container();
		else return context;
	}

	private void resolveParent(NodeContext ctx, NodeImpl node) {
		if (node.isSub()) {
			Node peek = (Node) deque.peek();
			if (!peek.isAbstract()) peek.addFlag(Tag.Abstract);
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
		facetTarget.target(ctx.identifierReference().getText());
		if (ctx.with() != null) facetTarget.constraints(collectConstrains(ctx.with().identifierReference()));
		peek.facetTarget(facetTarget);
		facetTarget.owner(peek);
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

	public void addParameter(String name, int position, String measureValue, List<Object> values, int line, int column) {
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
		final CompositionRule rule = createCompositionRule(ctx.ruleContainer() != null ? Collections.singletonList(ctx.ruleContainer()) : Collections.emptyList());
		container.add(nodeReference, rule == null ? Size.MULTIPLE() : rule);
	}

	private Tag[] resolveTags(AnnotationsContext annotations) {
		List<Tag> values = new ArrayList<>();
		if (annotations == null) return new Tag[0];
		values.addAll(annotations.annotation().stream().map(a -> Tag.valueOf(Format.capitalize(a.getText()))).collect(Collectors.toList()));
		return values.toArray(new Tag[values.size()]);
	}

	private List<Tag> resolveTags(FlagsContext flags) {
		List<Tag> tags = new ArrayList<>();
		if (flags == null) return Collections.emptyList();
		tags.addAll(flags.flag().stream().map(f -> Tag.valueOf(Format.capitalize(f.getText()))).collect(Collectors.toList()));
		return tags;
	}

	@Override
	public void enterAnchor(@NotNull AnchorContext ctx) {
		if (!errors.isEmpty()) return;
		((Node) deque.peek()).anchor(ctx.getText().replace("*", ""));
	}

	@Override
	public void enterVariable(@NotNull VariableContext ctx) {
		if (!errors.isEmpty()) return;
		NodeContainer container = deque.peek();
		Variable variable = createVariable(ctx, container);
		addHeaderInformation(ctx, variable);
		addValue(variable, ctx);
		Size size = createSize(ctx);
		if (!variable.values().isEmpty()) size = new Size(0, size.max(), size.into());
		variable.size(size);
		variable.rule(ctx.ruleContainer() != null ? createRule(variable, ctx.ruleContainer().ruleValue()) : null);
		final List<Tag> tags = resolveTags(ctx.flags());
		variable.addFlags(tags.toArray(new Tag[tags.size()]));
		container.add(variable);
	}

	private Size createSize(VariableContext context) {
		final SizeContext sizeContext = context.size();
		if (sizeContext == null) return Size.SINGLE_REQUIRED();
		final SizeRangeContext rangeContext = sizeContext.sizeRange();
		if (rangeContext == null) return new Size(1, Integer.MAX_VALUE);
		final ListRangeContext listRange = rangeContext.listRange();
		if (listRange != null)
			return new Size(Integer.parseInt(listRange.children.get(0).getText()), Integer.parseInt(listRange.children.get(listRange.children.size() - 1).getText()));
		final int minMax = Integer.parseInt(rangeContext.getText());
		return new Size(minMax, minMax);
	}

	private Rule createRule(Variable variable, RuleValueContext rule) {
		if (isCustom(rule)) {
			if (variable.type().equals(FUNCTION)) return new NativeRule(rule.getText());
			else return new CustomRule(rule.getText());
		} else return processLambdaRule(variable, rule);
	}

	private Rule processLambdaRule(Variable var, RuleValueContext rule) {
		List<ParseTree> params = rule.children.subList(1, ((ArrayList) rule.children).size() - 1);
		if (DOUBLE.equals(var.type())) return new DoubleRule(minOf(params), maxOf(params), metric(params));
		else if (INTEGER.equals(var.type()))
			return new IntegerRule(minOf(params).intValue(), maxOf(params).intValue(), metric(params));
		else if (STRING.equals(var.type())) createStringVariable(var, params);
		else if (RESOURCE.equals(var.type())) return new FileRule(valuesOf(params));
		else if (FUNCTION.equals(var.type())) return new NativeRule(params.get(0).getText());
		else if (WORD.equals(var.type())) return new WordRule(valuesOf(params));
		return null;
	}

	private CompositionRule processLambdaCompositionRule(RuleValueContext isRule, RuleValueContext intoRule) {
		if (isRule == null && intoRule == null) return null;
		if (isRule != null) return createCompositionRule(isRule, intoRule);
		else return new Size(Size.MULTIPLE(), processLambdaCompositionRule(intoRule, null));
	}

	private CompositionRule createCompositionRule(RuleValueContext isRule, RuleValueContext intoRule) {
		List<ParseTree> params = isRule.children.subList(1, ((ArrayList) isRule.children).size() - 1);
		final int min = minOf(params).intValue();
		if (min < 0) addError("Array size cannot be negative", isRule);
		return new Size(min, maxOf(params).intValue(), intoRule != null ? processLambdaCompositionRule(intoRule, null) : null);
	}

	private void createStringVariable(Variable variable, List<ParseTree> parameters) {
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
		if (ctx.value() == null && ctx.bodyValue() == null) return;
		List<Object> values = ctx.bodyValue() != null ? resolveValue(ctx.bodyValue()) : resolveValue(ctx.value());
		if (variable.type().equals(DOUBLE) && !values.isEmpty() && values.get(0) instanceof Integer)
			values = values.stream().map(v -> new Double((Integer) v)).collect(Collectors.toList());
		variable.values(values);
		if (ctx.value() != null && ctx.value().metric() != null) variable.defaultMetric(ctx.value().metric().getText());
	}

	@Override
	public void enterVarInit(@NotNull VarInitContext ctx) {
		if (!errors.isEmpty()) return;
		String extension = ctx.value() != null && ctx.value().metric() != null ? ctx.value().metric().getText() : null;
		addParameter(ctx.IDENTIFIER().getText(), -1, extension, ctx.bodyValue() != null ? resolveValue(ctx.bodyValue()) : resolveValue(ctx.value()), ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
	}

	private List<Object> resolveValue(ValueContext ctx) {
		List<Object> values = new ArrayList<>();
		if (!ctx.booleanValue().isEmpty())
			values.addAll(ctx.booleanValue().stream().
				map(context -> BOOLEAN.convert(context.getText()).get(0)).collect(Collectors.toList()));
		else if (!ctx.integerValue().isEmpty())
			values.addAll(ctx.integerValue().stream().
				map(context -> INTEGER.convert((String) context.getText()).get(0)).collect(Collectors.toList()));
		else if (!ctx.doubleValue().isEmpty())
			values.addAll(ctx.doubleValue().stream().
				map(context -> DOUBLE.convert((String) context.getText()).get(0)).collect(Collectors.toList()));
		else if (!ctx.tupleValue().isEmpty())
			values.addAll(ctx.tupleValue().stream().
				map(context -> new AbstractMap.SimpleEntry<>(context.stringValue().getText(), DOUBLE.convert((String) context.doubleValue().getText()).get(0))).collect(Collectors.toList()));
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
		return values;
	}

	private List<Object> resolveValue(BodyValueContext ctx) {
		List<Object> values = new ArrayList<>();
		if (ctx.stringValue() != null) values.add(formatString(ctx.stringValue().getText()));
		else if (ctx.expression() != null)
			values.add(new Expression(formatExpression(ctx.expression().getText()).trim()));
		return values;
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