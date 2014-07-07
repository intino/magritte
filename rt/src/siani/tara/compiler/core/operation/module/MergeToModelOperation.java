package siani.tara.compiler.core.operation.module;

import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.MergeException;
import siani.tara.compiler.parser.ASTMerger;
import siani.tara.compiler.rt.TaraRtConstants;

import java.util.Collection;
import java.util.logging.Logger;

public class MergeToModelOperation extends ModuleUnitOperation {
	private static final Logger LOG = Logger.getLogger(MergeToModelOperation.class.getName());

	private CompilationUnit compilationUnit;

	public MergeToModelOperation(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}

	public void call(Collection<SourceUnit> sources) throws CompilationFailedException {
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Tarac: Merging to global model");
			ASTMerger merger = new ASTMerger(sources);
			compilationUnit.setModel(merger.doMerge());
		} catch (MergeException e) {
			LOG.severe("Error merging sources representations: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
