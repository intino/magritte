package siani.tara.lang;

public abstract class Variable implements Cloneable {
	public String name;
	public String doc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract String getType();

	public abstract boolean isList();

	public abstract boolean isProperty();

	public abstract String toString();

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public abstract Variable clone();

}
