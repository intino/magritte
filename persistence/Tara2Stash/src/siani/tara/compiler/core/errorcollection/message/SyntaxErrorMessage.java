package siani.tara.compiler.core.errorcollection.message;

import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.SyntaxException;

import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SyntaxErrorMessage extends Message {

	private static final Logger LOG = Logger.getLogger(SyntaxErrorMessage.class.getName());

	protected SyntaxException cause;
	protected SourceUnit source;

	public SyntaxErrorMessage(SyntaxException cause, SourceUnit source) {
		this.cause = cause;
		this.source = source;
		cause.setSourceLocator(source.getName());
	}

	public SyntaxException getCause() {
		return this.cause;
	}

	public void write(PrintWriter output) {
		String description = "Syntax error: ";
		String message = this.cause.getMessage();
		if (message != null) output.println(description + message);
		else output.println(description + this.cause);
		output.println();
		LOG.log(Level.SEVERE, cause.getMessage(), cause);
	}
}
