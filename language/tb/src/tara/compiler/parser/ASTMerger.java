package tara.compiler.parser;

import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.MergeException;
import tara.compiler.model.Model;
import tara.compiler.rt.TaraBuildConstants;
import tara.language.model.Node;

import java.util.Collection;

public class ASTMerger {
	private final Collection<SourceUnit> sources;
	private final CompilerConfiguration conf;

	public ASTMerger(Collection<SourceUnit> sources, CompilerConfiguration conf) {
		this.sources = sources;
		this.conf = conf;
	}

	public Model doMerge() throws MergeException {
		Model model = new Model(conf.getProject() + "." + conf.getGeneratedLanguage());
		model.setLevel(conf.getLevel());
		for (SourceUnit unit : sources) {
			Collection<Node> includedNodes = unit.getModel().components();
			model.add(includedNodes.toArray(new Node[includedNodes.size()]));
		}
		for (Node node : model.components()) node.container(model);
		model.addMetrics(MetricsLoader.loadMetrics(conf));
		System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Tarac: loading metrics...");
		return model;
	}
}
