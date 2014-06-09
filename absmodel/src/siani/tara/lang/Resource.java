package siani.tara.lang;

public class Resource extends Variable {

	public final String node;
	public final boolean isProperty;

	public Resource(String node, String name, boolean isProperty) {
		this.node = node;
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

	public String toString() {
		return "Resource:" + node + " " + name;
	}

	@Override
	public Variable clone() {
		return new Resource(node, name, isProperty);
	}
}
