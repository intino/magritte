package tara.compiler.core.operation.sourceunit;

import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.ErrorCollector;
import tara.compiler.core.errorcollection.SyntaxException;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.errorcollection.message.Message;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ParseOperation extends SourceUnitOperation {
	private static final Logger LOG = Logger.getLogger(ParseOperation.class.getName());
	private final CompilationUnit unit;
	private final ErrorCollector errorCollector;

	public ParseOperation(CompilationUnit unit) {
		this.unit = unit;
		this.errorCollector = unit.getErrorCollector();
	}

	@Override
	public void call(SourceUnit source) {
		try {
			if (unit.getConfiguration().isVerbose())
				System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Parsing " + source.getName());
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