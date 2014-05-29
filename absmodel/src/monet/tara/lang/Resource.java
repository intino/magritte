package monet.tara.lang;

public class Resource extends Variable {

	public final String type;
	public final boolean isProperty;

	public Resource(String type, String name, boolean isProperty) {
		this.type = type;
		this.name = name;
		this.isProperty = isProperty;
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public boolean isProperty() {
		return isProperty;
	}
}
