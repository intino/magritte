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
		String name = this.source.getName();
		int line = getCause().getStartLine();
		int column = getCause().getStartColumn();
//		String sample = this.source.getSample(line, column);
//
//		output.print(name + ": " + line + ": " + getCause().getMessage());
//		if (sample != null) {
//			output.println();
//			output.print(sample);
//			output.println();
//		}
	}
}
