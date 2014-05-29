package monet.tara.lang;

public class Reference extends Variable {
	public String node;
	public boolean isList;

	public Reference(String node, String name, boolean isList) {
		this.node = node;
		this.name = name;
		this.isList = isList;
	}

	public String getNode() {
		return node;
	}

	public boolean isList() {
		return isList;
	}

	\@Override
	public boolean isProperty() {
		return false;
	}
}
