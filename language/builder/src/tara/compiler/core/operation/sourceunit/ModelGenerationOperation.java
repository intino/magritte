package tara.compiler.core.operation.sourceunit;

import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.ErrorCollector;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.errorcollection.message.Message;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelGenerationOperation extends SourceUnitOperation {
	private static final Logger LOG = Logger.getLogger(ModelGenerationOperation.class.getName());
	private final ErrorCollector errorCollector;
	private final CompilationUnit unit;

	public ModelGenerationOperation(CompilationUnit unit) {
		this.unit = unit;
		this.errorCollector = unit.getErrorCollector();
	}

	@Override
	public void call(SourceUnit source) {
		try {
			if (unit.getConfiguration().isVerbose())
				System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Converting " + source.getName());
			source.importData();
			errorCollector.failIfErrors();
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during conversion: " + e.getMessage());
			errorCollector.addError(Message.create(e.getMessage(), source));
		}
	}
}
