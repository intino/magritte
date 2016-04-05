package tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.engine.adapters.ExcludeAdapter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.layer.TypesProvider;
import tara.compiler.codegeneration.magritte.natives.NativeExtractor;
import tara.compiler.model.NodeReference;
import tara.compiler.model.VariableReference;
import tara.lang.model.*;
import tara.lang.model.rules.variable.CustomRule;
import tara.lang.model.rules.variable.NativeObjectRule;
import tara.lang.model.rules.variable.NativeRule;
import tara.lang.model.rules.variable.WordRule;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.constraints.parameter.ReferenceParameter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static tara.lang.model.Primitive.OBJECT;

public abstract class Generator implements TemplateTags {

	protected final Language language;
	protected final String generatedLanguage;
	protected Set<String> imports = new HashSet<>();

	public Generator(Language language, String generatedLanguage) {
		this.language = language;
		this.generatedLanguage = generatedLanguage;
	}

	protected void addComponents(Frame frame, NodeContainer nodeContainer, Adapter.FrameContext<FacetTarget> context) {
		if (nodeContainer instanceof NodeReference) return;
		nodeContainer.components().stream().
			filter(inner -> !inner.isAnonymous() && (!inner.isReference() || (((NodeReference) inner).isHas()))).
			forEach(component -> {
				final Frame nodeFrame = (Frame) context.build(component);
				nodeFrame.addTypes(OWNER);
				frame.addFrame(NODE, nodeFrame);
			});
	}

	protected String getType(Variable variable, String generatedLanguage) {
		if (variable instanceof VariableReference)
			return getQn(((VariableReference) variable).getDestiny(), generatedLanguage.toLowerCase());
		else if (Primitive.WORD.equals(variable.type()))
			return variable.rule() != null && variable.rule() instanceof CustomRule ?
				generatedLanguage.toLowerCase() + ".rules." + Format.firstUpperCase().format(((CustomRule) variable.rule()).getSource()) :
				Format.firstUpperCase().format(variable.name()).toString();
		else if (OBJECT.equals(variable.type())) return (((NativeObjectRule) variable.rule()).type());
		else return variable.type().name();
	}

	protected static FacetTarget isInFacet(Node node) {
		NodeContainer container = node.container();
		while (container != null && (container instanceof Node) && ((Node) container).facetTarget() == null)
			container = container.container();
		return container != null && container instanceof Node ? ((Node) container).facetTarget() : null;
	}

	protected Frame ruleToFrame(Rule rule) {
		if (rule == null) return null;
		final FrameBuilder frameBuilder = new FrameBuilder();
		frameBuilder.register(Rule.class, new ExcludeAdapter<>("loadedClass"));
		final Frame frame = (Frame) frameBuilder.build(rule);
		if (rule instanceof CustomRule) {
			frame.addFrame(QN, ((CustomRule) rule).getLoadedClass().getName());
			if (((CustomRule) rule).isMetric()) {
				frame.addTypes(METRIC);
				frame.addFrame(DEFAULT, ((CustomRule) rule).getDefaultUnit());
			}
		}
		return frame;
	}

	protected void addTerminalVariables(Node node, final Frame frame) {
		final Collection<Constraint> allows = language.constraints(node.type());
		if (allows == null) return;
		final List<Constraint> terminalVariables = allows.stream().
			filter(allow -> allow instanceof Constraint.Parameter &&
				((Constraint.Parameter) allow).flags().contains(Tag.Terminal) &&
				!isRedefined((Constraint.Parameter) allow, node.variables())).collect(Collectors.toList());
		if (terminalVariables.isEmpty()) return;
		if (node.parent() == null)
			frame.addFrame(TYPE_INSTANCE, language.languageName().toLowerCase() + DOT + typeInstance(node));
		terminalVariables.forEach(allow -> addTerminalVariable(node.language().toLowerCase() + "." + node.type(), frame, (Constraint.Parameter) allow, node.parent() != null));
	}

	private String typeInstance(Node node) {
		final String type = node.type();
		return type.contains(":") ? type.split(":")[0].toLowerCase() + "." + node.type().replace(":", "") : node.type();
	}

	private boolean isRedefined(Constraint.Parameter allow, List<? extends Variable> variables) {
		for (Variable variable : variables) if (variable.name().equals(allow.name())) return true;
		return false;
	}

	private void addTerminalVariable(String type, Frame frame, Constraint.Parameter parameter, boolean inherited) {
		frame.addFrame(VARIABLE, createFrame(parameter, type, inherited));
	}

	private Frame createFrame(final Constraint.Parameter parameter, String type, boolean inherited) {
		final Frame frame = new Frame();
		frame.addTypes(TypesProvider.getTypes(parameter));
		if (inherited) frame.addTypes(INHERITED);
		frame.addTypes(METATYPE);
		frame.addTypes(TARGET);
		frame.addFrame(NAME, parameter.name());
		frame.addFrame(CONTAINER_NAME, "metaType");
		frame.addFrame(QN, type);
		frame.addFrame(LANGUAGE, language.languageName().toLowerCase());
		frame.addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
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
			final String signature = ((NativeRule) parameter.rule()).signature();
			NativeExtractor extractor = new NativeExtractor(signature);
			frame.addFrame("methodName", extractor.methodName());
			frame.addFrame("parameters", extractor.parameters());
			frame.addFrame("returnType", extractor.returnValue());
			imports.addAll(((NativeRule) parameter.rule()).imports().stream().collect(Collectors.toList()));
		}
		return frame;
	}

	private String type(Constraint.Parameter parameter) {
		if (parameter instanceof ReferenceParameter)
			return language.languageName().toLowerCase() + DOT + ((ReferenceParameter) parameter).referenceType();
		else return parameter.type().getName();
	}

	public Set<String> getImports() {
		return imports;
	}


	protected void addParent(Frame frame, Node node) {
		final Node parent = node.parent();
		if (parent != null) frame.addFrame(PARENT, getQn(parent, generatedLanguage));
	}
}
