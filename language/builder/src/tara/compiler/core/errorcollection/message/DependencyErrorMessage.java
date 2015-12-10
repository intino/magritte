package tara.compiler.core.errorcollection.message;

import tara.compiler.core.SourceUnit;
import tara.compiler.core.errorcollection.DependencyException;

import java.io.PrintWriter;

public class DependencyErrorMessage extends Message {

	protected DependencyException cause;
	protected SourceUnit source;

	public DependencyErrorMessage(DependencyException cause, SourceUnit source) {
		this.cause = cause;
		this.source = source;
	}


	public DependencyException getCause() {
		return this.cause;
	}
	@Override
	public void write(PrintWriter output) {
		String description = "Semantic error: ";
		String message = this.cause.getMessage();
		if (message != null) output.println(description + message);
		else output.println(description + this.cause);
		output.println();
	}
}
