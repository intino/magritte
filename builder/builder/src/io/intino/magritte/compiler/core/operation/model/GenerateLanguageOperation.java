package io.intino.magritte.compiler.core.operation.model;

import io.intino.magritte.compiler.codegeneration.lang.LanguageSerializer;
import io.intino.magritte.compiler.core.CompilationUnit;
import io.intino.magritte.compiler.core.Phases;
import io.intino.magritte.compiler.core.errorcollection.CompilationFailedException;
import io.intino.magritte.compiler.core.errorcollection.TaraException;
import io.intino.magritte.compiler.model.Model;

import java.util.Collection;
import java.util.logging.Logger;

import static io.intino.magritte.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;
import static java.util.logging.Level.SEVERE;

public class GenerateLanguageOperation extends ModelCollectionOperation {
	private static final Logger LOG = Logger.getGlobal();
	private final CompilationUnit unit;

	public GenerateLanguageOperation(CompilationUnit unit) {
		this.unit = unit;
	}

	@Override
	public void call(Collection<Model> models) {
		try {
			if (unit.configuration().model().level().isSolution()) return;
			if (unit.configuration().isVerbose())
				unit.configuration().out().println(PRESENTABLE_MESSAGE + "[" + unit.configuration().getModule() + " - " + unit.configuration().model().outDsl() + "] Generating language...");
			new LanguageSerializer(unit.configuration(), models).serialize();
			unit.getErrorCollector().failIfErrors();
		} catch (TaraException e) {
			LOG.log(SEVERE, "Error during language generation: " + e.getMessage() + "\n", e);
			throw new CompilationFailedException(Phases.LANGUAGE_GENERATION, unit, e);
		}
	}
}