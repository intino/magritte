package siani.tara.compiler.core.operation.sourceunit;

import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.errorcollection.ErrorCollector;
import siani.tara.compiler.core.errorcollection.SyntaxException;
import siani.tara.compiler.core.errorcollection.message.Message;
import siani.tara.compiler.rt.TaraRtConstants;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParseOperation extends SourceUnitOperation {
	private static final Logger LOG = Logger.getLogger(ParseOperation.class.getName());

	private ErrorCollector errorCollector;

	public ParseOperation(ErrorCollector errorCollector) {
		this.errorCollector = errorCollector;
	}

	public void call(SourceUnit source) throws CompilationFailedException {
		try {
			System.out.println(TaraRtConstants.PRESENTABLE_MESSAGE + "Parsing " + source.getName());
			source.parse();
			errorCollector.failIfErrors();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Error during Parsing: " + e.getMessage(), e);
			errorCollector.addError(Message.create(e.getMessage(), source));
		} catch (SyntaxException e) {
			LOG.log(Level.SEVERE, "Syntax error during Parsing", e);
			errorCollector.addError(Message.create(e, source));
		}
	}
}
