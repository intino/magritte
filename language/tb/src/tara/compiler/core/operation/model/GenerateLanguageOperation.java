package tara.compiler.core.operation.model;

import tara.compiler.codegeneration.lang.LanguageSerializer;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.Phases;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.model.Model;
import tara.compiler.rt.TaraBuildConstants;

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
			System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Generating language representation");
			LanguageSerializer generator = new LanguageSerializer(unit.getConfiguration());
			generator.serialize(model);
			unit.getErrorCollector().failIfErrors();
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during language generation: " + e.getMessage() + "\n", e);
			throw new CompilationFailedException(Phases.LANGUAGE_GENERATION, unit);
		}
	}
}
