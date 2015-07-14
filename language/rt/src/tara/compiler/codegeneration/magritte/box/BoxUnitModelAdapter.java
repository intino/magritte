package tara.compiler.codegeneration.magritte.box;

import org.siani.itrules.Adapter;
import org.siani.itrules.engine.formatters.PluralFormatter;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.Parametrized;
import tara.compiler.model.*;
import tara.compiler.model.impl.Model;
import tara.compiler.model.impl.NodeReference;
import tara.semantic.model.Primitives;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

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

	@Override
	public void execute(Frame frame, Model model, FrameContext FrameContext) {
		frame.addFrame(NAME, NameFormatter.capitalize(generatedLanguage) + NameFormatter.buildFileName(model.file()));
		if (!Objects.equals(language.languageName(), "Proteo")) frame.addFrame(LANGUAGE, language.languageName());
		frame.addFrame(PROJECT, project).addFrame(TERMINAL, m0);
		if (!m0) frame.addFrame(GENERATED_LANGUAGE, generatedLanguage);
		addMetricImports(frame);
		addFacetImports(model.components(), frame);
		parserNodeContainer(frame, model, FrameContext);
	}

	private void parserNodeContainer(Frame frame, Node nodeContainer, FrameContext context) {
		nodeContainer.components().stream().
			filter(node -> !(node instanceof NodeReference)).
			forEach(node -> fill(frame, node, context));
		parseFacets(frame, nodeContainer, context);
		if (nodeContainer.facetTargets() == null) return;
		parseFacetTargets(frame, nodeContainer, context);
	}

	private void parseFacets(Frame frame, Node nodeContainer, FrameContext context) {
		for (Facet facet : nodeContainer.facets()) {
			createNativeFrames(frame, extractNativeParameters(facet));
			for (Node node : facet.components()) {
				frame.addFrame(NODE, context.build(node));
				parserNodeContainer(frame, node, context);
				fill(frame, node, context);
			}
		}
	}

	private void parseFacetTargets(Frame frame, Node nodeContainer, FrameContext context) {
		for (FacetTarget facet : nodeContainer.facetTargets()) {
			frame.addFrame(NODE, context.build(facet));
			createDefaultValueNativeFrames(frame, extractNativeVariables(facet));
			for (Node node : facet.components())
				fill(frame, node, context);
		}
	}

	private void fill(Frame frame, Node node, FrameContext FrameContext) {
		createNativeFrames(frame, extractNativeParameters(node));
		createDefaultValueNativeFrames(frame, extractNativeVariables(node));
		frame.addFrame(NODE, FrameContext.build(node));
		parserNodeContainer(frame, node, FrameContext);
	}

	private void createNativeFrames(Frame frame, List<Parameter> parameters) {
		final BoxNativeFrameAdapter adapter = new BoxNativeFrameAdapter(generatedLanguage, language, m0);
		for (Parameter parameter : parameters) {
			if (!(parameter.values().get(0) instanceof Primitives.Expression)) continue;
			final Primitives.Expression body = (Primitives.Expression) parameter.values().get(0);
			String value = body.get();
			if (Primitives.NATIVE.equals(parameter.inferredType())) {
				adapter.fillFrameForNativeParameter(frame, parameter, value);
			} else adapter.fillFrameExpressionParameter(frame, parameter, value);
		}
	}

	private void createDefaultValueNativeFrames(Frame frame, List<Variable> variables) {
		final BoxNativeFrameAdapter adapter = new BoxNativeFrameAdapter(generatedLanguage, language, m0);
		for (Variable variable : variables) {
			if (variable.getDefaultValues().isEmpty() || !(variable.getDefaultValues().get(0) instanceof Primitives.Expression))
				continue;
			final Object next = variable.getDefaultValues().get(0);
			if (Primitives.NATIVE.equals(variable.type()))
				adapter.fillFrameForNativeVariable(frame, variable, next);
			else adapter.fillFrameExpressionVariable(frame, variable, next);
		}
	}

	private List<Parameter> extractNativeParameters(Parametrized node) {
		return unmodifiableList(node.getParameters().stream().
			filter(parameter -> parameter.getValues().get(0) instanceof Primitives.Expression).
			collect(Collectors.toList()));
	}

	private List<Variable> extractNativeVariables(NodeContainer node) {
		return unmodifiableList(node.variables().stream().
			filter(variable -> !variable.getDefaultValues().isEmpty() && variable.getDefaultValues().get(0) instanceof Primitives.Expression && !variable.isInherited()).
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
			imports.addAll(node.facets().stream().
				map(facet -> new PluralFormatter(locale).getInflector().plural(facet.getFacetType())).
				collect(Collectors.toList()));
			imports.addAll(searchFacets(node.components()));
		}
		return imports;
	}

}
