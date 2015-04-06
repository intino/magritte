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
	private final String module;
	private final Language language;
	private final Model model;
	private final Locale locale;
	private Map<Node, Long> keymap = new LinkedHashMap<>();
	private long count = 1;

	private BoxFrameCreator(String project, String module, Language language, Model model, Locale locale) {
		this.project = project;
		this.module = module;
		this.language = language;
		this.model = model;
		this.locale = locale;
		createKeyMap(model);
	}

	public BoxFrameCreator(CompilerConfiguration conf, Model model) {
		this(conf.getProject(), conf.getModule(), conf.getLanguage(), model, conf.getLocale());
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
		builder.register(Model.class, new BoxModelAdapter(project, module, language, locale, model.getMetrics()));
		builder.register(Node.class, new BoxNodeAdapter(language, keymap));
		builder.register(Variable.class, new BoxVariableAdapter(model.getMetrics()));
		builder.register(Parameter.class, new BoxParameterAdapter(model.getMetrics()));
		return builder.createFrame(boxModel);
	}
}
