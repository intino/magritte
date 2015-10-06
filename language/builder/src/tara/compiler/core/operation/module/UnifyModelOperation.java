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

public class UnifyModelOperation extends ModuleUnitOperation {
	private static final Logger LOG = Logger.getLogger(UnifyModelOperation.class.getName());

	private CompilationUnit unit;

	public UnifyModelOperation(CompilationUnit unit) {
		this.unit = unit;
	}

	@Override
	public void call(Collection<SourceUnit> sources) {
		try {
			if (unit.getConfiguration().isVerbose())System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Tarac: Merging to global model");
			Model model = new ASTMerger(sources, unit.getConfiguration()).doMerge();
			unit.setModel(model);
		} catch (MergeException e) {
			LOG.log(Level.SEVERE, "Error merging sources representations: " + e.getMessage(), e);
		}
	}
}
