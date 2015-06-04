package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.engine.formatters.PluralFormatter;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.codegeneration.Format;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeReference;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.stream.Collectors;

import static siani.tara.compiler.codegeneration.magritte.NameFormatter.*;
import static siani.tara.compiler.codegeneration.magritte.TemplateTags.DOT;
import static siani.tara.compiler.codegeneration.magritte.TemplateTags.*;
import static siani.tara.compiler.model.Variable.NATIVE_SEPARATOR;

public class BoxModelAdapter implements Adapter<Model> {
	private final String project;
	private final String generatedLanguage;
	private final Language language;
	private final Locale locale;
	private final Map<String, List<SimpleEntry<String, String>>> metrics;
	private final boolean terminal;


	public BoxModelAdapter(String project,
	                       String generatedLanguage,
	                       Language language,
	                       Locale locale, Map<String, List<SimpleEntry<String, String>>> metrics,
	                       boolean terminal) {
		this.project = project;
		this.generatedLanguage = generatedLanguage;
		this.language = language;
		this.locale = locale;
		this.metrics = metrics;
		this.terminal = terminal;
	}

	@Override
	public void execute(Frame frame, Model model, FrameContext FrameContext) {
		frame.addFrame(NAME, capitalize(generatedLanguage) + buildFileName(model.getFile()));
		if (!Objects.equals(language.languageName(), "Proteo")) frame.addFrame(LANGUAGE, language.languageName());
		frame.addFrame("project", project).addFrame("generatedLanguage", generatedLanguage).addFrame("terminal", terminal);
		addMetricImports(frame);
		addFacetImports(model.getIncludedNodes(), frame);
		parserAllNodes(frame, model, FrameContext);
	}


	private void parserAllNodes(Frame frame, Node nodeContainer, FrameContext context) {
		for (Node node : nodeContainer.getIncludedNodes()) {
			if (node instanceof NodeReference) continue;
			fill(frame, node, context);
		}
		for (Facet facet : nodeContainer.getFacets())
			for (Node node : facet.getIncludedNodes()) {
				frame.addFrame(NODE, context.build(node));
				parserAllNodes(frame, node, context);
			}
		if (nodeContainer.getFacetTargets() == null) return;
		for (FacetTarget facet : nodeContainer.getFacetTargets()) {
			frame.addFrame(NODE, context.build(facet));
			for (Node node : facet.getIncludedNodes()) fill(frame, node, context);
		}
	}

	private void fill(Frame frame, Node node, FrameContext FrameContext) {
		frame.addFrame("node", FrameContext.build(node));
		if (!node.isAbstract()) {
			createIntentionFrames(frame, extractNativeParameters(node));
			createDefaultIntentionFrames(frame, extractNativeVariables(node));
		}
		parserAllNodes(frame, node, FrameContext);
	}

	private void createIntentionFrames(Frame frame, List<Parameter> parameters) {
		for (Parameter parameter : parameters) {
			if (!parameter.getInferredType().equals(Primitives.NATIVE)) continue;
			final String body = (String) parameter.getValues().get(0);
			Frame intentionFrame = new Frame().addTypes("intention").addFrame("body", body.endsWith(";") ? body : body + ";");
			intentionFrame.addFrame("generatedLanguage", generatedLanguage);
			intentionFrame.addFrame("varName", parameter.getName());
			intentionFrame.addFrame("container", buildContainerPath(parameter.getOwner()));
			intentionFrame.addFrame("parentIntention", language.languageName());
			intentionFrame.addFrame("interface", getInterface(parameter));
			intentionFrame.addFrame("signature", getSignature(parameter));
			intentionFrame.addFrame("path", NameFormatter.createNativeClassReference(parameter.getOwner(), parameter.getName()));
			frame.addFrame("intention", intentionFrame);
		}
	}

	private void createDefaultIntentionFrames(Frame frame, List<Variable> variables) {
		for (Variable variable : variables) {
			if (!variable.getType().equals(Primitives.NATIVE) || variable.getDefaultValues().isEmpty()) continue;
			final Object next = variable.getDefaultValues().get(0);
			if (next instanceof EmptyNode) return;
			final String body = String.valueOf(next);
			Frame intentionFrame = new Frame().addTypes("intention").addFrame("body", body.endsWith(";") ? body : body + ";");
			intentionFrame.addFrame("generatedLanguage", generatedLanguage);
			intentionFrame.addFrame("varName", variable.getName());
			intentionFrame.addFrame("container", buildContainerPath(variable.getContainer()));
			intentionFrame.addFrame("parentIntention", generatedLanguage);
			intentionFrame.addFrame("interface", getInterface(variable));
			intentionFrame.addFrame("signature", getSignature(variable));
			intentionFrame.addFrame("path", NameFormatter.createNativeClassReference(variable.getContainer(), variable.getName()));
			frame.addFrame("intention", intentionFrame);
		}
	}

	private String getInterface(Variable variable) {
		return variable.getContract().substring(0, variable.getContract().indexOf(NATIVE_SEPARATOR));
	}

	private String getInterface(Parameter parameter) {
		return parameter.getContract().substring(0, parameter.getContract().indexOf(NATIVE_SEPARATOR));
	}

	private String getSignature(Parameter parameter) {
		return parameter.getContract().substring(parameter.getContract().indexOf(NATIVE_SEPARATOR) + 1);
	}

	private String getSignature(Variable variable) {
		return variable.getContract().substring(variable.getContract().indexOf(NATIVE_SEPARATOR) + 1);
	}

	private String buildContainerPath(NodeContainer owner) {
		if (owner instanceof Node) return getQn(firstNoFeatureAndUnnamed(owner), (Node) owner, generatedLanguage);
		return NameFormatter.getQn((FacetTarget) owner, generatedLanguage);
//		if (owner instanceof Node && !((Node) owner).isAnonymous()) return format(owner.getQualifiedName());
//		return format(getFirstNamed(owner));
	}

	public static String getQn(Node owner, Node node, String generatedLanguage) {
		final FacetTarget facetTarget = facetTargetContainer(node);
		if (owner.isFacet())
			return composeMorphPackagePath(generatedLanguage) + DOT + owner.getName().toLowerCase() + DOT + Format.reference().format(facetTarget.getTarget());
		else
			return composeMorphPackagePath(generatedLanguage) + DOT + (facetTarget == null ? node.getQualifiedName() : composeInFacetTargetQN(node, facetTarget));
	}

	private static FacetTarget facetTargetContainer(Node node) {
		NodeContainer container = node.getContainer();
		while (container != null) if (container instanceof FacetTarget) return (FacetTarget) container;
		else container = container.getContainer();
		return null;
	}

	private Node firstNoFeatureAndUnnamed(NodeContainer owner) {
		NodeContainer container = owner;
		while (container != null) {
			if (container instanceof Node && !((Node) container).isFeature() && !((Node) container).isAnonymous())
				return (Node) container;
			container = container.getContainer();
		}
		return null;
	}

	private List<Parameter> extractNativeParameters(Node node) {
		return Collections.unmodifiableList(node.getParameters().stream().
			filter(parameter -> parameter.getInferredType().equals(Primitives.NATIVE)).
			collect(Collectors.toList()));
	}

	private List<Variable> extractNativeVariables(Node node) {
		return Collections.unmodifiableList(node.getVariables().stream().
			filter(variable -> variable.getType().equals(Primitives.NATIVE)).
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
				map(facet -> new PluralFormatter(locale).getInflector().plural(facet.getType())).
				collect(Collectors.toList()));
			imports.addAll(searchFacets(node.getIncludedNodes()));
		}
		return imports;
	}

}
