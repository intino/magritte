package siani.tara.compiler.parser;

import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.MergeException;
import siani.tara.lang.Model;

import java.util.Collection;

public class ASTMerger {
	private final Collection<SourceUnit> sources;

	public ASTMerger(Collection<SourceUnit> sources) {
		this.sources = sources;
	}

	public Model doMerge() throws MergeException {
		CompilerConfiguration conf = sources.iterator().next().getConfiguration();
		Model model = new Model(conf.getProject() + "." + conf.getModule());
		for (SourceUnit unit : sources) {
			model.addAll(unit.getNodeTree().getTree());
			model.putAllIdentifiers(unit.getNodeTree().getIdentifiers());
			model.putAllInNodeTable(unit.getNodeTree().getNodeTable());
		}
		return model;
	}
}
