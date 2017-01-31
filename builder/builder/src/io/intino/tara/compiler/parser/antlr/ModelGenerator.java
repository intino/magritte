package io.intino.tara.compiler.parser.antlr;

import io.intino.tara.compiler.codegeneration.Format;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.core.errorcollection.SyntaxException;
import io.intino.tara.compiler.model.*;
import io.intino.tara.lang.grammar.TaraGrammar;
import io.intino.tara.lang.grammar.TaraGrammar.*;
import io.intino.tara.lang.grammar.TaraGrammarBaseListener;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.Size;
import io.intino.tara.lang.model.rules.composition.NodeCustomRule;
import io.intino.tara.lang.model.rules.variable.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;
import java.util.stream.Collectors;

import static io.intino.tara.lang.model.Primitive.*;
import static io.intino.tara.lang.model.Primitive.RESOURCE;
import static io.intino.tara.lang.model.Primitive.WORD;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class ModelGenerator extends TaraGrammarBaseListener {

	private final String file;
	private List<CompilerConfiguration.DSL> languages;
	private final String outDsl;
	private final Deque<Node> deque = new ArrayDeque<>();
	private final Set<String> uses = new HashSet<>();
	private final Model model;
	private List<SyntaxException> errors = new ArrayList<>();

	public ModelGenerator(String file, List<CompilerConfiguration.DSL> languages, String outDsl) {
		this.file = file;
		this.languages = languages;
		this.outDsl = outDsl;
		deque.add(model = new Model(file));
	}

	@Override
	public void enterAnImport(TaraGrammar.AnImportContext ctx) {
		uses.add(ctx.headerReference().getText());
	}

	@Override
	public void enterDslDeclaration(TaraGrammar.DslDeclarationContext ctx) {
		if (ctx.headerReference() != null) {
			final String langName = ctx.headerReference().getText();
			for (CompilerConfiguration.DSL language : languages)
				if (language.name().equals(langName)) model.setLanguage(language.get());
		}
	}

	@Override
	public void enterNode(NodeContext ctx) {
		if (!errors.isEmpty()) return;
		NodeImpl node = new NodeImpl();
		node.language(model.language());
		node.setSub(ctx.signature().SUB() != null);
		if (ctx.signature().IDENTIFIER() != null) node.name(ctx.signature().IDENTIFIER().getText());
		Node container = resolveContainer(node);
		node.type(node.isSub() ? deque.peek().type() : ctx.signature().metaidentifier().getText());
		resolveParent(ctx, node);
		List<Rule> rules = createCompositionRule(compositionRules(ctx.signature().ruleContainer()));
		if (rules == null && node.isSub()) rules = container.rulesOf(node.parent());
		else if (rules == null) rules = singletonList(Size.MULTIPLE());
		container.add(node, rules);
		node.container(container);
		addTags(ctx.signature().tags(), node);
		addHeaderInformation(ctx, node);
		node.addUses(new ArrayList<>(uses));
		deque.push(node);
	}

	private List<RuleContainerContext> compositionRules(List<RuleContainerContext> container) {
		List<RuleContainerContext> contexts = new ArrayList<>();
		if (container.isEmpty()) return emptyList();
		if (container.size() == 1) {
			if (isInto(container.get(0))) contexts.add(null);
			contexts.add(container.get(0));
		} else return container;
		return contexts;
	}

	private boolean isInto(RuleContainerContext ruleContainer) {
		final List<ParseTree> children = ((ParserRuleContext) ruleContainer.parent).children;
		final ParseTree node = children.get(children.indexOf(ruleContainer) - 1);
		return !(node instanceof MetaidentifierContext) && !(node instanceof TerminalNode && node.getText().equals("sub")) && !(node instanceof TerminalNode && node.getText().equals("has"));
	}

	private List<Rule> createCompositionRule(List<RuleContainerContext> ruleContainer) {
		if (ruleContainer == null || ruleContainer.isEmpty()) return Collections.singletonList(Size.MULTIPLE());
		return ruleContainer.stream().map(context -> createRule(context.ruleValue())).collect(Collectors.toList());
	}

	private Rule createRule(RuleValueContext rule) {
		return isCustom(rule) ? new NodeCustomRule(rule.getText()) : processLambdaRule(rule);
	}

	private boolean isCustom(RuleValueContext isRule) {
		return isRule != null && isRule.LEFT_CURLY() == null;
	}

	private void addTags(TaraGrammar.TagsContext ctx, Node node) {
		if (ctx == null) return;
		if (ctx.flags() != null) node.addFlags(resolveTags(ctx.flags()));
		if (ctx.annotations() != null) node.addAnnotations(resolveTags(ctx.annotations()));
	}

	private Node resolveContainer(Node node) {
		Node context = deque.peek();
		if (node.isSub()) return context.container();
		else return context;
	}

	private void resolveParent(NodeContext ctx, NodeImpl node) {
		if (node.isSub()) {
			Node peek = deque.peek();
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
	public void exitNode(NodeContext ctx) {
		if (!errors.isEmpty()) return;
		deque.pop();
	}

	@Override
	public void enterFacet(FacetContext ctx) {
		if (!errors.isEmpty()) return;
		FacetImpl facet = new FacetImpl(ctx.metaidentifier().getText());
		addHeaderInformation(ctx, facet);
		if (deque.peek() == null) {
			addError("Unavailable component facet apply in context " + deque.peek().getClass().getInterfaces()[0].getSimpleName(), ctx);
			return;
		}
		Node peek = deque.peek();
		peek.addFacets(facet);
		facet.container(peek);
	}

	@Override
	public void enterFacetTarget(FacetTargetContext ctx) {
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
		return contexts.stream().map(IdentifierReferenceContext::getText).collect(toList());
	}

	private NodeImpl getNodeContainer() {
		NodeContainer peek = deque.peek();
		while (!(peek instanceof NodeImpl))
			peek = peek.container();
		return (NodeImpl) peek;
	}

	@Override
	public void enterDoc(DocContext ctx) {
		if (!errors.isEmpty()) return;
		StringBuilder builder = new StringBuilder();
		for (TerminalNode doc : ctx.DOC())
			builder.append(doc.getText().substring(2));
		String trim = builder.toString().trim();
		deque.peek().doc(trim);
	}

	@Override
	public void enterParameter(ParameterContext ctx) {
		if (!errors.isEmpty()) return;
		int position = ((ParametersContext) ctx.getParent()).parameter().indexOf(ctx);
		String metric = ctx.value().metric() != null ? ctx.value().metric().getText() : null;
		addParameter(ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : "", facetOf(ctx), position, metric, resolveValue(ctx.value()), ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
	}

	private String facetOf(ParameterContext ctx) {
		return ctx.getParent().getParent() instanceof FacetContext ? ((FacetContext) ctx.getParent().getParent()).metaidentifier().getText() : "";
	}

	private void addParameter(String name, String facet, int position, String measureValue, List<Object> values, int line, int column) {
		Parametrized object = deque.peek();
		object.addParameter(name, facet, position, measureValue, line, column, values);
	}

	@Override
	public void enterNodeReference(NodeReferenceContext ctx) {
		if (!errors.isEmpty()) return;
		Node container = deque.peek();
		NodeReference nodeReference = new NodeReference(ctx.identifierReference().getText());
		nodeReference.addUses(new ArrayList<>(uses));
		addHeaderInformation(ctx, nodeReference);
		nodeReference.setHas(true);
		addTags(ctx.tags(), nodeReference);
		nodeReference.container(container);
		container.add(nodeReference, createCompositionRule(ctx.ruleContainer() != null ? compositionRules(ctx.ruleContainer()) : emptyList()));
	}

	private Tag[] resolveTags(AnnotationsContext annotations) {
		List<Tag> values = new ArrayList<>();
		if (annotations == null) return new Tag[0];
		values.addAll(annotations.annotation().stream().map(a -> Tag.valueOf(Format.capitalize(a.getText()))).collect(toList()));
		return values.toArray(new Tag[values.size()]);
	}

	private List<Tag> resolveTags(FlagsContext flags) {
		List<Tag> tags = new ArrayList<>();
		if (flags == null) return emptyList();
		tags.addAll(flags.flag().stream().map(f -> Tag.valueOf(Format.capitalize(f.getText()))).collect(toList()));
		return tags;
	}

	@Override
	public void enterVariable(VariableContext ctx) {
		if (!errors.isEmpty()) return;
		Node container = deque.peek();
		Variable variable = createVariable(ctx, container);
		addHeaderInformation(ctx, variable);
		addValue(variable, ctx);
		Size size = createSize(ctx);
		if (!variable.values().isEmpty()) size = new Size(0, size.max(), size.into());
		variable.size(size);
		variable.rule(ctx.ruleContainer() != null ? createRule(variable, ctx.ruleContainer().ruleValue()) : variable.type().defaultRule());
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

	private VariableRule createRule(Variable variable, RuleValueContext rule) {
		if (isCustom(rule)) {
			if (FUNCTION.equals(variable.type())) return new NativeRule(rule.getText());
			else if (OBJECT.equals(variable.type())) return new NativeObjectRule(rule.getText());
			else
				return isBundledRule(rule.identifierReference().getText()) ? createDefault(rule.identifierReference().getText()) : new VariableCustomRule(rule.getText());
		} else return processLambdaRule(variable, rule);

	}

	private boolean isBundledRule(String text) {
		try {
			Class.forName("tara.lang.model.rules.custom." + Format.firstUpperCase().format(text));
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	private VariableRule createDefault(String rule) {
		try {
			return (VariableRule) Class.forName("tara.lang.model.rules.custom." + Format.firstUpperCase().format(rule)).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	private VariableRule processLambdaRule(Variable var, RuleValueContext rule) {
		List<ParseTree> params = rule.children.subList(1, rule.children.size() - 1);
		if (DOUBLE.equals(var.type())) return new DoubleRule(minOf(params), maxOf(params), metric(params));
		else if (INTEGER.equals(var.type()))
			return new IntegerRule(minOf(params).intValue(), maxOf(params).intValue(), metric(params));
		else if (STRING.equals(var.type())) createStringVariable(var, params);
		else if (RESOURCE.equals(var.type())) return new FileRule(valuesOf(params));
		else if (FUNCTION.equals(var.type())) return new NativeRule(params.get(0).getText());
		else if (WORD.equals(var.type())) return new WordRule(valuesOf(params));
		else if (OBJECT.equals(var.type())) return new NativeObjectRule(params.get(0).getText());
		return null;
	}

	private Rule processLambdaRule(RuleValueContext isRule) {
		return isRule == null ? null : createNodeRule(isRule);
	}

	private Size createNodeRule(RuleValueContext rule) {
		List<ParseTree> params = rule.children.subList(1, rule.children.size() - 1);
		if (isNamedSize(params)) return createNamedSize(params);
		final int min = minOf(params).intValue();
		if (min < 0) addError("Array size cannot be negative", rule);
		return new Size(min, maxOf(params).intValue(), null);
	}

	private Size createNamedSize(List<ParseTree> params) {
		int min = 0;
		int max = Integer.MAX_VALUE;
		for (ParseTree param : params)
			if (param.getText().equalsIgnoreCase("single")) max = 1;
			else if (param.getText().equalsIgnoreCase("required")) min = 1;
		return new Size(min, max);
	}

	private boolean isNamedSize(List<ParseTree> params) {
		for (ParseTree param : params)
			if (!param.getText().equalsIgnoreCase("single") && !param.getText().equalsIgnoreCase("required"))
				return false;
		return true;
	}

	private void createStringVariable(Variable variable, List<ParseTree> parameters) {
		final String value = valueOf(parameters, StringValueContext.class);
		if (value.isEmpty()) {
			addError("Expected pattern rule", (ParserRuleContext) parameters.get(0).getParent());
			return;
		}
		variable.rule(new StringRule(value.substring(1, value.length() - 1)));
	}

	private String metric(List<ParseTree> parameters) {
		for (ParseTree parameter : parameters)
			if (parameter instanceof TerminalNode || parameter instanceof MetricContext) return parameter.getText();
		return "";
	}

	private List<String> valuesOf(List<ParseTree> parameters) {
		return parameters.stream().map(ParseTree::getText).collect(toList());
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

	private Variable createVariable(VariableContext ctx, Node container) {
		VariableTypeContext variableType = ctx.variableType();
		return variableType.identifierReference() != null ?
				new VariableReference(container, variableType.getText(), ctx.IDENTIFIER().getText(), outDsl) :
				new VariableImpl(container, value(variableType.getText()), ctx.IDENTIFIER().getText(), outDsl);
	}

	private void addValue(Variable variable, VariableContext ctx) {
		if (ctx.value() == null && ctx.bodyValue() == null) return;
		List<Object> values = ctx.bodyValue() != null ? resolveValue(ctx.bodyValue()) : resolveValue(ctx.value());
		if (variable.type().equals(DOUBLE) && !values.isEmpty() && values.get(0) instanceof Integer)
			values = values.stream().map(v -> new Double((Integer) v)).collect(toList());
		variable.values(values);
		if (ctx.value() != null && ctx.value().metric() != null) variable.defaultMetric(ctx.value().metric().getText());
	}

	@Override
	public void enterVarInit(VarInitContext ctx) {
		if (!errors.isEmpty()) return;
		String extension = ctx.value() != null && ctx.value().metric() != null ? ctx.value().metric().getText() : null;
		addParameter(ctx.IDENTIFIER().getText(), "", -1, extension, ctx.bodyValue() != null ? resolveValue(ctx.bodyValue()) : resolveValue(ctx.value()), ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
	}

	private List<Object> resolveValue(ValueContext ctx) {
		List<Object> values = new ArrayList<>();
		if (!ctx.booleanValue().isEmpty())
			values.addAll(ctx.booleanValue().stream().
					map(context -> BOOLEAN.convert(context.getText()).get(0)).collect(toList()));
		else if (!ctx.integerValue().isEmpty())
			values.addAll(ctx.integerValue().stream().
					map(context -> INTEGER.convert((String) context.getText()).get(0)).collect(toList()));
		else if (!ctx.doubleValue().isEmpty())
			values.addAll(ctx.doubleValue().stream().
					map(context -> DOUBLE.convert((String) context.getText()).get(0)).collect(toList()));
		else if (!ctx.tupleValue().isEmpty())
			values.addAll(ctx.tupleValue().stream().
					map(context -> new AbstractMap.SimpleEntry<>(context.stringValue().getText(), DOUBLE.convert((String) context.doubleValue().getText()).get(0))).collect(toList()));
		else if (!ctx.stringValue().isEmpty())
			values.addAll(ctx.stringValue().stream().
					map(context -> formatString(context.getText())).collect(toList()));
		else if (!ctx.identifierReference().isEmpty())
			values.addAll(ctx.identifierReference().stream().map(context -> new Reference(context.getText())).collect(toList()));
		else if (!ctx.methodReference().isEmpty())
			values.addAll(ctx.methodReference().stream().map(context -> new MethodReference(context.getText().substring(1))).collect(toList()));
		else if (!ctx.expression().isEmpty())
			values.addAll(ctx.expression().stream().map(context -> new Expression(formatExpression(context.getText()).trim())).collect(toList()));
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
		return format(value.trim().replaceAll("--(-*)\\n", "").replaceAll("--(-*)", ""));
	}

	private String formatString(String text) {
		String value = text.replace("\r", "");
		if (!value.trim().startsWith("==")) return value.substring(1, value.length() - 1).replace("\\\"", "\"");
		return format(value.trim().replaceAll("==(=*)\\n", "").replaceAll("==(=*)", ""));
	}

	private String format(String text) {
		String pattern = pattern(text);
		String result = "";
		for (String line : text.split("\\n")) result += line.replaceFirst(pattern, "") + "\n";
		while (result.endsWith("\n")) result = result.substring(0, result.length() - 1);
		return result;
	}

	private String pattern(String text) {
		final String replace = text.substring(0, text.indexOf("\n"));
		return replace.replace(replace.trim(), "");
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