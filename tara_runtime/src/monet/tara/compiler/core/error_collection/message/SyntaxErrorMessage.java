package monet.tara.compiler.core.error_collection.message;

import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.error_collection.SyntaxException;

import java.io.PrintWriter;

public class SyntaxErrorMessage extends Message {

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
	}
}
