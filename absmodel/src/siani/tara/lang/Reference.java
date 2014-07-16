package siani.tara.lang;

public class Reference extends Variable {
	public String type;
	public boolean empty = false;

	public Reference(String type, String name, boolean isMultiple, boolean isTerminal) {
		this.type = type;
		this.name = name;
		this.isSingle = isMultiple;
		this.isTerminal = isTerminal;
	}

	public Reference(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public String getType() {
		return type;
	}

	@Override
	public String getValue() {
		return null;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	@Override
	public String toString() {
		return type + (isSingle ? "[]" : "") + " " + name;
	}

	@Override
	public Reference clone() {
		return new Reference(type, name, isSingle, isTerminal);
	}
}
