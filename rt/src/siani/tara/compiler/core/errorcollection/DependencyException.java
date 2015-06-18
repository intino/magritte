package siani.tara.compiler.core.errorcollection;


import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.Parameter;
import siani.tara.compiler.model.Variable;
import siani.tara.compiler.model.impl.NodeImpl;
import siani.tara.semantic.MessageProvider;

public class DependencyException extends TaraException {

	private final String message;
	private final Element element;
	private final String[] parameters;
	private final int line;

	public DependencyException(String message, Element element, String... parameters) {
		this.message = message;
		this.element = element;
		this.parameters = parameters;
		if (element != null)
			this.line = element.getLine();
		else this.line = -1;
	}

	public String getMessage() {
		String elementReference = element != null && element instanceof NodeImpl ? ((Node) element).getQualifiedName() : getElement(element);
		return "Inconsistent dependency in " + elementReference + "; " + getCompleteMessage() + " @ line " + this.line + ", column " + 1 + ".";
	}

	private String getElement(Element element) {
		if (element instanceof Variable)
			return "variable " + element.toString();
		else if (element instanceof Parameter) return "parameter " + element.toString();
		else return element.toString();

	}

	public Element getElement() {
		return element;
	}

	public int getLine() {
		return line;
	}

	private String getCompleteMessage() {
		return MessageProvider.message(message, parameters);
	}
}
