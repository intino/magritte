package monet.tara.compiler.core.errorcollection;

import monet.tara.compiler.core.CompilerConfiguration;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MultipleCompilationErrorsException extends CompilationFailedException {
	protected final ErrorCollector collector;

	public MultipleCompilationErrorsException(ErrorCollector ec) {
		super(0, null);
		if (ec == null) {
			CompilerConfiguration config = super.getUnit() != null ? super.getUnit().getConfiguration() : new CompilerConfiguration();
			this.collector = new ErrorCollector(config);
		} else
			this.collector = ec;
	}

	public ErrorCollector getErrorCollector() {
		return this.collector;
	}

	public String getMessage() {
		StringWriter data = new StringWriter();
		PrintWriter writer = new PrintWriter(data);
		writer.write(super.getMessage());
		writer.println(":");
		this.collector.write(writer);
		return data.toString();
	}
}
