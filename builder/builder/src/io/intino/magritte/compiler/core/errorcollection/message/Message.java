package io.intino.magritte.compiler.core.errorcollection.message;

import io.intino.magritte.compiler.core.ProcessingUnit;
import io.intino.magritte.compiler.core.SourceUnit;
import io.intino.magritte.compiler.core.errorcollection.DependencyException;
import io.intino.magritte.compiler.core.errorcollection.SemanticException;
import io.intino.magritte.compiler.core.errorcollection.SyntaxException;

import java.io.PrintWriter;

public abstract class Message {

	public Message() {
	}

	public static Message create(String text, SourceUnit owner) {
		return new SimpleMessage(text, owner);
	}

	public static Message create(String text, Object data, SourceUnit owner) {
		return new SimpleMessage(text, data, owner);
	}

	public static Message create(SyntaxException error, SourceUnit owner) {
		return new SyntaxErrorMessage(error, owner);
	}

	public static Message create(SemanticException error, SourceUnit owner) {
		return new SemanticErrorMessage(error, owner);
	}

	public static Message create(DependencyException exception, SourceUnit owner) {
		return new DependencyErrorMessage(exception, owner);
	}

	public static Message create(String text, ProcessingUnit owner) {
		return new SimpleMessage(text, owner);
	}

	public abstract void write(PrintWriter paramPrintWriter);

}
