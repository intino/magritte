package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.codegeneration.model.ModelSerializer;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.Phases;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.lang.Annotation;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
			cleanCaseNodesFromModel(model);
			ModelSerializer generator = new ModelSerializer(unit.getConfiguration());
			generator.serialize(model);
			unit.getErrorCollector().failIfErrors();
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during model generation: " + e.getMessage() + "\n", e);
			throw new CompilationFailedException(Phases.MODEL_GENERATION, unit);
		}
	}

	private void cleanCaseNodesFromModel(Model model) {
		List<Node> toRemove = new ArrayList<>();
		for (Node entry : model.getNodeTable())
			if (entry.is(Annotation.CASE))
				toRemove.add(entry);
		for (Node node : toRemove)
			model.getNodeTable().remove(node);
	}
}
