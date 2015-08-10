package tara.compiler.core.operation.module;

import tara.compiler.core.CompilationUnit;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.MergeException;
import tara.compiler.model.Model;
import tara.compiler.parser.ASTMerger;
import tara.compiler.constants.TaraBuildConstants;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MergeToModelOperation extends ModuleUnitOperation {
	private static final Logger LOG = Logger.getLogger(MergeToModelOperation.class.getName());

	private CompilationUnit compilationUnit;

	public MergeToModelOperation(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}

	@Override
	public void call(Collection<SourceUnit> sources) {
		try {
			System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Tarac: Merging to global model");
			Model model = new ASTMerger(sources, compilationUnit.getConfiguration()).doMerge();
			compilationUnit.setModel(model);
		} catch (MergeException e) {
			LOG.log(Level.SEVERE, "Error merging sources representations: " + e.getMessage(), e);
		}
	}
}
