package io.intino.magritte.builder.compiler.operations;

import io.intino.magritte.builder.compiler.codegeneration.lang.LanguageSerializer;
import io.intino.magritte.builder.core.CompilationUnit;
import io.intino.magritte.builder.core.Phases;
import io.intino.magritte.builder.core.errorcollection.CompilationFailedException;
import io.intino.magritte.builder.core.errorcollection.TaraException;
import io.intino.magritte.builder.core.operation.model.ModelCollectionOperation;
import io.intino.magritte.builder.model.Model;

import java.util.Collection;
import java.util.logging.Logger;

import static io.intino.magritte.builder.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;
import static java.util.logging.Level.SEVERE;

public class GenerateLanguageOperation extends ModelCollectionOperation {
	private static final Logger LOG = Logger.getGlobal();

	public GenerateLanguageOperation(CompilationUnit unit) {
		super(unit);
	}

	@Override
	public void call(Collection<Model> models) {
		try {
			if (compilationUnit.configuration().model().level().isSolution()) return;
			if (compilationUnit.configuration().isVerbose())
				compilationUnit.configuration().out().println(PRESENTABLE_MESSAGE + "[" + compilationUnit.configuration().getModule() + " - " + compilationUnit.configuration().model().outDsl() + "] Generating language...");
			new LanguageSerializer(compilationUnit.configuration(), models).serialize();
			compilationUnit.getErrorCollector().failIfErrors();
		} catch (TaraException e) {
			LOG.log(SEVERE, "Error during language generation: " + e.getMessage() + "\n", e);
			throw new CompilationFailedException(Phases.CODE_GENERATION, compilationUnit, e);
		}
	}
}