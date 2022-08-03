package io.intino.magritte.builder.compiler.core.errorcollection.message;

import io.intino.magritte.builder.compiler.core.SourceUnit;
import io.intino.magritte.builder.compiler.core.errorcollection.SemanticException;

import java.io.PrintWriter;

public class SemanticErrorMessage extends Message {

	private final SemanticException cause;

	public SemanticErrorMessage(SemanticException error, SourceUnit owner) {
		this.cause = error;
	}

	public SemanticException getCause() {
		return this.cause;
	}

	@Override
	public void write(PrintWriter paramPrintWriter) {

	}
}
