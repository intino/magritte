package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.codegeneration.model.ModelSerializer;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.Phases;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.lang.Model;

import java.util.logging.Logger;

public class SaveModelOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(SaveModelOperation.class.getName());
	private final CompilationUnit unit;

	public SaveModelOperation(CompilationUnit unit) {
		this.unit = unit;
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Generating model representation");
			ModelSerializer generator = new ModelSerializer(unit.getConfiguration());
			generator.serialize(model);
			unit.getErrorCollector().failIfErrors();
		} catch (TaraException e) {
			LOG.severe("Error during model generation: " + e.getMessage() + "\n");
			throw new CompilationFailedException(Phases.MODEL_GENERATION, unit);
		}
	}
}
