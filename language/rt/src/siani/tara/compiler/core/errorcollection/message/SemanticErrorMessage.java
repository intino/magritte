package siani.tara.compiler.core.errorcollection.message;

import siani.tara.compiler.core.SourceUnit;
import siani.tara.compiler.core.errorcollection.SemanticException;

import java.io.PrintWriter;

public class SemanticErrorMessage extends Message {

	private final SemanticException cause;
	private final SourceUnit owner;

	public SemanticErrorMessage(SemanticException error, SourceUnit owner) {
		this.cause = error;
		this.owner = owner;
	}

	public SemanticException getCause() {
		return this.cause;
	}

	@Override
	public void write(PrintWriter paramPrintWriter) {

	}
}
