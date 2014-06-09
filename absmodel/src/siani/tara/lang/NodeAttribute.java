package siani.tara.lang;

public class NodeAttribute extends Variable {
	public final String primitiveType;
	public final boolean isProperty;
	public final boolean isList;
	public String value;

	public NodeAttribute(String type, String name, boolean isList, boolean isProperty) {
		this.primitiveType = type;
		this.name = name;
		this.isList = isList;
		this.isProperty = isProperty;
	}

	public String getPrimitiveType() {
		return primitiveType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isList() {
		return isList;
	}

	public boolean isProperty() {
		return isProperty;
	}

	public String toString() {
		return primitiveType + (isList ? "[]" : "") + " " + name;
	}

	@Override
	public NodeAttribute clone() {
		NodeAttribute nodeAttribute = new NodeAttribute(primitiveType, name, isList, isProperty);
		nodeAttribute.setValue(value);
		return nodeAttribute;
	}
}
