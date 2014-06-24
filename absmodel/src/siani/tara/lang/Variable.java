package siani.tara.lang;

public abstract class Variable implements Cloneable {
	public String name;
	public String doc;
	public boolean isTerminal = false;
	public boolean isMultiple = false;
	public boolean isProperty = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMultiple() {
		return isMultiple;
	}

	public void setMultiple(boolean multiple) {
		this.isMultiple = multiple;
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
