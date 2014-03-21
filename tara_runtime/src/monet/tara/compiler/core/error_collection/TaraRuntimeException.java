package monet.tara.compiler.core.error_collection;


import monet.tara.compiler.core.ast.ASTNode;

public class TaraRuntimeException extends RuntimeException {

	//	private ModuleNode module;
	private ASTNode node;

	public TaraRuntimeException() {
	}

	public TaraRuntimeException(String message) {
		super(message);
	}

	public TaraRuntimeException(String message, ASTNode node) {
		super(message);
		this.node = node;
	}

	public TaraRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TaraRuntimeException(Throwable t) {
		initCause(t);
	}


	public String getMessage() {
		return getMessageWithoutLocationText() + getLocationText();
	}

	public ASTNode getNode() {
		return this.node;
	}

	public String getMessageWithoutLocationText() {
		return super.getMessage();
	}

	protected String getLocationText() {
		String answer = ". ";
		if (this.node != null) {
			answer = answer + "At [" + this.node.getLine() + "] ";
		}
		if (answer.equals(". ")) {
			return "";
		}
		return answer;
	}
}
