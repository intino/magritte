package siani.tara.compiler.parser;

import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.MergeException;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.Collection;
import java.util.Locale;

public class ASTMerger {
	private final Collection<SourceUnit> sources;

	public ASTMerger(Collection<SourceUnit> sources) {
		this.sources = sources;
	}

	public Model doMerge() throws MergeException {
		CompilerConfiguration configuration = sources.iterator().next().getConfiguration();
		Model model = new Model(configuration.getProject() + "." + configuration.getModule());
		model.setParentModelName(sources.iterator().next().getModel().getParentModelName());
		model.setTerminal(sources.iterator().next().getModel().isTerminal());
		for (SourceUnit unit : sources) {
			model.addAll(unit.getModel().getTreeModel());
			model.putAllIdentifiers(unit.getModel().getIdentifiers());
			model.putAllInNodeTable(unit.getModel().getNodeTable());
		}
		for (Node node : model.getNodeTable().values())
			node.setModelOwner(model.getModelName());
		model.addMetrics(MetricsLoader.loadMetrics(configuration));
		System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Tarac: loading metrics...");
		addCodeGenerationLanguage(model, configuration);
		return model;
	}

	private void addCodeGenerationLanguage(Model model, CompilerConfiguration configuration) {
		model.setLanguage(configuration.getLanguageForCodeGeneration().equalsIgnoreCase("Spanish") ? new Locale("Spanish", "Spain", "es_ES") : Locale.ENGLISH);
	}
}
