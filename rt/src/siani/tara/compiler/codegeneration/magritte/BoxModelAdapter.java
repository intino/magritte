package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.formatter.InflectorFactory;
import org.siani.itrules.framebuilder.Adapter;
import org.siani.itrules.framebuilder.BuilderContext;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.model.Facet;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeReference;
import siani.tara.semantic.Assumption;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;

import static siani.tara.compiler.codegeneration.magritte.NameFormatter.buildFileName;
import static siani.tara.compiler.codegeneration.magritte.TemplateTags.*;

public class BoxModelAdapter implements Adapter<Model> {
	private final String project;
	private final String module;
	private final Language language;
	private final Locale locale;
	private final Map<String, List<SimpleEntry<String, String>>> metrics;


	public BoxModelAdapter(String project, String module, Language language, Locale locale, Map<String, List<SimpleEntry<String, String>>> metrics) {
		this.project = project;
		this.module = module;
		this.language = language;
		this.locale = locale;
		this.metrics = metrics;
	}

	@Override
	public void adapt(Frame frame, Model model, BuilderContext context) {
		frame.addFrame(NAME, buildFileName(model.getFile()));
		if (!Objects.equals(language.languageName(), "Proteo"))
			frame.addFrame(LANGUAGE, language.languageName());
		frame.addFrame("project", project).addFrame("module", module);
		addMetricImports(frame);
		addFacetImports(model.getIncludedNodes(), frame);
		parserAllNodes(frame, model, context);
	}

	private void parserAllNodes(Frame frame, Node nodeContainer, BuilderContext context) {
		for (Node node : nodeContainer.getIncludedNodes()) {
			if (node instanceof NodeReference) continue;
			frame.addFrame("node", context.build(node));
			parserAllNodes(frame, node, context);
		}
		for (Facet facet : nodeContainer.getFacets())
			for (Node node : facet.getIncludedNodes()) {
				frame.addFrame("node", context.build(node));
				parserAllNodes(frame, node, context);
			}
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
			for (Facet facet : node.getFacets())
				if (isIntentionInstance(facet.getType()))
					imports.add(InflectorFactory.getInflector(locale).plural(facet.getType()));
			imports.addAll(searchFacets(node.getIncludedNodes()));
		}
		return imports;
	}

	private boolean isIntentionInstance(String type) {
		Collection<Assumption> assumptions = language.assumptions(type);
		for (Assumption assumption : assumptions) if (assumption instanceof Assumption.IntentionInstance) return true;
		return false;
	}

}
