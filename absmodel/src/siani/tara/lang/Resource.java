package siani.tara.lang;

public class Resource extends Variable {

	public final String node;

	public Resource(String node, String name, boolean isTerminal) {
		this.node = node;
		this.name = name;
		this.isTerminal = isTerminal;
	}

	public Resource(String node, String name) {
		this.node = node;
		this.name = name;
	}

	@Override
	public String getType() {
		return node;
	}

	@Override
	public String getValue() {
		return node;
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override

	public String toString() {
		return "Resource:" + node + " " + name;
	}

	@Override
	public Variable clone() {
		return new Resource(node, name, isTerminal);
	}
}
