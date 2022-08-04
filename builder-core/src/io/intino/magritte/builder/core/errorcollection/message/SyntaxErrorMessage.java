package io.intino.magritte.builder.core.errorcollection.message;

import io.intino.magritte.builder.core.SourceUnit;
import io.intino.magritte.builder.core.errorcollection.SyntaxException;

import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SyntaxErrorMessage extends Message {

	private static final Logger LOG = Logger.getGlobal();

	private SyntaxException cause;
	protected SourceUnit source;

	SyntaxErrorMessage(SyntaxException cause, SourceUnit source) {
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
