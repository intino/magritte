package siani.tara.lang;

public class NodeAttribute extends Variable {
	public final String primitiveType;
	public boolean isMultiple;
	public String value;

	public NodeAttribute(String type, String name, boolean isMultiple, boolean isTerminal) {
		this.primitiveType = type;
		this.name = name;
		this.isMultiple = isMultiple;
		this.isTerminal = isTerminal;
	}

	public NodeAttribute(String type, String name) {
		this.primitiveType = type;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getType() {
		return primitiveType;
	}

	public String toString() {
		return primitiveType + (isMultiple ? "[]" : "") + " " + name;
	}

	@Override
	public NodeAttribute clone() {
		NodeAttribute nodeAttribute = new NodeAttribute(primitiveType, name, isMultiple, isTerminal);
		nodeAttribute.setValue(value);
		return nodeAttribute;
	}
}
