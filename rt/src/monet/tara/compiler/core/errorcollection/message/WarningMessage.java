package monet.tara.compiler.core.errorcollection.message;

import monet.tara.compiler.core.SourceUnit;

import java.io.PrintWriter;

public class WarningMessage extends SimpleMessage {
	public static final int NONE = 0;
	public static final int LIKELY_ERRORS = 1;
	public static final int POSSIBLE_ERRORS = 2;
	public static final int PARANOIA = 3;
	private int importance;

	public WarningMessage(int importance, String message, SourceUnit owner) {
		super(message, owner);
		this.importance = importance;
	}

	public WarningMessage(int importance, String message, Object data, SourceUnit owner) {
		super(message, data, owner);
		this.importance = importance;
	}

	public static boolean isRelevant(int actual, int limit) {
		return actual <= limit;
	}

	public boolean isRelevant(int importance) {
		return isRelevant(this.importance, importance);
	}

	public void write(PrintWriter writer) {
		writer.print("warning: ");
		super.write(writer);
	}


}