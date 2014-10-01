package siani.tara.lang;

public class Attribute extends Variable {
	public final String primitiveType;
	public String value;
	public String measure;

	public Attribute(String type, String name, boolean isList, boolean isTerminal) {
		this.primitiveType = type;
		this.name = name;
		this.isList = isList;
		this.isTerminal = isTerminal;
	}

	public Attribute(String type, String name) {
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
	public Attribute clone() {
		Attribute attribute = new Attribute(primitiveType, name, isList, isTerminal);
		attribute.setValue(value);
		attribute.setProperty(isProperty);
		attribute.measure = measure;
		return attribute;
	}
}
