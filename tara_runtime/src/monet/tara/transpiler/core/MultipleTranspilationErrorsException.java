package monet.tara.transpiler.core;

import org.codehaus.groovy.control.Janitor;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MultipleTranspilationErrorsException extends TranspilationFailedException

{
	protected ErrorCollector collector;

	public MultipleTranspilationErrorsException(ErrorCollector ec) {
		super(0, null);
		if (ec == null) {
			TranspilerConfiguration config = super.getUnit() != null ? super.getUnit().getConfiguration() : new TranspilerConfiguration();

			this.collector = new ErrorCollector(config);
		} else {
			this.collector = ec;
		}
	}

	public ErrorCollector getErrorCollector() {
		return this.collector;
	}

	public String getMessage() {
		StringWriter data = new StringWriter();
		PrintWriter writer = new PrintWriter(data);
		Janitor janitor = new Janitor();

		writer.write(super.getMessage());
		writer.println(":");
		try {
			this.collector.write(writer, janitor);
		} finally {
			janitor.cleanup();
		}

		return data.toString();
	}
}
