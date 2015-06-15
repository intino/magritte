package siani.tara.compiler.codegeneration.magritte.box;

import org.siani.itrules.Adapter;
import org.siani.itrules.engine.formatters.PluralFormatter;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.codegeneration.Format;
import siani.tara.compiler.codegeneration.magritte.NameFormatter;
import siani.tara.compiler.codegeneration.magritte.TemplateTags;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.semantic.model.Primitives;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;
import static siani.tara.compiler.codegeneration.magritte.NameFormatter.*;
import static siani.tara.compiler.model.Variable.NATIVE_SEPARATOR;

public class BoxUnitModelAdapter implements Adapter<Model>, TemplateTags {
	private final String project;
	private final String generatedLanguage;
	private final Language language;
	private final Locale locale;
	private final Map<String, List<SimpleEntry<String, String>>> metrics;
	private final boolean scene;


	public BoxUnitModelAdapter(String project,
	                           String generatedLanguage,
	                           Language language,
	                           Locale locale, Map<String, List<SimpleEntry<String, String>>> metrics,
	                           boolean scene) {
		this.project = project;
		this.generatedLanguage = generatedLanguage;
		this.language = language;
		this.locale = locale;
		this.metrics = metrics;
		this.scene = scene;
	}

	public static String getQn(Node owner, Node node, String generatedLanguage) {
		final FacetTarget facetTarget = facetTargetContainer(node);
		if (owner.isFacet())
			return composeMorphPackagePath(generatedLanguage) + DOT + owner.getName().toLowerCase() + DOT +
				Format.reference().format(owner.getName()) + "_" + Format.reference().format(facetTarget.getTarget());
		else
			return composeMorphPackagePath(generatedLanguage) + DOT + (facetTarget == null ? node.getQualifiedName() : composeInFacetTargetQN(node, facetTarget));
	}

	private static FacetTarget facetTargetContainer(Node node) {
		NodeContainer container = node.getContainer();
		while (container != null) if (container instanceof FacetTarget) return (FacetTarget) container;
		else container = container.getContainer();
		return null;
	}

	@Override
	public void execute(Frame frame, Model model, FrameContext FrameContext) {
		frame.addFrame(NAME, capitalize(generatedLanguage) + buildFileName(model.getFile()));
		if (!Objects.equals(language.languageName(), "Proteo")) frame.addFrame(LANGUAGE, language.languageName());
		frame.addFrame(PROJECT, project).addFrame(TERMINAL, scene);
		if (!scene) frame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
		addMetricImports(frame);
		addFacetImports(model.getIncludedNodes(), frame);
		parserAllNodes(frame, model, FrameContext);
	}

	private void parserAllNodes(Frame frame, Node nodeContainer, FrameContext context) {
		nodeContainer.getIncludedNodes().stream().
			filter(node -> !(node instanceof NodeReference)).
			forEach(node -> fill(frame, node, context));
		for (Facet facet : nodeContainer.getFacets()) {
			createIntentionFrames(frame, extractNativeParameters(facet));
			for (Node node : facet.getIncludedNodes()) {
				frame.addFrame(NODE, context.build(node));
				parserAllNodes(frame, node, context);
				fill(frame, node, context);
			}
		}
		if (nodeContainer.getFacetTargets() == null) return;
		for (FacetTarget facet : nodeContainer.getFacetTargets()) {
			frame.addFrame(NODE, context.build(facet));
			createDefaultValueIntentionFrames(frame, extractNativeVariables(facet));
			for (Node node : facet.getIncludedNodes())
				fill(frame, node, context);
		}
	}

	private void fill(Frame frame, Node node, FrameContext FrameContext) {
		frame.addFrame(NODE, FrameContext.build(node));
		createIntentionFrames(frame, extractNativeParameters(node));
		createDefaultValueIntentionFrames(frame, extractNativeVariables(node));
		parserAllNodes(frame, node, FrameContext);
	}

	private void createIntentionFrames(Frame frame, List<Parameter> parameters) {
		for (Parameter parameter : parameters) {
			if (!parameter.getInferredType().equals(Primitives.NATIVE)) continue;
			final Primitives.Expression body = (Primitives.Expression) parameter.getValues().get(0);
			String bodyText = body.get();
			Frame intentionFrame = new Frame().addTypes("intention").addFrame("body", bodyText.endsWith(";") ? bodyText : bodyText + ";");
			intentionFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
			intentionFrame.addFrame("varName", parameter.getName());
			intentionFrame.addFrame("container", buildContainerPath(parameter.getOwner()));
			intentionFrame.addFrame("parentIntention", language.languageName());
			intentionFrame.addFrame("interface", getInterface(parameter));
			intentionFrame.addFrame("signature", getSignature(parameter));
			intentionFrame.addFrame("path", NameFormatter.createNativeClassReference(parameter.getOwner(), parameter.getName()));
			frame.addFrame(INTENTION, intentionFrame);
		}
	}

	private void createDefaultValueIntentionFrames(Frame frame, List<Variable> variables) {
		for (Variable variable : variables) {
			if (!variable.getType().equals(Primitives.NATIVE) || variable.getDefaultValues().isEmpty()) continue;
			final Object next = variable.getDefaultValues().get(0);
			if (next instanceof EmptyNode) return;
			final String body = String.valueOf(next);
			Frame intentionFrame = new Frame().addTypes("intention").addFrame("body", body.endsWith(";") ? body : body + ";");
			intentionFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
			intentionFrame.addFrame("varName", variable.getName());
			intentionFrame.addFrame("container", buildContainerPath(variable.getContainer()));
			intentionFrame.addFrame("parentIntention", generatedLanguage);
			intentionFrame.addFrame("interface", getInterface(variable));
			intentionFrame.addFrame("signature", getSignature(variable));
			intentionFrame.addFrame("path", NameFormatter.createNativeClassReference(variable.getContainer(), variable.getName()));
			frame.addFrame(INTENTION, intentionFrame);
		}
	}

	private String getInterface(Variable variable) {
		return variable.getContract().substring(0, variable.getContract().indexOf(NATIVE_SEPARATOR));
	}

	private String getInterface(Parameter parameter) {
		if (!parameter.getContract().contains(NATIVE_SEPARATOR))
			return "";//throw new SemanticException(new SemanticError("reject.native.signature.notfound", new LanguageParameter(parameter)));
		return parameter.getContract().substring(0, parameter.getContract().indexOf(NATIVE_SEPARATOR));
	}

	private String getSignature(Parameter parameter) {
		return parameter.getContract().substring(parameter.getContract().indexOf(NATIVE_SEPARATOR) + 1);
	}

	private String getSignature(Variable variable) {
		return variable.getContract().substring(variable.getContract().indexOf(NATIVE_SEPARATOR) + 1);
	}

	private String buildContainerPath(NodeContainer owner) {
		if (owner instanceof Node)
			return getQn(firstNoFeatureAndNamed(owner), (Node) owner, generatedLanguage);
		return NameFormatter.getQn((FacetTarget) owner, generatedLanguage);
	}

	private Node firstNoFeatureAndNamed(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && !((Node) container).isAnonymous() &&
				!((Node) container).isFeatureInstance())
				return (Node) container;
			container = container.getContainer();
		}
		return null;
	}

	private List<Parameter> extractNativeParameters(Parametrized node) {
		return unmodifiableList(node.getParameters().stream().
			filter(parameter -> parameter.getInferredType().equals(Primitives.NATIVE)).
			collect(Collectors.toList()));
	}

	private List<Variable> extractNativeVariables(NodeContainer node) {
		return unmodifiableList(node.getVariables().stream().
			filter(variable -> variable.getType().equals(Primitives.NATIVE) && !variable.isInherited()).
			collect(Collectors.toList()));
	}

	private void addMetricImports(Frame frame) {
		for (String metric : metrics.keySet())
			frame.addFrame("importMetric", IMPORT + " " + STATIC + " " + project.toLowerCase() + DOT + METRICS + DOT + metric + DOT + STAR + SEMICOLON);
	}

	private void addFacetImports(Collection<Node> nodes, Frame frame) {
		Set<String> imports = searchFacets(nodes);
		for (String anImport : imports)
			frame.addFrame("importFacet", IMPORT + " " + project.toLowerCase() + DOT + EXTENSIONS + DOT + anImport.toLowerCase() + DOT + STAR + SEMICOLON);
	}

	private Set<String> searchFacets(Collection<Node> nodes) {
		Set<String> imports = new HashSet<>();
		for (Node node : nodes) {
			if (node instanceof NodeReference) continue;
			imports.addAll(node.getFacets().stream().
				map(facet -> new PluralFormatter(locale).getInflector().plural(facet.getFacetType())).
				collect(Collectors.toList()));
			imports.addAll(searchFacets(node.getIncludedNodes()));
		}
		return imports;
	}

}
