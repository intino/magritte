package tara.compiler.core.operation.sourceunit;

import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.ErrorCollector;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.errorcollection.message.Message;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BuildModelOperation extends SourceUnitOperation {
	private static final Logger LOG = Logger.getLogger(BuildModelOperation.class.getName());
	private final ErrorCollector errorCollector;

	public BuildModelOperation(ErrorCollector errorCollector) {
		this.errorCollector = errorCollector;
	}

	public void call(SourceUnit source) throws CompilationFailedException {
		try {
			source.importData();
			errorCollector.failIfErrors();
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during conversion: " + e.getMessage(), e);
			errorCollector.addError(Message.create(e.getMessage(), source));
		}
	}
}
