package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.framebuilder.FrameBuilder;
import org.siani.itrules.model.Frame;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.model.*;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.model.impl.NodeReference;

import java.util.*;


public class BoxFrameCreator extends FrameCreator {

	private final Model model;
	private final Locale locale;
	private final boolean terminal;
	private Map<String, List<Long>> keymap = new LinkedHashMap<>();
	private long count = 1;
	private final String project;
	private String languageName;

	private BoxFrameCreator(String project, String languageName, Model model, Locale locale, boolean terminal) {
		super(project, model);
		this.project = project;
		this.languageName = languageName;
		this.model = model;
		this.locale = locale;
		this.terminal = terminal;
		createKeyMap(model);
	}

	public BoxFrameCreator(CompilerConfiguration conf, Model model) {
		this(conf.getProject(), conf.getGeneratedLanguage(), model, conf.getLocale(), conf.isTerminal());
	}

	private void createKeyMap(NodeContainer node) {
		for (Node include : node.getIncludedNodes()) {
			if (include instanceof NodeReference && ((NodeReference) include).isHas()) continue;
			if (!keymap.containsKey(include.getQualifiedName()))
				keymap.put(include.getQualifiedName(), new ArrayList<Long>());
			keymap.get(include.getQualifiedName()).add(count);
			count++;
			createKeyMap(include);
			for (FacetTarget facetTarget : include.getFacetTargets())
				createKeyMap(facetTarget);
		}
	}

	public Frame create(Collection<Node> nodes, Collection<String> dependencies) {
		Model boxModel = new Model(((Element) nodes.iterator().next()).getFile());
		boxModel.setName(model.getName());
		boxModel.addIncludedNodes(nodes.toArray(new Node[nodes.size()]));
		final FrameBuilder builder = new FrameBuilder();
		builder.register(Model.class, new BoxModelAdapter(project, languageName, boxModel, dependencies, locale));
		builder.register(Node.class, new BoxNodeAdapter(project, boxModel, terminal, keymap));
		builder.register(Variable.class, new BoxVariableAdapter(project, boxModel, terminal));
		return builder.createFrame(boxModel);
	}
}
