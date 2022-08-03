package io.intino.magritte.builder.compiler.core.operation.sourceunit;

import io.intino.magritte.builder.compiler.core.CompilationUnit;
import io.intino.magritte.builder.compiler.core.SourceUnit;
import io.intino.magritte.builder.compiler.core.errorcollection.ErrorCollector;
import io.intino.magritte.builder.compiler.core.errorcollection.SyntaxException;
import io.intino.magritte.builder.compiler.core.errorcollection.TaraException;
import io.intino.magritte.builder.compiler.core.errorcollection.message.Message;

import java.util.logging.Level;
import java.util.logging.Logger;

import static io.intino.magritte.builder.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;

public class ModelGenerationOperation extends SourceUnitOperation {
	private static final Logger LOG = Logger.getGlobal();
	private final ErrorCollector errorCollector;
	private final CompilationUnit unit;

	public ModelGenerationOperation(CompilationUnit unit) {
		this.unit = unit;
		this.errorCollector = unit.getErrorCollector();
	}

	@Override
	public void call(SourceUnit source) {
		try {
			if (unit.configuration().isVerbose())
				unit.configuration().out().println(PRESENTABLE_MESSAGE + "Converting " + source.getName());
			source.importData();
			errorCollector.failIfErrors();
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during conversion: " + e.getMessage());
			if (e instanceof SyntaxException) errorCollector.addError(Message.create((SyntaxException) e, source));
			else errorCollector.addError(Message.create(e.getMessage(), source));
		}
	}
}
