package siani.tara.lang;

public abstract class Variable implements Cloneable {
	public String name;
	public String doc;
	public boolean isTerminal = false;
	public boolean isSingle = false;
	public boolean isProperty = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSingle() {
		return isSingle;
	}

	public void setSingle(boolean multiple) {
		this.isSingle = multiple;
	}

	public boolean isTerminal() {
		return isTerminal;
	}

	public void setTerminal(boolean isTerminal) {
		this.isTerminal = isTerminal;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public boolean isProperty() {
		return isProperty;
	}

	public void setProperty(boolean isProperty) {
		this.isProperty = isProperty;
	}

	public abstract String getType();

	public abstract Variable clone();

	public abstract String toString();
}
