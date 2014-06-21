package siani.tara.lang;

public class Reference extends Variable {
	public String type;

	public Reference(String type, String name, boolean isMultiple, boolean isTerminal) {
		this.type = type;
		this.name = name;
		this.isMultiple = isMultiple;
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
	public String toString() {
		return type + (isMultiple ? "[]" : "") + " " + name;
	}

	@Override
	public Reference clone() {
		return new Reference(type, name, isMultiple, isTerminal);
	}
}
