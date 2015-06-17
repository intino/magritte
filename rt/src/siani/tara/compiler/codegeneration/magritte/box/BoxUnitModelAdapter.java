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
	private final boolean m0;


	public BoxUnitModelAdapter(String project,
	                           String generatedLanguage,
	                           Language language,
	                           Locale locale, Map<String, List<SimpleEntry<String, String>>> metrics,
	                           boolean m0) {
		this.project = project;
		this.generatedLanguage = generatedLanguage;
		this.language = language;
		this.locale = locale;
		this.metrics = metrics;
		this.m0 = m0;
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
		frame.addFrame(PROJECT, project).addFrame(TERMINAL, m0);
		if (!m0) frame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
		addMetricImports(frame);
		addFacetImports(model.getIncludedNodes(), frame);
		parserAllNodes(frame, model, FrameContext);
	}

	private void parserAllNodes(Frame frame, Node nodeContainer, FrameContext context) {
		nodeContainer.getIncludedNodes().stream().
			filter(node -> !(node instanceof NodeReference)).
			forEach(node -> fill(frame, node, context));
		for (Facet facet : nodeContainer.getFacets()) {
			createNativeFrames(frame, extractNativeParameters(facet));
			for (Node node : facet.getIncludedNodes()) {
				frame.addFrame(NODE, context.build(node));
				parserAllNodes(frame, node, context);
				fill(frame, node, context);
			}
		}
		if (nodeContainer.getFacetTargets() == null) return;
		for (FacetTarget facet : nodeContainer.getFacetTargets()) {
			frame.addFrame(NODE, context.build(facet));
			createDefaultValueNativeFrames(frame, extractNativeVariables(facet));
			for (Node node : facet.getIncludedNodes())
				fill(frame, node, context);
		}
	}

	private void fill(Frame frame, Node node, FrameContext FrameContext) {
		frame.addFrame(NODE, FrameContext.build(node));
		createNativeFrames(frame, extractNativeParameters(node));
		createDefaultValueNativeFrames(frame, extractNativeVariables(node));
		parserAllNodes(frame, node, FrameContext);
	}

	private void createNativeFrames(Frame frame, List<Parameter> parameters) {
		for (Parameter parameter : parameters) {
			if (!parameter.getInferredType().equals(Primitives.NATIVE)) continue;
			final Primitives.Expression body = (Primitives.Expression) parameter.getValues().get(0);
			String bodyText = body.get();
			final String signature = getSignature(parameter);
			Frame intentionFrame = new Frame().addTypes("intention").addFrame("body", formatBody(bodyText, signature));
			intentionFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
			intentionFrame.addFrame("varName", parameter.getName());
			intentionFrame.addFrame("container", cleanQn(buildContainerPath(parameter.getContract(), parameter.getOwner())));
			intentionFrame.addFrame("parentIntention", getScope(parameter));
			intentionFrame.addFrame("interface", cleanQn(getInterface(parameter)));
			intentionFrame.addFrame("signature", signature);
			intentionFrame.addFrame("path", cleanQn(NameFormatter.createNativeClassReference(parameter.getOwner(), parameter.getName())));
			frame.addFrame(INTENTION, intentionFrame);
		}
	}

	private String getScope(Parameter parameter) {
		if (parameter.getContract().contains(siani.tara.semantic.model.Variable.NATIVE_SEPARATOR)) {
			final String[] split = parameter.getContract().split(siani.tara.semantic.model.Variable.NATIVE_SEPARATOR);
			if (split.length == 3) return split[2].toLowerCase();
			else return language.languageName();
		} else return language.languageName();
	}

	private void createDefaultValueNativeFrames(Frame frame, List<Variable> variables) {
		for (Variable variable : variables) {
			if (!variable.getType().equals(Primitives.NATIVE) || variable.getDefaultValues().isEmpty()) continue;
			final Object next = variable.getDefaultValues().get(0);
			if (next instanceof EmptyNode) return;
			final String body = String.valueOf(next);
			final String signature = getSignature(variable);
			Frame intentionFrame = new Frame().addTypes("intention").addFrame("body", formatBody(body, signature));
			intentionFrame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
			intentionFrame.addFrame("varName", variable.getName());
			intentionFrame.addFrame("container", cleanQn(buildContainerPath(variable.getContract(), variable.getContainer())));
			intentionFrame.addFrame("parentIntention", generatedLanguage);
			intentionFrame.addFrame("interface", cleanQn(getInterface(variable)));
			intentionFrame.addFrame("signature", signature);
			intentionFrame.addFrame("path", NameFormatter.createNativeClassReference(variable.getContainer(), variable.getName()));
			frame.addFrame(INTENTION, intentionFrame);
		}
	}

	private String formatBody(String body, String signature) {
		final String returnText = "return ";
		body = body.endsWith(";") ? body : body + ";";
		if (!signature.contains(" void ") && !body.contains("\n") && !body.startsWith(returnText))
			return returnText + body;
		return body;
	}

	private static String getQn(Node owner, Node node, String language, boolean m0) {
		final FacetTarget facetTarget = facetTargetContainer(node);
		if (owner.isFacet())
			return composeMorphPackagePath(language) + DOT + owner.getName().toLowerCase() + DOT +
				Format.reference().format(owner.getName()) + "_" + Format.reference().format(facetTarget.getTarget());
		else
			return !m0 ? composeMorphPackagePath(language) + DOT + (facetTarget == null ? node.getQualifiedName() : composeInFacetTargetQN(node, facetTarget)) :
				language.toLowerCase() + DOT + node.getType();
	}

	private String getInterface(Variable variable) {
		return variable.getContract().contains(NATIVE_SEPARATOR) ? variable.getContract().substring(0, variable.getContract().indexOf(NATIVE_SEPARATOR)) : variable.getContract();
	}

	private String getInterface(Parameter parameter) {
		if (!parameter.getContract().contains(NATIVE_SEPARATOR))
			return "";//throw new SemanticException(new SemanticError("reject.native.signature.notfound", new LanguageParameter(parameter)));
		return parameter.getContract().substring(0, parameter.getContract().indexOf(NATIVE_SEPARATOR));
	}

	private String getSignature(Parameter parameter) {
		return !parameter.getContract().contains(NATIVE_SEPARATOR) ? parameter.getContract() :
			parameter.getContract().split(NATIVE_SEPARATOR)[1];
	}

	private String getSignature(Variable variable) {
		return variable.getContract().substring(variable.getContract().indexOf(NATIVE_SEPARATOR) + 1);
	}

	private String buildContainerPath(String contract, NodeContainer owner) {
		if (owner instanceof Node)
			return getQn(firstNoFeatureAndNamed(owner), (Node) owner, withContract(contract, generatedLanguage), m0);
		return NameFormatter.getQn((FacetTarget) owner, withContract(contract, generatedLanguage));
	}

	private String withContract(String contract, String language) {
		if (contract.contains(siani.tara.semantic.model.Variable.NATIVE_SEPARATOR)) {
			final String[] split = contract.split(siani.tara.semantic.model.Variable.NATIVE_SEPARATOR);
			if (split.length == 3) return split[2].toLowerCase();
			else return language;
		} else return language;
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
