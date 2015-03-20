package siani.tara.compiler.core.operation.model;

import siani.tara.compiler.codegeneration.lang.LanguageSerializer;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.Phases;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.TaraException;
import siani.tara.compiler.rt.TaraRtConstants;
import siani.tara.compiler.model.Annotation;
import siani.tara.compiler.model.Model;
import siani.tara.compiler.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenerateLanguageOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(GenerateLanguageOperation.class.getName());
	private final CompilationUnit unit;

	public GenerateLanguageOperation(CompilationUnit unit) {
		this.unit = unit;
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		try {
			if (unit.getConfiguration().getGeneratedLanguage() == null) return;
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Generating language representation");
			cleanCaseNodesFromModel(model);
			LanguageSerializer generator = new LanguageSerializer(unit.getConfiguration());
			generator.serialize(model);
			unit.getErrorCollector().failIfErrors();
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during language generation: " + e.getMessage() + "\n", e);
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
