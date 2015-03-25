package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.framebuilder.FrameBuilder;
import org.siani.itrules.model.Frame;
import siani.tara.Language;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeReference;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;


public class BoxFrameCreator {

	private final String project;
	private final Language language;
	private final Model model;
	private final Locale locale;
	private final boolean terminal;
	private Map<Node, Long> keymap = new LinkedHashMap<>();
	private long count = 1;

	private BoxFrameCreator(String project, Language language, Model model, Locale locale, boolean terminal) {
		this.project = project;
		this.language = language;
		this.model = model;
		this.locale = locale;
		this.terminal = terminal;
		createKeyMap(model);
	}

	public BoxFrameCreator(CompilerConfiguration conf, Model model) {
		this(conf.getProject(), conf.getLanguage(), model, conf.getLocale(), conf.isTerminal());
	}

	private void createKeyMap(NodeContainer node) {
		for (Node include : node.getIncludedNodes()) {
			keymap.put(include, count);
			count++;
			if (include instanceof NodeReference && ((NodeReference) include).isHas()) continue;
			createKeyMap(include);
			for (FacetTarget facetTarget : include.getFacetTargets())
				createKeyMap(facetTarget);
			for (Facet facet : include.getFacets())
				createKeyMap(facet);
		}
	}

	public Frame create(Collection<Node> nodes) {
		Model boxModel = new Model(((Element) nodes.iterator().next()).getFile());
		boxModel.setName(model.getName());
		boxModel.addIncludedNodes(nodes.toArray(new Node[nodes.size()]));
		final FrameBuilder builder = new FrameBuilder();
		builder.register(Model.class, new BoxModelAdapter(project, language, boxModel, locale));
		builder.register(Node.class, new BoxNodeAdapter(language, keymap));
		builder.register(Variable.class, new BoxVariableAdapter());
		builder.register(Parameter.class, new BoxParameterAdapter());
		return builder.createFrame(boxModel);
	}
}
