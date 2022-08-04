package io.intino.magritte.builder.core.errorcollection;


import io.intino.magritte.lang.model.Element;

public class TaraRuntimeException extends RuntimeException {

	private final transient Element element;

	public TaraRuntimeException(String message, Element element, Throwable e) {
		super(message, e);
		this.element = element;
	}

	@Override
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
		if (this.element != null) answer = answer + "At [" + this.element.line() + "] ";
		if (". ".equals(answer)) return "";
		return answer;
	}
}
