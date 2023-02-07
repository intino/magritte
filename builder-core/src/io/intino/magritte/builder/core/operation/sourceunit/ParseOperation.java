package io.intino.magritte.builder.core.operation.sourceunit;

import io.intino.magritte.builder.core.CompilationUnit;
import io.intino.magritte.builder.core.SourceUnit;
import io.intino.magritte.builder.core.errorcollection.ErrorCollector;
import io.intino.magritte.builder.core.errorcollection.SyntaxException;
import io.intino.magritte.builder.core.errorcollection.TaraException;
import io.intino.magritte.builder.core.errorcollection.message.Message;

import java.util.logging.Level;
import java.util.logging.Logger;

import static io.intino.magritte.builder.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;

public class ParseOperation extends SourceUnitOperation {
	private static final Logger LOG = Logger.getGlobal();
	private final CompilationUnit unit;
	private final ErrorCollector errorCollector;

	public ParseOperation(CompilationUnit unit) {
		this.unit = unit;
		this.errorCollector = unit.getErrorCollector();
	}

	@Override
	public void call(SourceUnit source) {
		try {
			if (unit.configuration().isVerbose()) unit.configuration().out().println(PRESENTABLE_MESSAGE + "Parsing " + source.getName());
			source.parse();
			errorCollector.failIfErrors();
		} catch (TaraException e) {
			if (e.getCause() instanceof SyntaxException) {
				LOG.log(Level.SEVERE, "Syntax error during Parsing: " + e.getMessage());
				errorCollector.addError(Message.create((SyntaxException) e.getCause(), source));
			} else if (e instanceof SyntaxException) {
				LOG.log(Level.SEVERE, "Syntax error during Parsing: " + e.getMessage());
				errorCollector.addError(Message.create((SyntaxException) e, source));
			} else {
				LOG.log(Level.SEVERE, "Error during Parsing: " + e.getMessage());
				errorCollector.addError(Message.create(e.getMessage(), source));
			}
		}
	}
}