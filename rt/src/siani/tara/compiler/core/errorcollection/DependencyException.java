package siani.tara.compiler.core.errorcollection;


import siani.tara.compiler.model.Element;
import siani.tara.compiler.model.Node;
import siani.tara.compiler.model.impl.NodeImpl;

public class DependencyException extends TaraException {

	private final String message;
	private final Element element;
	private final int line;

	public DependencyException(String message, Element element) {
		this.message = message;
		this.element = element;
		if (element != null)
			this.line = element.getLine();
		else this.line = -1;
	}

	public String getMessage() {
		String node = element != null && element instanceof NodeImpl ? ((Node) element).getQualifiedName() : "";
		return "Inconsistent dependency in reference " + node + "; " + message + " @ line " + this.line + ", column " + 1 + ".";
	}

	public Element getElement() {
		return element;
	}

	public int getLine() {
		return line;
	}
}
