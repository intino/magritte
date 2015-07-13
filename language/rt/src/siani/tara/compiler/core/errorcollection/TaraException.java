package siani.tara.compiler.core.errorcollection;

public class TaraException extends Exception {

	private final boolean fatal;

	public TaraException() {
		fatal = true;
	}

	public TaraException(String message) {
		super(message);
		fatal = true;
	}

	public TaraException(String message, Throwable cause) {
		super(message, cause);
		fatal = true;
	}

	public TaraException(boolean fatal) {
		this.fatal = fatal;
	}

	public TaraException(String message, boolean fatal) {
		super(message);
		this.fatal = fatal;
	}

	public boolean isFatal() {
		return this.fatal;
	}

}
