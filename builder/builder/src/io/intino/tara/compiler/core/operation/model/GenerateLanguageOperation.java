package io.intino.tara.compiler.core.operation.model;

import io.intino.tara.compiler.codegeneration.lang.LanguageSerializer;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.core.Phases;
import io.intino.tara.compiler.core.errorcollection.CompilationFailedException;
import io.intino.tara.compiler.core.errorcollection.TaraException;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.core.CompilationUnit;
import io.intino.tara.compiler.shared.TaraBuildConstants;

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
			if (unit.getConfiguration().level().equals(CompilerConfiguration.Level.System)) return;
			if (unit.getConfiguration().isVerbose())
				System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "[" + unit.getConfiguration().getModule() + " - " + unit.getConfiguration().outDSL() + "] Generating language...");
			LanguageSerializer generator = new LanguageSerializer(unit.getConfiguration());
			generator.write(model);
			unit.getErrorCollector().failIfErrors();
		} catch (TaraException e) {
			LOG.log(java.util.logging.Level.SEVERE, "Error during language generation: " + e.getMessage() + "\n", e);
			throw new CompilationFailedException(Phases.LANGUAGE_GENERATION, unit);
		}
	}
}