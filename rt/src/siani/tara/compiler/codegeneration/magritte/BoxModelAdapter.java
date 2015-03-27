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

import static siani.tara.compiler.codegeneration.magritte.NameFormatter.buildFileName;
import static siani.tara.compiler.codegeneration.magritte.TemplateTags.*;

public class BoxModelAdapter implements Adapter<Model> {
	private final String project;
	private final Language language;
	private final Model boxModel;
	private final Locale locale;

	public BoxModelAdapter(String project, Language language, Model boxModel, Locale locale) {
		this.project = project;
		this.language = language;
		this.boxModel = boxModel;
		this.locale = locale;
	}

	@Override
	public void adapt(Frame frame, Model boxModel, BuilderContext context) {
		frame.addFrame(NAME, buildFileName(boxModel.getFile()));
		if (!Objects.equals(language.languageName(), "Proteo"))
			frame.addFrame(LANGUAGE, language.languageName());
		addMetricImports(frame);
		addFacetImports(boxModel.getIncludedNodes(), frame);
		parserAllNodes(frame, boxModel, context);
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
//		for (String metric : boxModel.getMetrics().keySet())
//			frame.addFrame("importMetric", IMPORT + " " + STATIC + " " + project.toLowerCase() + DOT + METRICS + DOT + metric + DOT + STAR + SEMICOLON);
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
				if (isIntentionInstance(facet.type()))
					imports.add(InflectorFactory.getInflector(locale).plural(facet.type()));
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
