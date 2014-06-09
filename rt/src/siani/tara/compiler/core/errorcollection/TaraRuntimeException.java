package siani.tara.compiler.core.errorcollection;


import siani.tara.lang.Node;

public class TaraRuntimeException extends RuntimeException {

	private Node node;

	public TaraRuntimeException(String message) {
		super(message);
	}

	public TaraRuntimeException(String message, Node node) {
		super(message);
		this.node = node;
	}

	public String getMessage() {
		return getMessageWithoutLocationText() + getLocationText();
	}

	public Node getNode() {
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
