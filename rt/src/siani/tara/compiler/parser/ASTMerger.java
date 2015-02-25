package siani.tara.compiler.parser;

import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.MergeException;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.Collection;

public class ASTMerger {
	private final Collection<SourceUnit> sources;
	private final CompilerConfiguration conf;

	public ASTMerger(Collection<SourceUnit> sources, CompilerConfiguration conf) {
		this.sources = sources;
		this.conf = conf;
	}

	public Model doMerge() throws MergeException {
		Model model = new Model(conf.getModelName() != null ? conf.getModelName() : conf.getProject() + "." + conf.getModule());
		model.setTerminal(conf.isTerminal());
		model.setParentModelName(sources.iterator().next().getModel().getParentModelName());
		model.setTerminal(sources.iterator().next().getModel().isTerminal());
		for (SourceUnit unit : sources) {
			model.addAll(unit.getModel().getTreeModel());
			model.putAllIdentifiers(unit.getModel().getIdentifiers());
			model.register(unit.getModel().getNodeTable().toArray(new Node[unit.getModel().getNodeTable().size()]));
		}
		for (Node node : model.getNodeTable())
			node.setModelOwner(model.getName());
		model.addMetrics(MetricsLoader.loadMetrics(conf));
		System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Tarac: loading metrics...");
		addCodeGenerationLanguage(model, conf);
		return model;
	}

	private void addCodeGenerationLanguage(Model model, CompilerConfiguration configuration) {
		model.setLanguage(configuration.getLanguageForCodeGeneration());
	}
}
