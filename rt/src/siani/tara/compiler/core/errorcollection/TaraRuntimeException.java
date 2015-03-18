package siani.tara.compiler.core.errorcollection;


import siani.tara.model.DeclaredNode;

public class TaraRuntimeException extends RuntimeException {

	private final DeclaredNode node;

	public TaraRuntimeException(String message, DeclaredNode node) {
		super(message);
		this.node = node;
	}

	public String getMessage() {
		return getMessageWithoutLocationText() + getLocationText();
	}

	public DeclaredNode getNode() {
		return this.node;
	}

	public String getMessageWithoutLocationText() {
		return super.getMessage();
	}

	protected String getLocationText() {
		String answer = ". ";
		if (this.node != null) answer = answer + "At [" + this.node.getLine() + "] ";
		if (". ".equals(answer)) return "";
		return answer;
	}
}
