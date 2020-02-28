package io.intino.magritte.compiler.parser.antlr;

import io.intino.magritte.compiler.codegeneration.Format;
import io.intino.magritte.compiler.core.CompilerConfiguration;
import io.intino.magritte.compiler.core.errorcollection.SyntaxException;
import io.intino.magritte.compiler.model.*;
import io.intino.magritte.lang.grammar.TaraGrammar;
import io.intino.magritte.lang.grammar.TaraGrammar.*;
import io.intino.magritte.lang.grammar.TaraGrammarBaseListener;
import io.intino.magritte.lang.model.*;
import io.intino.magritte.lang.model.rules.Size;
import io.intino.magritte.lang.model.rules.composition.NodeCustomRule;
import io.intino.magritte.lang.model.rules.variable.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static io.intino.magritte.lang.model.Primitive.RESOURCE;
import static io.intino.magritte.lang.model.Primitive.STRING;
import static io.intino.magritte.lang.model.Primitive.WORD;
import static io.intino.magritte.lang.model.Primitive.*;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class ModelGenerator extends TaraGrammarBaseListener {
	private final String file;
	private final String outDsl;
	private final Deque<Node> deque = new ArrayDeque<>();
	private final Set<String> uses = new HashSet<>();
	private final Model model;
	private CompilerConfiguration.Language language;
	private List<SyntaxException> errors = new ArrayList<>();

	public ModelGenerator(String file, CompilerConfiguration.Language language, String outDsl) {
		this.file = file;
		this.language = language;
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
			model.setLanguage(language.get());
			if (model.languageName().isEmpty()) addError("Language " + langName + " not found", ctx);
		} else addError("Language not found", ctx);
	}

	@Override
	public void enterNode(NodeContext ctx) {
		if (!errors.isEmpty()) return;
		NodeImpl node = new NodeImpl();
		node.languageName(model.languageName());
		node.setSub(ctx.signature().SUB() != null);
		String hashCodeName = calculateName(ctx);
		if (ctx.signature().IDENTIFIER() != null) node.name(ctx.signature().IDENTIFIER().getText());
		else node.name(hashCodeName);
		node.setHashCode(hashCodeName);
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

	private String calculateName(NodeContext ctx) {
		int hashCode = ctx.getText().replace(" ", "").hashCode();
		return "tara_" +
				new File(file).getName().replace(".tara", "") + "_" +
				ctx.getStart().getLine() + "_" + ctx.getStart().getCharPositionInLine() + "_" +
				(hashCode > 0 ? "0" + hashCode : "1" + Math.abs(hashCode));
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
		if (ctx.flags() != null) node.addFlags(resolveTags(ctx.flags()).toArray(new Tag[0]));
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
			if (!peek.isAbstract() && !peek.flags().contains(Tag.Abstract)) peek.addFlags(Tag.Abstract);
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
		element.languageName(model.languageName());
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
	public void enterWith(WithContext ctx) {
		if (deque.peek() == null) {
			addError("Unavailable constraint 'with' in context " + deque.peek().getClass().getInterfaces()[0].getSimpleName(), ctx);
			return;
		}
		NodeImpl peek = (NodeImpl) deque.peek();
		peek.aspectConstraints(collectConstrains(ctx.identifierReference()));
		super.enterWith(ctx);
	}

	private List<String> collectConstrains(List<IdentifierReferenceContext> contexts) {
		return contexts.stream().map(IdentifierReferenceContext::getText).collect(toList());
	}

	@Override
	public void enterAspect(AspectContext ctx) {
		if (!errors.isEmpty()) return;
		AspectImpl aspect = new AspectImpl(ctx.metaidentifier().getText());
		addHeaderInformation(ctx, aspect);
		if (deque.peek() == null) {
			addError("Unavailable component facet apply in context " + deque.peek().getClass().getInterfaces()[0].getSimpleName(), ctx);
			return;
		}
		Node current = deque.peek();
		current.applyAspects(aspect);
		aspect.container(current);
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
		addParameter(ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : "", aspectOf(ctx), position, metric, resolveValue(ctx.value()), ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
	}

	private String aspectOf(ParameterContext ctx) {
		return ctx.getParent().getParent() instanceof AspectContext ? ((AspectContext) ctx.getParent().getParent()).metaidentifier().getText() : "";
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
		if (annotations == null) return new Tag[0];
		return annotations.annotation().stream().map(a -> Tag.valueOf(Format.capitalize(a.getText()))).toArray(Tag[]::new);
	}

	private List<Tag> resolveTags(FlagsContext flags) {
		if (flags == null) return emptyList();
		return new ArrayList<>(flags.flag().stream().map(f -> Tag.valueOf(Format.capitalize(f.getText()))).collect(toList()));
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
		variable.addFlags(tags.toArray(new Tag[0]));
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
		else if (OBJECT.equals(var.type())) return new NativeObjectRule(classFrom(params.get(0).getText()));
		return null;
	}

	private String classFrom(String text) {
		return text.startsWith("\"") ? text.substring(1, text.length() - 1) : text;
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
			values = values.stream().map(v -> Double.valueOf((Integer) v)).collect(toList());
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
					map(context -> {
						try {
							return INTEGER.convert(context.getText()).get(0);
						} catch (NumberFormatException e) {
							return LONG.convert(context.getText()).get(0);
						}
					}).collect(toList()));
		else if (!ctx.doubleValue().isEmpty())
			values.addAll(ctx.doubleValue().stream().
					map(context -> DOUBLE.convert(context.getText()).get(0)).collect(toList()));
		else if (!ctx.tupleValue().isEmpty())
			values.addAll(ctx.tupleValue().stream().
					map(context -> new AbstractMap.SimpleEntry<>(context.stringValue().getText(), DOUBLE.convert(context.doubleValue().getText()).get(0))).collect(toList()));
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
		return format(value.trim().replaceAll("==(=*)\\n", "").replaceAll("==(=*)", "")).replace("\\=", "=");
	}

	private String format(String text) {
		String pattern = pattern(text);
		StringBuilder result = new StringBuilder();
		for (String line : text.split("\\n")) result.append(line.replaceFirst(pattern, "")).append("\n");
		while (result.toString().endsWith("\n")) result = new StringBuilder(result.substring(0, result.length() - 1));
		return result.toString();
	}

	private String pattern(String text) {
		if (text.isEmpty() || !text.contains("\n")) return text;
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