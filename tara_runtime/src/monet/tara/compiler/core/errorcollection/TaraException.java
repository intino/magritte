package monet.tara.compiler.core.errorcollection;

public class TaraException extends Exception {

	private boolean fatal = true;

	public TaraException() {
	}

	public TaraException(String message) {
		super(message);
	}

	public TaraException(String message, Throwable cause) {
		super(message, cause);
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

	public void setFatal(boolean fatal) {
		this.fatal = fatal;
	}
}
