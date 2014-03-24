package monet.tara.compiler.core.errorcollection.message;

import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.errorcollection.SyntaxException;

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
		String description = "Syntax error: ";
		String message = this.cause.getMessage();
		if (message != null) output.println(description + message);
		else output.println(description + this.cause);
		output.println();
		this.cause.printStackTrace(output);
	}
}
