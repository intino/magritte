package siani.tara.lang;

public class NodeAttribute extends Variable {
	public final String primitiveType;
	public String value;
	public String measure;

	public NodeAttribute(String type, String name, boolean isList, boolean isTerminal) {
		this.primitiveType = type;
		this.name = name;
		this.isList = isList;
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
		return primitiveType + (isList ? "..." : "") + " " + name;
	}

	@Override
	public NodeAttribute clone() {
		NodeAttribute nodeAttribute = new NodeAttribute(primitiveType, name, isList, isTerminal);
		nodeAttribute.setValue(value);
		nodeAttribute.setProperty(isProperty);
		nodeAttribute.measure = measure;
		return nodeAttribute;
	}
}
