package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.Adapter;
import org.siani.itrules.engine.formatters.PluralFormatter;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.model.Facet;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeReference;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;

import static siani.tara.compiler.codegeneration.magritte.NameFormatter.buildFileName;
import static siani.tara.compiler.codegeneration.magritte.NameFormatter.capitalize;
import static siani.tara.compiler.codegeneration.magritte.TemplateTags.*;

public class BoxModelAdapter implements Adapter<Model> {
	private final String project;
	private final String module;
	private final Language language;
	private final Locale locale;
	private final Map<String, List<SimpleEntry<String, String>>> metrics;
	private final boolean terminal;


	public BoxModelAdapter(String project, String module, Language language, Locale locale, Map<String, List<SimpleEntry<String, String>>> metrics, boolean terminal) {
		this.project = project;
		this.module = module;
		this.language = language;
		this.locale = locale;
		this.metrics = metrics;
		this.terminal = terminal;
	}

	@Override
	public void execute(Frame frame, Model model, FrameContext FrameContext) {
		frame.addFrame(NAME, capitalize(module) + buildFileName(model.getFile()));
		if (!Objects.equals(language.languageName(), "Proteo"))
			frame.addFrame(LANGUAGE, language.languageName());
		frame.addFrame("project", project).addFrame("module", module);
		frame.addFrame("terminal", terminal);
		addMetricImports(frame);
		addFacetImports(model.getIncludedNodes(), frame);
		parserAllNodes(frame, model, FrameContext);
	}


	private void parserAllNodes(Frame frame, Node nodeContainer, FrameContext FrameContext) {
		for (Node node : nodeContainer.getIncludedNodes()) {
			if (node instanceof NodeReference) continue;
			frame.addFrame("node", FrameContext.build(node));
			parserAllNodes(frame, node, FrameContext);
		}
		for (Facet facet : nodeContainer.getFacets())
			for (Node node : facet.getIncludedNodes()) {
				frame.addFrame("node", FrameContext.build(node));
				parserAllNodes(frame, node, FrameContext);
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
				imports.add(new PluralFormatter(locale).getInflector().plural(facet.getType()));
			imports.addAll(searchFacets(node.getIncludedNodes()));
		}
		return imports;
	}

}
