package tara.compiler.core.operation.module;

import tara.compiler.core.CompilationUnit;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.MergeException;
import tara.compiler.model.impl.Model;
import tara.compiler.parser.ASTMerger;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MergeToModelOperation extends ModuleUnitOperation {
	private static final Logger LOG = Logger.getLogger(MergeToModelOperation.class.getName());

	private CompilationUnit compilationUnit;

	public MergeToModelOperation(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}

	public void call(Collection<SourceUnit> sources) throws CompilationFailedException {
		try {
			Model model = new ASTMerger(sources).doMerge();
			compilationUnit.setModel(model);
		} catch (MergeException e) {
			LOG.log(Level.SEVERE, "Error merging sources representations: " + e.getMessage(), e);
		}
	}
}