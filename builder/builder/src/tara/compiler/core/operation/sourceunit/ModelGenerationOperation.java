package tara.compiler.core.operation.sourceunit;

import tara.compiler.core.CompilationUnit;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.ErrorCollector;
import tara.compiler.core.errorcollection.SyntaxException;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.errorcollection.message.Message;

import java.util.logging.Level;
import java.util.logging.Logger;

import static tara.compiler.shared.TaraBuildConstants.PRESENTABLE_MESSAGE;

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
			if (unit.getConfiguration().isVerbose())
				System.out.println(PRESENTABLE_MESSAGE + "Converting " + source.getName());
			source.importData();
			errorCollector.failIfErrors();
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during conversion: " + e.getMessage());
			if (e instanceof SyntaxException) errorCollector.addError(Message.create((SyntaxException) e, source));
			else errorCollector.addError(Message.create(e.getMessage(), source));
		}
	}
}
