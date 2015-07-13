package tara.compiler.core.errorcollection;


import tara.compiler.model.Element;

public class TaraRuntimeException extends RuntimeException {

	private final Element element;

	public TaraRuntimeException(String message, Element element) {
		super(message);
		this.element = element;
	}

	public String getMessage() {
		return getMessageWithoutLocationText() + getLocationText();
	}

	public Element getElement() {
		return this.element;
	}

	public String getMessageWithoutLocationText() {
		return super.getMessage();
	}

	protected String getLocationText() {
		String answer = ". ";
		if (this.element != null) answer = answer + "At [" + this.element.getLine() + "] ";
		if (". ".equals(answer)) return "";
		return answer;
	}
}
