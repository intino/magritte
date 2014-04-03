package monet.tara.compiler.core.errorcollection.message;

import monet.tara.compiler.core.SourceUnit;

import java.io.PrintWriter;

public class ExceptionMessage extends Message {
	protected boolean verbose = true;
	SourceUnit owner = null;
	private Exception cause = null;

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
		if (message != null) output.println(description + message);
		else output.println(description + this.cause);
		output.println();
		this.cause.printStackTrace(output);
	}
}
