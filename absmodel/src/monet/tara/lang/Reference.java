package monet.tara.lang;

public class Reference extends Variable {
	public String type;
	public boolean isList;

	public Reference(String type, String name, boolean isList) {
		this.type = type;
		this.name = name;
		this.isList = isList;
	}

	public String getType() {
		return type;
	}

	public boolean isList() {
		return isList;
	}

	@Override
	public boolean isProperty() {
		return false;
	}

	@Override
	public String toString() {
		return type + (isList ? "[]" : "") + " " + name;
	}

	@Override
	public Reference clone() {
		return new Reference(type, name, isList);
	}
}
