package tara.compiler.codegeneration.magritte.box;

import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.AbstractFrame;
import tara.Language;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.model.*;
import tara.compiler.model.impl.Model;
import tara.compiler.model.impl.NodeReference;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class BoxUnitFrameCreator {

	private final String project;
	private final String generatedLanguage;
	private final Language language;
	private final int level;
	private final Model model;
	private final Locale locale;
	private final List<Node> nodes;
	private Map<Node, Long> keymap = new LinkedHashMap<>();
	private long count = 1;

	private BoxUnitFrameCreator(String project, String generatedLanguage, Language language, int level, Model model, Locale locale, List<Node> nodes) {
		this.project = project;
		this.generatedLanguage = generatedLanguage;
		this.language = language;
		this.level = level;
		this.model = model;
		this.locale = locale;
		createKeyMap(this.nodes = nodes);
	}

	public BoxUnitFrameCreator(CompilerConfiguration conf, Model model, List<Node> nodes) {
		this(conf.getProject(), conf.getGeneratedLanguage() != null ? conf.getGeneratedLanguage() : conf.getModule(), conf.getLanguage(), conf.getLevel(), model, conf.getLocale(), nodes);
	}

	private void createKeyMap(List<Node> nodes) {
		nodes.forEach(this::createKeyMap);
	}

	private void createKeyMap(NodeContainer node) {
		if (node instanceof Node) {
			addKey((Node) node);
			((Node) node).facetTargets().forEach(this::createKeyMap);
			((Node) node).facets().forEach(this::createKeyMap);
		}
		node.components().stream().filter(include -> !(include instanceof NodeReference)).forEach(this::createKeyMap);
	}

	private void addKey(Node node) {
		if (keymap.containsKey(node)) return;
		keymap.put(node, count);
		count++;
	}

	public AbstractFrame create() {
		Model boxModel = new Model(nodes.iterator().next().file());
		boxModel.name(model.name());
		boxModel.add(nodes.toArray(new Node[nodes.size()]));
		final FrameBuilder builder = new FrameBuilder();
		builder.register(Model.class, new BoxUnitModelAdapter(project, generatedLanguage, language, locale, model.getMetrics(), level == 0));
		builder.register(Node.class, new BoxUnitNodeAdapter(keymap, level == 0));
		builder.register(FacetTarget.class, new BoxUnitFacetTargetAdapter(keymap, level == 0));
		builder.register(Variable.class, new BoxVariableAdapter(model.getMetrics(), level));
		builder.register(Parameter.class, new BoxParameterAdapter(model.getMetrics()));
		return builder.build(boxModel);
	}
}
