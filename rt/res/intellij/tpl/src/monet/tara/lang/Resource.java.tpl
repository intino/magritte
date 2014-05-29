package monet.tara.lang;

public class Resource extends Variable {

	public final String resourceType;
	public final boolean isProperty;

	public Resource(String resourceType, String name, boolean isProperty) {
		this.resourceType = resourceType;
		this.name = name;
		this.isProperty = isProperty;
	}

	\@Override
	public boolean isList() {
		return false;
	}

	\@Override
	public boolean isProperty() {
		return isProperty;
	}
}
