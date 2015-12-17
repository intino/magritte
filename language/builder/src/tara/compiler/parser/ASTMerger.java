package tara.compiler.parser;

import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.MergeException;
import tara.compiler.model.Model;
import tara.compiler.model.NodeImpl;
import tara.lang.model.Node;

import java.io.File;
import java.util.*;

public class ASTMerger {
	private final Collection<SourceUnit> sources;
	private final CompilerConfiguration conf;

	public ASTMerger(Collection<SourceUnit> sources, CompilerConfiguration conf) {
		this.sources = sources;
		this.conf = conf;
	}

	public Model doMerge() throws MergeException {
		Model model = new Model(getName(), conf.getLanguage());
		model.setLevel(conf.level());
		for (SourceUnit unit : sources) {
			List<Node> components = unit.getModel().components();
			components.stream().forEach(c -> model.add(c, unit.getModel().ruleOf(c)));
			if (!components.isEmpty()) model.language(components.get(0).language());
		}
		for (Node node : model.components()) node.container(model);
		if (conf.isVerbose()) System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Tarac: loading metrics...");
		mergeExtensionNodes(model);
		return model;
	}

	private void mergeExtensionNodes(Model model) throws MergeException {
		Map<String, List<Node>> toMerge = extensionNodes(model);
		for (List<Node> nodes : toMerge.values()) merge(nodes);
		for (List<Node> nodes : toMerge.values()) for (int i = 1; i < nodes.size(); i++) model.remove(nodes.get(i));
	}

	private void merge(List<Node> nodes) throws MergeException {
		if (nodes.size() < 2) return;
		if (!correctParent(nodes)) throw new MergeException("Error merging extension elements. Parents are not homogeneous.");
		Node target = nodes.get(0);
		for (Node node : nodes.subList(1, nodes.size())) merge((NodeImpl) node, (NodeImpl) target);
	}

	private boolean correctParent(List<Node> nodes) {
		String parent = nodes.get(0).parentName() == null ? "" : nodes.get(0).parentName();
		for (Node node : nodes) if (!parent.equals(node.parentName())) return false;
		return true;
	}

	private void merge(NodeImpl node, NodeImpl target) {
		target.absorb(node);
	}

	private Map<String, List<Node>> extensionNodes(Model model) {
		Map<String, List<Node>> toMerge = new HashMap<>();
		for (Node node : model.components()) {
			if (!node.isExtension()) continue;
			if (!toMerge.containsKey(node.name())) toMerge.put(node.name(), new ArrayList<>());
			toMerge.get(node.name()).add(node);
		}
		return toMerge;
	}

	private String getName() {
		return conf.getProject() != null ? conf.getProject() + "." + conf.generatedLanguage() :
			getPresentableName();
	}

	private String getPresentableName() {
		final String name = new File(sources.iterator().next().getName()).getName();
		return name.substring(0, name.lastIndexOf("."));
	}

}
