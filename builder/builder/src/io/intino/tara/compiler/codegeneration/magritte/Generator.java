package io.intino.tara.compiler.codegeneration.magritte;

import io.intino.tara.compiler.codegeneration.magritte.layer.TypesProvider;
import io.intino.tara.compiler.model.NodeReference;
import io.intino.tara.compiler.model.VariableReference;
import io.intino.tara.lang.model.*;
import org.siani.itrules.Adapter;
import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.engine.adapters.ExcludeAdapter;
import org.siani.itrules.model.Frame;
import io.intino.tara.Language;
import io.intino.tara.compiler.codegeneration.Format;
import io.intino.tara.compiler.codegeneration.magritte.natives.NativeExtractor;
import io.intino.tara.lang.model.rules.variable.NativeObjectRule;
import io.intino.tara.lang.model.rules.variable.NativeRule;
import io.intino.tara.lang.model.rules.variable.VariableCustomRule;
import io.intino.tara.lang.model.rules.variable.WordRule;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.constraints.parameter.ReferenceParameter;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static io.intino.tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static io.intino.tara.lang.model.Primitive.OBJECT;
import static io.intino.tara.lang.model.Tag.Terminal;

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

	protected void addComponents(Frame frame, Node node, Adapter.FrameContext<FacetTarget> context) {
		if (node instanceof NodeReference) return;
		node.components().stream().
				filter(component -> !component.isAnonymous() && (!component.isReference() || (((NodeReference) component).isHas()))).
				forEach(component -> {
					final Frame nodeFrame = (Frame) context.build(component);
					nodeFrame.addTypes(OWNER);
					frame.addFrame(NODE, nodeFrame);
				});
	}

	protected String getType(Variable variable, String workingPacakge) {
		if (variable instanceof VariableReference)
			return NameFormatter.cleanQn(NameFormatter.getQn(((VariableReference) variable).getDestiny(), workingPacakge.toLowerCase()));
		else if (Primitive.WORD.equals(variable.type()))
			return variable.rule() != null && variable.rule() instanceof VariableCustomRule ?
					workingPacakge.toLowerCase() + ".rules." + Format.firstUpperCase().format(((VariableCustomRule) variable.rule()).getSource()) :
					Format.firstUpperCase().format(variable.name()).toString();
		else if (OBJECT.equals(variable.type())) return (((NativeObjectRule) variable.rule()).type());
		else return variable.type().name();
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
			frame.addFrame(QN, ((VariableCustomRule) rule).getLoadedClass().getName());
			if (((VariableCustomRule) rule).isMetric()) {
				frame.addTypes(METRIC);
				frame.addFrame(DEFAULT, ((VariableCustomRule) rule).getDefaultUnit());
			}
		}
		return frame;
	}

	protected Predicate<Tag> isLayerInterface() {
		return tag -> tag.equals(Tag.Component) || tag.equals(Tag.Feature) || tag.equals(Tag.Terminal)
				|| tag.equals(Tag.Private) || tag.equals(Tag.Volatile) || tag.equals(Tag.Versioned);
	}

	protected void addTerminalVariables(Node node, final Frame frame) {
		final List<Constraint> terminalCoreVariables = collectTerminalCoreVariables(node);
		if (node.parent() == null && !terminalCoreVariables.isEmpty()) {
			if (!Arrays.asList(frame.slots()).contains(META_TYPE.toLowerCase()))
				frame.addFrame(META_TYPE, languageWorkingPackage + DOT + metaType(node));
		}
		terminalCoreVariables.forEach(c -> addTerminalVariable(languageWorkingPackage + "." + node.type(), frame, (Constraint.Parameter) c, node.parent() != null, isRequired(node, (Constraint.Parameter) c), META_TYPE, languageWorkingPackage));
		addFacetVariables(node, frame);
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
			frame.addFrame(META_FACET, new Frame().addTypes(META_FACET).addFrame(NAME, facet.type()).addFrame(TYPE, metaType(facet)));
		collectTerminalFacetVariables(node).entrySet().forEach(entry -> entry.getValue().forEach(c ->
				addTerminalVariable(languageWorkingPackage + "." + node.type(), frame, (Constraint.Parameter) c, node.parent() != null, isRequired(node, (Constraint.Parameter) c), entry.getKey(), languageWorkingPackage)));
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

	private void addTerminalVariable(String type, Frame frame, Constraint.Parameter parameter, boolean inherited, boolean isRequired, String containerName, String languageWorkingPackage) {
		frame.addFrame(VARIABLE, createFrame(parameter, type, inherited, isRequired, containerName, languageWorkingPackage));
	}

	private Frame createFrame(final Constraint.Parameter parameter, String type, boolean inherited, boolean isRequired, String containerName, String workingPackage) {
		final Frame frame = new Frame();
		frame.addTypes(TypesProvider.getTypes(parameter, isRequired));
		if (inherited) frame.addTypes(INHERITED);
		frame.addTypes(META_TYPE);
		frame.addTypes(TARGET);
		frame.addFrame(NAME, parameter.name());
		frame.addFrame(CONTAINER_NAME, containerName);
		frame.addFrame(QN, type);
		frame.addFrame(LANGUAGE, language.languageName().toLowerCase());
		frame.addFrame(WORKING_PACKAGE, workingPackage);
		frame.addFrame(TYPE, type(parameter));
		if (parameter.type().equals(Primitive.WORD)) {
			final WordRule rule = (WordRule) parameter.rule();
			final List<String> words = rule.words();
			if (rule.isCustom()) {
				frame.addTypes(OUTDEFINED);
				frame.addFrame(EXTERNAL_CLASS, rule.externalWordClass());
			}
			frame.addFrame(WORD_VALUES, words.toArray(new String[words.size()]));
		}
		if (parameter.type().equals(Primitive.FUNCTION)) {
			final NativeRule rule = (NativeRule) parameter.rule();
			final String signature = rule.signature();
			NativeExtractor extractor = new NativeExtractor(signature);
			frame.addFrame("methodName", extractor.methodName());
			frame.addFrame("parameters", extractor.parameters());
			frame.addFrame("returnType", extractor.returnValue());
			frame.addFrame(RULE, rule.interfaceClass());
			frame.addFrame(OUT_LANGUAGE, parameter.scope());
			imports.addAll(rule.imports().stream().collect(Collectors.toList()));
		}
		if (!Arrays.asList(frame.slots()).contains(OUT_LANGUAGE.toLowerCase()))
			frame.addFrame(OUT_LANGUAGE, outDsl.toLowerCase());
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
		if (parent != null) frame.addFrame(PARENT, NameFormatter.cleanQn(NameFormatter.getQn(parent, workingPackage)));
	}
}
