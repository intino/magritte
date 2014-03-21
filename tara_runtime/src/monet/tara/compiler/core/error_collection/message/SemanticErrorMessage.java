package monet.tara.compiler.core.error_collection.message;

import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.error_collection.semantic.SemanticError;

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
	public void write(PrintWriter paramPrintWriter) {
	}
}
