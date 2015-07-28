package tara.compiler.core.operation.sourceunit;

import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.ErrorCollector;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.errorcollection.message.Message;
import tara.compiler.rt.TaraRtConstants;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportDataOperation extends SourceUnitOperation {
	private static final Logger LOG = Logger.getLogger(ImportDataOperation.class.getName());
	private final ErrorCollector errorCollector;

	public ImportDataOperation(ErrorCollector errorCollector) {
		this.errorCollector = errorCollector;
	}

	public void call(SourceUnit source) throws CompilationFailedException {
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Converting " + source.getName());
			source.importData();
			errorCollector.failIfErrors();
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during conversion: " + e.getMessage());
			errorCollector.addError(Message.create(e.getMessage(), source));
		}
	}
}