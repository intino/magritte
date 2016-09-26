package tara.compiler.core.operation.model;

import tara.compiler.codegeneration.lang.LanguageSerializer;
import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.Phases;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.model.Model;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GenerateLanguageOperation extends ModelOperation {
	private static final Logger LOG = Logger.getGlobal();
	private final CompilationUnit unit;

	public GenerateLanguageOperation(CompilationUnit unit) {
		this.unit = unit;
	}

	@Override
	public void call(Model model) {
		try {
			if (unit.getConfiguration().moduleType().equals(CompilerConfiguration.ModuleType.System)) return;
			if (unit.getConfiguration().isVerbose())
				System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "[" + unit.getConfiguration().getModule() + " - " + unit.getConfiguration().outDSL() + "] Generating language...");
			LanguageSerializer generator = new LanguageSerializer(unit.getConfiguration());
			generator.serialize(model);
			unit.getErrorCollector().failIfErrors();
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during language generation: " + e.getMessage() + "\n", e);
			throw new CompilationFailedException(Phases.LANGUAGE_GENERATION, unit);
		}
	}
}