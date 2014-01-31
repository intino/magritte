package monet.tara.compiler.core.error_collection.message;

import monet.tara.compiler.core.SourceUnit;
import monet.tara.compiler.core.error_collection.SyntaxException;

import java.io.PrintWriter;

/**
 * Created by oroncal on 27/01/14.
 */
public abstract class Message {

	public Message() {
	}

	public abstract void write(PrintWriter paramPrintWriter);


	public static Message create(String text, SourceUnit owner) {
		return new SimpleMessage(text, owner);
	}

	public static Message create(String text, Object data, SourceUnit owner) {
		return new SimpleMessage(text, data, owner);
	}

	public static Message create(SyntaxException error, SourceUnit owner) {
		return new SyntaxErrorMessage(error, owner);
	}
}
