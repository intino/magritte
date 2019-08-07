package io.intino.tara.compiler.core.errorcollection;


import io.intino.tara.compiler.model.NodeImpl;
import io.intino.tara.lang.model.Element;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Parameter;
import io.intino.tara.lang.model.Variable;
import io.intino.tara.lang.semantics.MessageProvider;

public class DependencyException extends TaraException {

	private final String message;
	private transient final Element element;
	private final String[] parameters;
	private final int line;

	public DependencyException(String message, Element element, String... parameters) {
		this.message = message;
		this.element = element;
		this.parameters = parameters;
		if (element != null) this.line = element.line();
		else this.line = -1;
	}

	@Override
	public String getMessage() {
		String elementReference = element != null && element instanceof NodeImpl ? ((Node) element).qualifiedName() : getElement(element);
		return "Dependency resolution error in " + elementReference + "; " + getCompleteMessage() + " @ line " + this.line + ", column " + 1 + ".";
	}

	private String getElement(Element element) {
		if (element == null) return "";
		if (element instanceof Variable)
			return "variable " + element.toString();
		else if (element instanceof Parameter) return "parameter";
		else return element.toString();
	}

	public Element getElement() {
		return element;
	}

	public int getLine() {
		return line;
	}

	private String getCompleteMessage() {
		return MessageProvider.message(message, (Object[]) parameters);
	}
}
