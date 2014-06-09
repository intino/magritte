package siani.tara.lang;

public abstract class Variable implements Cloneable {
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract boolean isList();

	public abstract boolean isProperty();

	public abstract String toString();

	public abstract Variable clone();

}