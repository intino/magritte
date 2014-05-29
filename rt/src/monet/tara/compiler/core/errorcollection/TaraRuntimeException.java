package monet.tara.compiler.core.errorcollection;


import monet.tara.lang.AbstractNode;

public class TaraRuntimeException extends RuntimeException {

	private AbstractNode node;

	public TaraRuntimeException(String message) {
		super(message);
	}

	public TaraRuntimeException(String message, AbstractNode node) {
		super(message);
		this.node = node;
	}

	public String getMessage() {
		return getMessageWithoutLocationText() + getLocationText();
	}

	public AbstractNode getNode() {
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
