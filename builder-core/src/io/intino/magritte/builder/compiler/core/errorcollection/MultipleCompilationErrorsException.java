package io.intino.magritte.builder.compiler.core.errorcollection;

import io.intino.magritte.builder.compiler.core.CompilerConfiguration;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MultipleCompilationErrorsException extends CompilationFailedException {
	protected final transient ErrorCollector collector;

	public MultipleCompilationErrorsException(ErrorCollector ec) {
		super(0, null);
		if (ec == null) {
			CompilerConfiguration config = super.getUnit() != null ? super.getUnit().configuration() : new CompilerConfiguration();
			this.collector = new ErrorCollector(config);
		} else
			this.collector = ec;
	}

	public ErrorCollector getErrorCollector() {
		return this.collector;
	}

	@Override
	public String getMessage() {
		StringWriter data = new StringWriter();
		PrintWriter writer = new PrintWriter(data);
		writer.write(super.getMessage());
		writer.println(":");
		this.collector.write(writer);
		return data.toString();
	}
}
