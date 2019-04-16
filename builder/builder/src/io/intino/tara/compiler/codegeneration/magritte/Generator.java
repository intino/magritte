package io.intino.tara.compiler.codegeneration.magritte;

import io.intino.itrules.engine.Context;
import io.intino.itrules.engine.ExcludeAdapter;
import io.intino.itrules.engine.FrameBuilder;
import io.intino.itrules.model.Frame;
import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.Format;
import io.intino.tara.compiler.codegeneration.magritte.layer.TypesProvider;
import io.intino.tara.compiler.codegeneration.magritte.natives.NativeExtractor;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.compiler.model.VariableReference;
import io.intino.tara.lang.model.*;
import io.intino.tara.lang.model.rules.variable.NativeObjectRule;
import io.intino.tara.lang.model.rules.variable.NativeRule;
import io.intino.tara.lang.model.rules.variable.VariableCustomRule;
import io.intino.tara.lang.model.rules.variable.WordRule;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.constraints.parameter.ReferenceParameter;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static io.intino.tara.lang.model.Primitive.OBJECT;
import static io.intino.tara.lang.model.Tag.Terminal;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;

public abstract class Generator implements TemplateTags {

	protected final Language language;
	protected final String outDsl;
	protected final String workingPackage;
	protected final String languageWorkingPackage;
	protected Set<String> imports = new HashSet<>();

	public Generator(Language language, String outDsl, String workingPackage, String languageWorkingPackage) {
		this.language = language;
		this.outDsl = outDsl;
		this.workingPackage = workingPackage;
		this.languageWorkingPackage = languageWorkingPackage;
	}

	protected void addComponents(Frame frame, Node node, Context context) {
		if (node instanceof NodeReference) return;
		node.components().stream().
				filter(component -> !component.isAnonymous() && (!component.isReference() || (((NodeReference) component).isHas()))).
				forEach(component -> {
					final Frame nodeFrame = (Frame) context.build(component);
					nodeFrame.addTypes(OWNER);
					frame.addSlot(NODE, nodeFrame);
				});
	}

	protected String getType(Variable variable) {
		if (variable instanceof VariableReference)
			return cleanQn(getQn(((VariableReference) variable).getDestiny(), (((VariableReference) variable).isTypeReference() ? languageWorkingPackage : workingPackage).toLowerCase()));
		else if (Primitive.WORD.equals(variable.type()))
			return variable.rule() != null && variable.rule() instanceof VariableCustomRule ?
					workingPackage.toLowerCase() + ".rules." + Format.firstUpperCase().format(((VariableCustomRule) variable.rule()).getExternalWordClass()) :
					Format.firstUpperCase().format(variable.name()).toString();
		else if (OBJECT.equals(variable.type())) return (((NativeObjectRule) variable.rule()).type());
		else return variable.type().javaName();
	}

	protected static FacetTarget isInFacet(Node node) {
		NodeContainer container = node.container();
		while (container != null && ((Node) container).facetTarget() == null)
			container = container.container();
		return container != null ? ((Node) container).facetTarget() : null;
	}

	protected Frame ruleToFrame(Rule rule) {
		if (rule == null) return null;
		final FrameBuilder frameBuilder = new FrameBuilder();
		frameBuilder.register(Rule.class, new ExcludeAdapter<>("loadedClass"));
		final Frame frame = (Frame) frameBuilder.build(rule);
		if (rule instanceof VariableCustomRule) {
			frame.addSlot(QN, ((VariableCustomRule) rule).qualifiedName());
			if (((VariableCustomRule) rule).isMetric()) {
				frame.addTypes(METRIC);
				frame.addSlot(DEFAULT, ((VariableCustomRule) rule).getDefaultUnit());
			}
		}
		return frame;
	}

	protected Predicate<Tag> isLayerInterface() {
		return tag -> tag.equals(Tag.Component) || tag.equals(Tag.Feature) || tag.equals(Tag.Terminal)
				|| tag.equals(Tag.Private) || tag.equals(Tag.Volatile);
	}

	protected void addTerminalVariables(Node node, final Frame frame) {
		final List<Constraint> terminalCoreVariables = collectTerminalCoreVariables(node);
		if (node.parent() == null && !terminalCoreVariables.isEmpty()) {
			if (!asList(frame.slots()).contains(META_TYPE.toLowerCase()))
				frame.addSlot(META_TYPE, languageWorkingPackage + DOT + metaType(node));
		}
		terminalCoreVariables.forEach(c -> addTerminalVariable(node, languageWorkingPackage + "." + node.type(), frame, (Constraint.Parameter) c, node.parent() != null, isRequired(node, (Constraint.Parameter) c), META_TYPE, languageWorkingPackage));
		addFacetVariables(node, frame);
		if (!asList(frame.slots()).contains(CONTAINER))
			frame.addSlot(CONTAINER, node.name() + facetName(node.facetTarget()));
	}

	private boolean isRequired(Node node, Constraint.Parameter allow) {
		Node n = node.isReference() ? node.destinyOfReference() : node;
		while (n != null) {
			for (Parameter parameter : n.parameters())
				if (parameter.name().equals(allow.name())) return false;
			n = n.parent();
		}
		return true;
	}

	private void addFacetVariables(Node node, Frame frame) {
		for (Facet facet : node.facets())
			frame.addSlot(META_FACET, new Frame().addTypes(META_FACET).addSlot(NAME, facet.type()).addSlot(TYPE, metaType(facet)));
		collectTerminalFacetVariables(node).forEach((key, value) -> value.forEach(c ->
				addTerminalVariable(node, languageWorkingPackage + "." + node.type(), frame, (Constraint.Parameter) c, node.parent() != null, isRequired(node, (Constraint.Parameter) c), key, languageWorkingPackage)));
	}

	private List<Constraint> collectTerminalCoreVariables(Node node) {
		final Collection<Constraint> allows = language.constraints(node.type());
		if (allows == null) return emptyList();
		return allows.stream().filter(allow -> allow instanceof Constraint.Parameter &&
				((Constraint.Parameter) allow).flags().contains(Terminal) &&
				!isRedefined((Constraint.Parameter) allow, node.variables())).collect(Collectors.toList());
	}

	private Map<String, List<Constraint>> collectTerminalFacetVariables(Node node) {
		return collectFacetConstrains(language.constraints(node.type()), node);
	}

	private static Map<String, List<Constraint>> collectFacetConstrains(List<Constraint> constraints, Node node) {
		if (constraints == null) return emptyMap();
		Map<String, List<Constraint>> map = new HashMap<>();
		final List<Constraint> facets = constraints.stream().filter(c -> c instanceof Constraint.Facet && hasFacet(node, ((Constraint.Facet) c).type())).collect(Collectors.toList());
		for (Constraint facet : facets) map.put(((Constraint.Facet) facet).type(), new ArrayList<>());
		facets.forEach(f -> map.put(((Constraint.Facet) f).type(), ((Constraint.Facet) f).constraints().stream().filter(byTerminalParameters(node)).collect(Collectors.toList())));
		return map;
	}

	private static boolean hasFacet(Node node, String type) {
		for (Facet facet : node.facets()) if (facet.type().equals(type)) return true;
		return false;
	}

	private static Predicate<Constraint> byTerminalParameters(Node node) {
		return o -> o instanceof Constraint.Parameter &&
				((Constraint.Parameter) o).flags().contains(Terminal) &&
				!isRedefined((Constraint.Parameter) o, node.variables());
	}

	private String metaType(Facet facet) {
		for (String key : language.catalog().keySet())
			if (key.startsWith(facet.type() + ":"))
				return language.catalog().get(key).doc().layer();
		return "";
	}

	protected String metaType(Node node) {
		final String type = node.type();
		return type.contains(":") ? type.split(":")[0].toLowerCase() + "." + node.type().replace(":", "") : node.type();
	}

	private static boolean isRedefined(Constraint.Parameter allow, List<? extends Variable> variables) {
		for (Variable variable : variables) if (variable.name().equals(allow.name())) return true;
		return false;
	}

	private void addTerminalVariable(Node node, String type, Frame frame, Constraint.Parameter parameter, boolean inherited, boolean isRequired, String containerName, String languageWorkingPackage) {
		Frame varFrame = createFrame(parameter, type, inherited, isRequired, containerName, languageWorkingPackage);
		if (!asList(varFrame.slots()).contains(CONTAINER))
			varFrame.addSlot(CONTAINER, node.name() + facetName(node.facetTarget()));
		frame.addSlot(VARIABLE, varFrame);
	}

	protected String facetName(FacetTarget facetTarget) {
		return facetTarget != null ? facetTarget.targetNode().name().replace(".", "") : "";
	}

	private Frame createFrame(final Constraint.Parameter parameter, String type, boolean inherited, boolean isRequired, String containerName, String workingPackage) {
		final Frame frame = new Frame();
		frame.addTypes(TypesProvider.getTypes(parameter, isRequired));
		if (inherited) frame.addTypes(INHERITED);
		frame.addTypes(META_TYPE);
		frame.addTypes(TARGET);
		frame.addSlot(NAME, parameter.name());
		frame.addSlot(CONTAINER_NAME, containerName);
		frame.addSlot(QN, type);
		frame.addSlot(LANGUAGE, language.languageName().toLowerCase());
		frame.addSlot(WORKING_PACKAGE, workingPackage);
		frame.addSlot(TYPE, type(parameter));
		if (parameter.type().equals(Primitive.WORD)) {
			final WordRule rule = (WordRule) parameter.rule();
			final List<String> words = rule.words();
			if (rule.isCustom()) {
				frame.addTypes(OUTDEFINED);
				frame.addSlot(EXTERNAL_CLASS, rule.externalWordClass());
			}
			frame.addSlot(WORD_VALUES, words.toArray(new String[words.size()]));
		}
		if (parameter.type().equals(Primitive.FUNCTION)) {
			final NativeRule rule = (NativeRule) parameter.rule();
			final String signature = rule.signature();
			NativeExtractor extractor = new NativeExtractor(signature);
			frame.addSlot("methodName", extractor.methodName());
			frame.addSlot("parameters", extractor.parameters());
			frame.addSlot("returnType", extractor.returnType());
			frame.addSlot(RULE, rule.interfaceClass());
			frame.addSlot(OUT_LANGUAGE, parameter.scope());
			imports.addAll(new ArrayList<>(rule.imports()));
		}
		if (!asList(frame.slots()).contains(OUT_LANGUAGE.toLowerCase()))
			frame.addSlot(OUT_LANGUAGE, outDsl.toLowerCase());
		return frame;
	}

	private String type(Constraint.Parameter parameter) {
		if (parameter instanceof ReferenceParameter)
			return languageWorkingPackage + DOT + ((ReferenceParameter) parameter).referenceType();
		else return parameter.type().getName();
	}

	public Set<String> getImports() {
		return imports;
	}

	protected void addParent(Frame frame, Node node) {
		final Node parent = node.parent();
		if (parent != null) frame.addSlot(PARENT, cleanQn(getQn(parent, workingPackage)));
		final List<String> slots = asList(frame.slots());
		if ((slots.contains(CREATE) || slots.contains(NODE)) || !node.children().isEmpty()) {
			frame.addSlot(PARENT_SUPER, node.parent() != null);
			if (node.parent() != null) frame.addSlot("parentName", cleanQn(getQn(parent, workingPackage)));
		}
		if ((slots.contains(NODE)) && node.parent() != null && !node.parent().components().isEmpty()) {
			frame.addSlot("parentClearName", cleanQn(getQn(parent, workingPackage)));
		}
	}
}
