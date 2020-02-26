package io.intino.magritte.compiler.core.errorcollection.message;

import io.intino.magritte.compiler.core.SourceUnit;

import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionMessage extends Message {
	private static final Logger LOG = Logger.getGlobal();

	private boolean verbose;
	private SourceUnit owner;
	private Exception cause;

	public ExceptionMessage(Exception cause, boolean v, SourceUnit owner) {
		this.verbose = v;
		this.cause = cause;
		this.owner = owner;
	}

	public Exception getCause() {
		return this.cause;
	}

	@Override
	public void write(PrintWriter output) {
		String description = "General error during " + "this.owner.getPhaseDescription()" + ": ";
		String message = this.cause.getMessage();
		if (message != null) output.println(description + message);
		else output.println(description + this.cause);
		output.println();
		LOG.log(Level.SEVERE, cause.getMessage(), cause);
	}
}
