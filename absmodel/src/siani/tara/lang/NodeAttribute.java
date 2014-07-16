package siani.tara.lang;

public class NodeAttribute extends Variable {
	public final String primitiveType;
	public String value;

	public NodeAttribute(String type, String name, boolean isSingle, boolean isTerminal) {
		this.primitiveType = type;
		this.name = name;
		this.isSingle = isSingle;
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
		return primitiveType + (isSingle ? "[]" : "") + " " + name;
	}

	@Override
	public NodeAttribute clone() {
		NodeAttribute nodeAttribute = new NodeAttribute(primitiveType, name, isSingle, isTerminal);
		nodeAttribute.setValue(value);
		return nodeAttribute;
	}
}
