package siani.tara.lang;

public class Reference extends Variable {
	public String type;
	public boolean empty = false;

	public Reference(String type, String name, boolean isMultiple, boolean isTerminal) {
		this.type = type;
		this.name = name;
		this.isList = isMultiple;
		this.isTerminal = isTerminal;
	}

	public Reference(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String[] getDefaultValues() {
		return (empty) ? new String[]{EMPTY} : new String[0];
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	@Override
	public String toString() {
		return type + (isList ? "..." : "") + " " + name;
	}

	@Override
	public Reference clone() {
		Reference reference = new Reference(type, name, isList, isTerminal);
		reference.setProperty(isProperty);
		reference.setUniversal(isUniversal);
		reference.setDefaultValues(getDefaultValues());
		if (values != null)
			for (Object value : values) reference.addValue(value);
		return reference;
	}
}
