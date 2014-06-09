package siani.tara.compiler.core.errorcollection.message;

import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.semantic.SemanticError;

import java.io.PrintWriter;

public class SemanticErrorMessage extends Message {

	protected SemanticError cause;
	protected SourceUnit source;

	public SemanticErrorMessage(SemanticError cause, SourceUnit source) {
		this.cause = cause;
		this.source = source;
	}

	public SemanticError getCause() {
		return this.cause;
	}

	@Override
	public void write(PrintWriter output) {
		String description = "Dependency error: ";
		String message = this.cause.getMessage();
		if (message != null) output.println(description + message);
		else output.println(description + this.cause);
		output.println();
	}
}