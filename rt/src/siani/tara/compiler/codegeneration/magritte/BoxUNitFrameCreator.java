package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.AbstractFrame;
import siani.tara.Language;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeReference;

import java.util.*;


public class BoxUnitFrameCreator {

	private final String project;
	private final String generatedLanguage;
	private final Language language;
	private final Model model;
	private final Locale locale;
	private final List<Node> nodes;
	private Map<Node, Long> keymap = new LinkedHashMap<>();
	private long count = 1;

	private BoxUnitFrameCreator(String project, String generatedLanguage, Language language, Model model, Locale locale, List<Node> nodes) {
		this.project = project;
		this.generatedLanguage = generatedLanguage;
		this.language = language;
		this.model = model;
		this.locale = locale;
		createKeyMap(this.nodes = nodes);
	}

	private void createKeyMap(List<Node> nodes) {
		for (Node node : nodes)
			createKeyMap(node);
	}

	public BoxUnitFrameCreator(CompilerConfiguration conf, Model model, List<Node> nodes) {
		this(conf.getProject(), conf.getGeneratedLanguage(), conf.getLanguage(), model, conf.getLocale(), nodes);
	}

	private void createKeyMap(NodeContainer node) {
		if (node instanceof Node) addKey((Node) node);
		for (Node include : node.getIncludedNodes()) {
			if (include instanceof NodeReference) continue;
			addKey(include);
			createKeyMap(include);
			for (FacetTarget facetTarget : include.getFacetTargets())
				createKeyMap(facetTarget);
			for (Facet facet : include.getFacets())
				createKeyMap(facet);
		}
	}

	private void addKey(Node node) {
		if (keymap.containsKey(node)) return;
		keymap.put(node, count);
		count++;
	}

	public AbstractFrame create() {
		Model boxModel = new Model(((Element) nodes.iterator().next()).getFile());
		boxModel.setName(model.getName());
		boxModel.addIncludedNodes(nodes.toArray(new Node[nodes.size()]));
		final FrameBuilder builder = new FrameBuilder();
		builder.register(Model.class, new BoxModelAdapter(project, generatedLanguage, language, locale, model.getMetrics(), model.isTerminal()));
		builder.register(Node.class, new BoxNodeAdapter(keymap, model.isTerminal()));
		builder.register(Variable.class, new BoxVariableAdapter(model.getMetrics()));
		builder.register(Parameter.class, new BoxParameterAdapter(model.getMetrics()));
		return builder.build(boxModel);
	}
}
