package siani.tara.compiler.parser;

import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.MergeException;
import siani.tara.lang.TreeWrapper;

import java.util.Collection;

public class ASTMerger {
	private final Collection<SourceUnit> sources;

	public ASTMerger(Collection<SourceUnit> sources) {
		this.sources = sources;
	}

	public TreeWrapper doMerge() throws MergeException {
		TreeWrapper treeWrapper = new TreeWrapper();
		for (SourceUnit unit : sources) {
			treeWrapper.addAll(unit.getNodeTree().getTree());
			treeWrapper.putAllIdentifiers(unit.getNodeTree().getIdentifiers());
			treeWrapper.putAllInNodeNameTable(unit.getNodeTree().getNodeTable());
		}
		return treeWrapper;
	}
}
