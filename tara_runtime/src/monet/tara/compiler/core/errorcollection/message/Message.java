package monet.tara.compiler.core.errorcollection.message;

import monet.tara.compiler.core.ProcessingUnit;
import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.errorcollection.SyntaxException;
import monet.tara.compiler.core.errorcollection.semantic.SemanticError;

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

	public static Message create(SemanticError error, SourceUnit owner) {
		return new SemanticErrorMessage(error, owner);
	}

	public static Message create(String text, ProcessingUnit owner) {
		return new SimpleMessage(text, owner);
	}

	public abstract void write(PrintWriter paramPrintWriter);

}
