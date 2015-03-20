package siani.tara.compiler.parser;

import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.MergeException;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.impl.Model;
import siani.tara.compiler.rt.TaraRtConstants;

import java.util.Collection;

public class ASTMerger {
	private final Collection<SourceUnit> sources;
	private final CompilerConfiguration conf;

	public ASTMerger(Collection<SourceUnit> sources, CompilerConfiguration conf) {
		this.sources = sources;
		this.conf = conf;
	}

	public Model doMerge() throws MergeException {
		Model model = new Model(conf.getProject() + "." + conf.getModule());
		model.setTerminal(conf.isTerminal());
		for (SourceUnit unit : sources) {
			Collection<Node> includedNodes = unit.getModel().getIncludedNodes();
			model.addIncludedNodes(includedNodes.toArray(new Node[includedNodes.size()]));
		}
		for (Node node : model.getIncludedNodes()) node.setContainer(model);
//		model.addMetrics(MetricsLoader.loadMetrics(conf));
		System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Tarac: loading metrics...");
		return model;
	}
}
