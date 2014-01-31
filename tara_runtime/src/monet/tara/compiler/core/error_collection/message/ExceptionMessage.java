package monet.tara.compiler.core.error_collection.message;

import monet.tara.compiler.core.SourceUnit;

import java.io.PrintWriter;

public class ExceptionMessage extends Message {
	protected boolean verbose = true;
	private Exception cause = null;
	SourceUnit owner = null;

	public ExceptionMessage(Exception cause, boolean v, SourceUnit owner) {
		this.verbose = v;
		this.cause = cause;
		this.owner = owner;
	}

	public Exception getCause() {
		return this.cause;
	}


	public void write(PrintWriter output) {
		String description = "General error during " + "this.owner.getPhaseDescription()" + ": ";

		String message = this.cause.getMessage();
		if (message != null) {
			output.println(description + message);
		} else {
			output.println(description + this.cause);
		}
		output.println();

		this.cause.printStackTrace(output);
	}
}
