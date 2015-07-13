package tara.compiler.parser;

import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.MergeException;
import tara.compiler.model.Node;
import tara.compiler.model.impl.Model;
import tara.compiler.rt.TaraRtConstants;

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
			Collection<Node> includedNodes = unit.getModel().getIncludedNodes();
			model.addIncludedNodes(includedNodes.toArray(new Node[includedNodes.size()]));
		}
		for (Node node : model.getIncludedNodes()) node.setContainer(model);
		model.addMetrics(MetricsLoader.loadMetrics(conf));
		System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Tarac: loading metrics...");
		return model;
	}
}
